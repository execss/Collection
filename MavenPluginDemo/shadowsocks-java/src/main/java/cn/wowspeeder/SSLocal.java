package cn.wowspeeder;

import cn.wowspeeder.config.Config;
import cn.wowspeeder.config.ConfigLoader;
import cn.wowspeeder.ss.*;
import cn.wowspeeder.temp.BootstrapChannelInitializer;
import cn.wowspeeder.temp.ServerBootstrapChannelInitializerImpl;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;


public class SSLocal {
    private static InternalLogger logger = InternalLoggerFactory.getInstance(SSLocal.class);


    private static EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    private static SSLocal SSLocal = new SSLocal();

    public static SSLocal getInstance() {
        return SSLocal;
    }

    private SSLocal() {

    }

    public void start(String configPath) throws Exception {

        Config config = ConfigLoader.load(configPath);
        logger.info("load config ! ");
        startSingle(
                config.getLocalAddress(),
                config.getLocalPort(),
                config.getServer(),
                config.getPort(),
                config.getPassword(),
                config.getMethod(),
                config.getObfs(),
                config.getObfsParam());
    }

    private void startSingle(String socks5Server, Integer socks5Port, String server, Integer port, String password, String method, String obfs, String obfsparam) throws Exception {
        ServerBootstrap tcpBootstrap = new ServerBootstrap();

        ServerBootstrapChannelInitializerImpl<NioSocketChannel> serverBootstrapChannelInitializer = new ServerBootstrapChannelInitializerImpl<>(server,port,method,password,obfs,obfsparam);

        //local socks5  server ,tcp
        tcpBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)// 读缓冲区为32k
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(serverBootstrapChannelInitializer);
        tcpBootstrap.bind(socks5Server, socks5Port).sync();



        //local socks5  server ,udp
        Bootstrap udpBootstrap = new Bootstrap();
        BootstrapChannelInitializer bootstrapChannelInitializer = new BootstrapChannelInitializer();
        udpBootstrap.group(bossGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, false)// 支持广播
                .option(ChannelOption.SO_RCVBUF, 64 * 1024)// 设置UDP读缓冲区为64k
                .option(ChannelOption.SO_SNDBUF, 64 * 1024)// 设置UDP写缓冲区为64k
                .handler(bootstrapChannelInitializer);


        udpBootstrap.bind(socks5Server, socks5Port).sync();



        logger.info("listen at {}:{}", socks5Server, socks5Port);
    }

    public void stop() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        logger.info("Stop Server!");
    }

    public static void main(String[] args) throws Exception {
        try {

            getInstance().start("conf/config-example-client.json");
        } catch (Exception e) {
            e.printStackTrace();
            getInstance().stop();
            System.exit(-1);
        }
    }

}
