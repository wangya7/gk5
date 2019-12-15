package wang.bannong.gk5.small.biz.handler;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Component;

import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import wang.bannong.gk5.small.biz.wxmp.builder.AbstractBuilder;
import wang.bannong.gk5.small.biz.wxmp.builder.ImageBuilder;
import wang.bannong.gk5.small.biz.wxmp.builder.TextBuilder;
import wang.bannong.gk5.small.biz.wxmp.service.WeixinService;
import wang.bannong.gk5.small.common.entity.WxMenuKey;

@Component
public class MenuHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        WeixinService weixinService = (WeixinService) wxMpService;

        String key = wxMessage.getEventKey();
        WxMenuKey menuKey;
        try {
            menuKey = JSON.parseObject(key, WxMenuKey.class);
        } catch (Exception e) {
            return WxMpXmlOutMessage.TEXT().content(key)
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
        }

        AbstractBuilder builder = null;
        switch (menuKey.getType()) {
            case WxConsts.XmlMsgType.TEXT:
                builder = new TextBuilder();
                break;
            case WxConsts.XmlMsgType.IMAGE:
                builder = new ImageBuilder();
                break;
            case WxConsts.XmlMsgType.VOICE:
                break;
            case WxConsts.XmlMsgType.VIDEO:
                break;
            case WxConsts.XmlMsgType.NEWS:
                break;
            default:
                break;
        }

        if (builder != null) {
            try {
                return builder.build(menuKey.getContent(), wxMessage, weixinService);
            } catch (Exception e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        return null;
    }
}
