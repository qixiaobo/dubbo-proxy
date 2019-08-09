[中文版本](README_zh.md)  
## Dubbo Proxy
Dubbo Proxy, a gateway of Dubbo, switch from HTTP request to Dubbo protocol，then invoke Dubbo service and return to the result. Later Dubbo Proxy would combine several features, including circuit breaker, current-limiting, api management. 


### instructions
 
HTTP request format:

```
{application Name}/​{Interface name}?version={version}&group={group}
```
Group and version is the mapping data in Dubbo service. 

http POST body: 

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
