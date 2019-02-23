# hty-master
1：项目采用的consul作为注册中心，需要先启动consul
2：网关采用的spring最新的gateway
3：权限控制使用的oauth2集成的jwt
4：访问获取/oahth/token从授权服务获取token，每次请求前gateway拦截器会去验证权限
