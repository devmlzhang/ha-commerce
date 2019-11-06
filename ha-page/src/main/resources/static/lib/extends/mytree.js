// 节点树
layui.define(['jquery', 'form'], function(exports){
    $ = layui.jquery;
    form = layui.form;

    obj = {
        el:null,
        nodes:null,
        // 渲染 + 绑定事件
        /**
         * 渲染DOM并绑定事件
         * @param  {[type]} el       [目标ID，如：#test1]
         * @param  {[type]} nodes    [数据，格式：{}]
         * @return {[type]} opt      [配置，格式：{}]
         */
        render: function(el, nodes, callback){
            obj.el=el;
            obj.nodes=nodes;
            obj.init(el,nodes);
            obj.event(el,callback);
            form.render('checkbox');
        },
        init:function(el, nodes){
            $(el).html('');
            //对nodes进行排序，使父标签pid在子标签id之前
            for(var i=0;i<nodes.length;i++){
                for(var j=0;j<nodes.length;j++){
                    if(nodes[i].pid==nodes[j].id){
                        if(i<j){
                            var temp = nodes[i];
                            nodes[i]=nodes[j];
                            nodes[j]=temp;
                            break;
                        }
                    }
                }
            }
            //图标
            var icon = {
                arrow: ['&#xe623;', '&#xe625;'] //箭头
            };
            var root = document.getElementById(el);
            for(var i=0;i<nodes.length;i++){
                if(nodes[i].pid=='#'){//根标签
                    if(root.childNodes.length==0){
                        var _ul = document.createElement('ul');
                        _ul.style.lineHeight='26px';
                        _ul.style.boxSizing='content-box';
                        root.appendChild(_ul);
                    }
                    var _li = document.createElement('li');
                    _li.setAttribute('id',nodes[i].id);
                    _li.style.textOverflow='ellipsis';
                    _li.style.overflow='hidden';
                    _li.style.whiteSpace='nowrap';
                    var _i =  document.createElement('i');
                    _i.setAttribute('class','layui-icon');
                    _i.setAttribute('spread','hidden');
                    _i.innerHTML=icon.arrow[0];
                    _i.style.cursor='pointer';
                    _i.style.opacity=0;
                    var _a =  document.createElement('a');
                    _a.setAttribute('href','javascript:;');
                    var _input =  document.createElement('input');
                    _input.setAttribute('type','checkbox');
                    _input.setAttribute('lay-skin','primary');
                    _input.setAttribute('title',nodes[i].name);
                    _input.setAttribute('pk',nodes[i].id);
                    _a.appendChild(_input);
                    _li.appendChild(_i);
                    _li.appendChild(_a);
                    root.childNodes[root.childNodes.length-1].appendChild(_li);
                }else{
                    var pid = document.getElementById(nodes[i].pid);
                    if(pid.childNodes.length==2){
                        pid.childNodes[0].style.opacity=1;
                        pid.childNodes[0].onclick=function(){
                            var spread = this.getAttribute('spread');
                            var p = this.parentNode;
                            if(spread=='hidden'){
                                //展開
                                this.setAttribute('spread','open');
                                this.innerHTML=icon.arrow[1];
                                $(p.childNodes[p.childNodes.length-1]).slideDown('fast');
                            }else{
                                //收起
                                this.setAttribute('spread','hidden');
                                this.innerHTML=icon.arrow[0];
                                $(p.childNodes[p.childNodes.length-1]).slideUp('fast');
                            }
                        };
                        var _ul = document.createElement('ul');
                        _ul.style.lineHeight='26px';
                        _ul.style.boxSizing='content-box';
                        _ul.style.paddingLeft='40px';
                        _ul.style.display='none';
                        pid.appendChild(_ul);
                    }
                    var _li = document.createElement('li');
                    _li.setAttribute('id',nodes[i].id);
                    var _i =  document.createElement('i');
                    _i.setAttribute('class','layui-icon');
                    _i.setAttribute('spread','hidden');
                    _i.innerHTML=icon.arrow[0];
                    _i.style.cursor='pointer';
                    _i.style.opacity=0;
                    var _a =  document.createElement('a');
                    _a.setAttribute('href','javascript:;');
                    var _input =  document.createElement('input');
                    _input.setAttribute('type','checkbox');
                    _input.setAttribute('lay-skin','primary');
                    _input.setAttribute('title',nodes[i].name);
                    _input.setAttribute('pk',nodes[i].id);
                    _a.appendChild(_input);
                    _li.appendChild(_i);
                    _li.appendChild(_a);
                    pid.childNodes[pid.childNodes.length-1].appendChild(_li);
                }
            }
        },
        event:function(el,callback){
            var root = document.getElementById(el);
            $(root).on('click', '.layui-form-checkbox', function(){
                var elem = $(this).prev();
                var checked = elem.is(':checked');
                var childs = elem.parent().next().find('input[type="checkbox"]').prop('checked', checked);
                if(checked){
                    elem.parents('#'+el+' ul').prev().find('input[type="checkbox"]').prop('checked', true);
                }
                form.render('checkbox');
                //註冊點擊的回調事件
                if(typeof callback=='function'){
                    var pk = elem.attr('pk');
                    for(var i=0;i<obj.nodes.length;i++){
                        if(obj.nodes[i].id==pk){
                            callback(obj.nodes[i]);
                            break;
                        }
                    }
                }
            });
        },
        getChecked: function(){
            var inputs = $('#'+obj.el).find('input[type="checkbox"]:checked');
            var data = [];
            inputs.each(function(index, item){
                var pk = item.getAttribute('pk');
                for(var i=0;i<obj.nodes.length;i++){
                    if(obj.nodes[i].id==pk){
                        data.push(obj.nodes[i]);
                    }
                }
            });
            return data;
        },
    };
    publicObj={
        render:obj.render,
        getChecked:obj.getChecked
    };
    exports('mytree', publicObj);
});