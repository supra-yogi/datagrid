# How to Run this App

```
$ docker pull infinispan/server:14.0.2.Final

$ docker run -p 11222:11222 -e USER=admin -e PASS=password infinispan/server:14.0.2.Final
```

Create cache on Infinispan Server
```json
{
  "test_datagrid": {
    "distributed-cache": {
      "owners": "1",
      "mode": "SYNC",
      "statistics": true,
      "encoding": {
        "key": {
          "media-type": "application/x-java-object"
        },
        "value": {
          "media-type": "application/x-java-object"
        }
      },
      "locking": {
        "isolation": "REPEATABLE_READ"
      }
    }
  }
}
```

```
$ curl -kv -X POST http://localhost:8082/hello/v1/greeting/edwin
*   Trying ::1:8082...
* TCP_NODELAY set
* Connected to localhost (::1) port 8082 (#0)
> POST /hello/v1/greeting/edwin HTTP/1.1
> Host: localhost:8082
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 11
< Date: Fri, 16 Dec 2022 08:05:20 GMT
<
* Connection #0 to host localhost left intact
Hello edwin                                   


$ curl -kv -X POST http://localhost:8082/hello/v1/greeting/edwin
*   Trying ::1:8082...
* TCP_NODELAY set
* Connected to localhost (::1) port 8082 (#0)
> POST /hello/v1/greeting/edwin HTTP/1.1
> Host: localhost:8082
> User-Agent: curl/7.65.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 32
< Date: Fri, 16 Dec 2022 08:05:24 GMT
<
* Connection #0 to host localhost left intact
Hello edwin, data got from cache                     
```