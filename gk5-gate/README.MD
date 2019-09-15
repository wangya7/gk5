# 网关实现


### version log
#### 1.0.0-SNAPSHOT : 基础功能
#### 1.1.0-SNAPSHOT : 规范API字段约定、超时校验、更新校验、加密校验
##### 字段规范：  
> post 请求采用 Content-Type:application/x-www-form-urlencoded
1. **api**: 必传，接口名称，例如：api=gate.user.adduser
2. **v**: 必传，接口版本号，例如：v=1.0
3. **ttid**：必传，渠道号，内部请求1001=Android，1002=IOS，1003=H5，其他外部渠道根据业务定义
4. **did**：必传，软硬件信息 厂牌@终端@设备系统信息@大版本号.小版本号.bugfix版本，即使信息丢失“@”也必须传 
    * HUAWEI P20@Android@Android 7.0@1.0.0 
    * Apple SE@IOS@ios11.0.0@1.0.0
    * Chrome/17.0.963.56@H5@Macintosh; Intel Mac OS X 10_7_0@1.0.0
5. **ts**：必传，Unix时间毫秒 例如：ts=1480929340486
6. **lng**：必传，经度 例如：lng=39.98871
7. **lat**：必传，纬度 例如：lat=116.43234
8. **mat**：非必传，用户token，32位唯一标识字符串，最终后端定义
9. **data**：非必传，请求数据JSON格式，必须经过URL编码 例如：data=%7b%22mobile%22%3a<br>%2213809852451%22%7d|
10. **sign**：非必传，签名，32位唯一标识字符串，最终后端定义
    * sign加密：api=xx&data=xx&did=xx&mat=xx&ts=xx&ttid=xx&v=%s，如果data和mat为空的情况下，加密的字符串值为空，那么加密的时任需带进去


