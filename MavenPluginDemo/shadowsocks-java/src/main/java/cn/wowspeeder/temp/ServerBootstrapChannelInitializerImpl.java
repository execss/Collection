package cn.wowspeeder.temp;

import cn.wowspeeder.socks5.SocksServerHandler;
import cn.wowspeeder.ss.SSCommon;
import cn.wowspeeder.ss.SSLocalTcpProxyHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.socksx.SocksPortUnificationServerHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class ServerBootstrapChannelInitializerImpl<NioSocketChannel> extends ChannelInitializer {

    private String server;
    private Integer port;
    private String method;
    private String password;
    private String obfs;
    private String obfsparam;

    public ServerBootstrapChannelInitializerImpl(String server, Integer port, String method, String password, String obfs, String obfsparam) {
        this.server = server;
        this.port = port;
        this.method = method;
        this.password = password;
        this.obfs = obfs;
        this.obfsparam = obfsparam;

    }

    @Override
    protected void initChannel(Channel ctx) throws Exception {
//        logger.debug("channel initializer");
        ctx.pipeline()
                //timeout
                .addLast("timeout", new IdleStateHandler(0, 0, SSCommon.TCP_PROXY_IDEL_TIME, TimeUnit.SECONDS) {
                    @Override
                    protected IdleStateEvent newIdleStateEvent(IdleState state, boolean first) {
                        ctx.close();
                        return super.newIdleStateEvent(state, first);
                    }
                });

        //socks5
        ctx.pipeline()
//                                .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new SocksPortUnificationServerHandler()).addLast(SocksServerHandler.INSTANCE).addLast(new SSLocalTcpProxyHandler(server, port, method, password, obfs, obfsparam));
    }

}
