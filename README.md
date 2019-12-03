# Getting Started


##一、模块说明
-------------
* 使用技术: SpringCloud全家桶、Elasticsearch、Redis、TkMapper、RabbitMQ、Swagger、Oauth2、OSS文件存储、阿里短信、微信支付宝支付、分布式锁、工作流
1. ha-common:公共类
2. ha-config:公共配置文件服务
3. ha-registry:注册中心服务
4. ha-gateway:网关服务
5. ha-pay:支付服务
6. ha-goods:商品服务
7. ha-user:用户管理服务
8. ha-auth:授权服务
9. ha-file: 文件服务
10. ha-sms: 短信服务
11. ha-search: 搜索服务
12. ha-page: 页面服务
13. ha-flow: 工作流服务


##二、服务端口占用
-------------

* 服务名          端口
  
* ha-gateway            10010
* ha-registry           10086
* ha-config             8769
* ha-goods              8081
* ha-auth               8082
* ha-pay                8083
* ha-user               8084
* ha-file               8085
* ha-sms                8086
* ha-search             8087
* ha-page               8088
* ha-flow               8089


##三、权限控制
* 权限调试：首先启动redis->注册中心服务(ha-registry)->配置中心服务(ha-config)->权限服务(ha-auth)->用户服务(ha-user)->商品服务(ha-goods)

* 登录：http://localhost:8082/userlogin?username=weirdo&password=111111
![](assets/img/login.png)



* 查询长令牌： http://localhost:8082/userjwt?uid=4f3886e9-79fc-4631-9dc7-c9b1ca54b919
![](assets/img/userInfo.png)


* 调用商品服务：http://localhost:8081/category/list?pid=0(请求头里面需要加长令牌：type:Bearer Token  )
![](assets/img/getGoods.png)

##四、集成Activiti
* 设计器访问地址：http://localhost:8089/activiti/create

* 接口地址：http://localhost:8089/doc.html

* 关于表解释：

    Activiti的后台是有数据库的支持，所有的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。
    ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
    ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
    ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
    ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
    ACT_GE_*: 通用数据， 用于不同场景下，如存放资源文件。
    资源库流程规则表
       1) act_re_deployment 部署信息表
       2) act_re_model  流程设计模型部署表
       3) act_re_procdef  流程定义数据表
    运行时数据库表
       1) act_ru_execution运行时流程执行实例表
       2) act_ru_identitylink运行时流程人员表，主要存储任务节点与参与者的相关信息
       3) act_ru_task运行时任务节点表
       4) act_ru_variable运行时流程变量数据表
    历史数据库表
    1) act_hi_actinst 历史节点表
    2) act_hi_attachment历史附件表
    3) act_hi_comment历史意见表
    4) act_hi_identitylink历史流程人员表
    5) act_hi_detail历史详情表，提供历史变量的查询
    6) act_hi_procinst历史流程实例表
    7) act_hi_taskinst历史任务实例表
    8) act_hi_varinst历史变量表
    通用数据表
    1) act_ge_bytearray二进制数据表
    2) act_ge_property属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录
    
    
    
    
    





