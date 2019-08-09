[English Version](README.md)  
### Dubbo Proxy
Dubbo Proxy是一个Dubbo网关，可以将Http请求转换成Dubbo的协议，调用Dubbo服务并且返回结果，后续还会集成熔断，限流，api管理等功能。

### 用法
http请求格式如下： 
```
POST {application Name}/​{Interface name}?version={version}&group={group}
```
其中group和version是Dubbo服务对应的group和version，为可选参数

http POST body如下: 

```json
{
    "methodName" : "sayHello",
    "paramTypes" : ["org.apache.dubbo.demo.model.User"],
    "paramValues": [
        {
            "id": 23,
            "username": "testUser"
        }
    ]
}
```
