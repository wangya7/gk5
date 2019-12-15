package wang.bannong.gk5.small.biz.wxmp.service;

import org.springframework.stereotype.Service;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMenuServiceImpl;

@Service("mpMenuService")
public class MpMenuService extends WxMpMenuServiceImpl {

    public MpMenuService(WxMpService wxMpService) {
        super(wxMpService);
    }
}
