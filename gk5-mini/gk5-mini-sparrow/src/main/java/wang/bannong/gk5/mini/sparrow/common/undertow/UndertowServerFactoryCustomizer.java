package wang.bannong.gk5.mini.sparrow.common.undertow;

import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.xnio.OptionMap;
import org.xnio.Options;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

import java.io.IOException;

import io.undertow.connector.ByteBufferPool;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;

/**
 * 设置Undertow服务器 XnioWorker Buffers
 * @see <a>https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-programmatic-embedded-container-customization</a>
 */
public class UndertowServerFactoryCustomizer implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo info = (WebSocketDeploymentInfo) deploymentInfo.getServletContextAttributes()
                    .get(WebSocketDeploymentInfo.ATTRIBUTE_NAME);
            info.setWorker(getXnioWorker());
            info.setBuffers(new DefaultByteBufferPool(Boolean.getBoolean("io.undertow.websockets.direct-buffers"),
                    1024, 100, 12));
        });
    }

    /**
     * 获取XnioWorker
     *
     * @return
     */
    private XnioWorker getXnioWorker() {
        XnioWorker worker = null;
        try {
            worker = Xnio.getInstance().createWorker(OptionMap.create(Options.THREAD_DAEMON, true));
        } catch (IOException ignored) {
        }
        return worker;
    }

}
