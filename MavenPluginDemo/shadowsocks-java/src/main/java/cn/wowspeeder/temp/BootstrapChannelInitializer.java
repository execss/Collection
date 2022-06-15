package cn.wowspeeder.temp;

import cn.wowspeeder.ss.SSLocalUdpProxyHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;

import static cn.wowspeeder.temp.ConfigStaticConstant.*;
import static cn.wowspeeder.temp.ConfigStaticConstant.obfsparam;

public class BootstrapChannelInitializer<NioDatagramChannel> extends ChannelInitializer {


    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
//                                .addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new SSLocalUdpProxyHandler(server, port, method, password, obfs, obfsparam))
        ;
    }
}
