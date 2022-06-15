package cn.wowspeeder.temp;

import cn.wowspeeder.config.ConfigLoader;

public class ConfigStaticConstant {

    public static String socks5Server = ConfigLoader.getSocks5Server();
    public static Integer socks5Port= ConfigLoader.getSocks5Port();

    public static String server = ConfigLoader.getServer();

    public final static Integer port = ConfigLoader.getPort();

    public static String password = ConfigLoader.getPassword();

    public static String method = ConfigLoader.getMethod();

    public static String obfs =ConfigLoader.getObfs();
    public static String obfsparam =ConfigLoader.getObfs();

}
