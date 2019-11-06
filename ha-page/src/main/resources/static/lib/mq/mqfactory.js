'use strict';
(function () {
    var _root = this;
    var _has_require = typeof require !== 'undefined';
    var _mqtt, _instance, _options, _uber;

    if (_has_require) {
        _mqtt = require('mqtt');
    } else {
        _mqtt = mqtt;
    }

    //深度复制对象，避免在构造mq的原型时，被本库污染原来的对象
    function deepCopy(p, c) {
        c = c || {};
        for (var i in p) {
            if (p.hasOwnProperty(i)) {
                if (typeof p[i] === 'object') {
                    //for ES3
                    if (Array.isArray !== 'function') {
                        Array.isArray = function (candidate) {
                            return Object.prototype.toString.call(candidate) === '[object Array]';
                        }
                    }
                    c[i] = Array.isArray(p[i]) ? [] : {};
                    deepCopy(p[i], c[i]);
                } else {
                    c[i] = p[i];
                }
            }
        }
        return c;
    }

    function mqclient(options, uber) {
        var _messageHandlers = [];

        function F() { };

        //uber是mqclient的扩展对象，用于简化订阅和反订阅，其作用类似c#的扩展方法，
        //为mqclient添加与业务功能相对应的方法，免去关注mqclient中的onss方法等细节问题
        F.prototype = deepCopy(uber);

        //创建MqttClient对象
        F.prototype.adv = _mqtt.connect(options);

        //断开MQ连接
        F.prototype.end = function (force, cb) {
            this.adv.end(force, cb);
        };

        //注册MQClient事件
        F.prototype.on = function (e, h) {
            this.adv.addListener(e, h);
        };

        //注册MQClient单次事件
        F.prototype.once = function (e, h) {
            this.adv.once(e, h);
        }

        //移除MQClient事件
        F.prototype.off = function (e, h) {
            this.adv.removeListener(e, h);
        };

        //创建一个新的MqClient对象，与单例模式不是同一对象
        F.prototype.fork = function () {
            var options = arguments.length > 0 ? arguments[0] : _options;
            var uber = arguments.length > 1 ? arguments[1] : _uber;
            if (_options.clientId == options.clientId) {
                options.clientId = 'mqttjs_' + Math.random().toString(16).substr(2, 8);
            }
            return mqclient(options, uber);
        }

        //简单订阅消息
        F.prototype.ss = function (topic, handler) {
            var that = this;
            this.adv.subscribe(topic, function () {
                if (_messageHandlers.indexOf(topic) < 0) {
                    _messageHandlers.push(topic);
                }

                that.adv.emit('onss', topic);

                if (handler) {
                    that.adv.addListener(topic, handler);
                }
            });
        };

        //简单取消订阅消息
        F.prototype.sus = function (topic, handler) {
            var that = this;
            this.adv.unsubscribe(topic, function () {
                var index = _messageHandlers.indexOf(topic);
                _messageHandlers.splice(index, 1);
                that.adv.emit('onsus', topic);

                if (handler) {
                    that.adv.removeListener(topic, handler);
                }
            });
        };

        //发布消息
        F.prototype.pub = function (topic, message) {
            this.adv.publish(topic, message);
        }

        //为简单订阅绑定事件
        F.prototype.adv.on('message', function (topic, message) {
            if (_messageHandlers.indexOf(topic) >= -1) {
                this.emit(topic, message);
            }
        });

        return new F();
    }

    function inject(options) {
        if (!_instance) {
            _options = options;
            if (arguments.length > 1) {
                _uber = arguments[1];
            }
        } else {
            throw new Error('Can not inject super object after mqclient initialized.');
        }
    }

    /********************************************
     * Mqtt connect options sample
     * 
     * var mqttOpts = { host: '192.168.1.88', port: 1883, username: 'admin', password: 'admin', protocol: 'mqtt', clientId: (() => { return 'mqttjs_' + Math.random().toString(16).substr(2, 8); })() };
     * 
     ********************************************/
    function create() {
        if (!_instance) {
            var options = arguments.length > 0 ? arguments[0] : _options;
            var uber = arguments.length > 1 ? arguments[1] : _uber;

            if (!options) {
                throw new Error('Mqtt connect options is undefined.');
            }

            if (!_options) {
                _options = options;
            }

            if (!_uber) {
                _uber = uber;
            }

            _instance = mqclient(options, uber);
        }

        return _instance;
    }

    var mqfactory = {
        inject: inject,
        create: create
    };

    if (typeof exports !== 'undefined') {
        if (typeof module !== 'undefined' && module.exports) {
            exports = module.exports = mqfactory;
        }
        exports.mqfactory = mqfactory;
    } else {
        _root.mqfactory = mqfactory;
    }

}).call(this);