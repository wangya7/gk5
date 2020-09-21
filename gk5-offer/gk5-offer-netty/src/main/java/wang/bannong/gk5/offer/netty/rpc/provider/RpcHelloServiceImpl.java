package wang.bannong.gk5.offer.netty.rpc.provider;

import wang.bannong.gk5.offer.netty.rpc.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

    public String hello(String name) {  
        return "Hello " + name + "!";  
    }  
  
}  
