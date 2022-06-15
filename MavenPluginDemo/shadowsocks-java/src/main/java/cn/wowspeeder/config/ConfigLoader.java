package cn.wowspeeder.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * 加载Config配置
 */
public class ConfigLoader {

    private static Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private static String socks5Server;
    private static Integer socks5Port;

    private static String server;

    private static Integer port;

    private static String password;

    private static String method;

    private static String obfs;
    private static String obfsparam;

    public static String getSocks5Server() {
        return socks5Server;
    }

    public static Integer getSocks5Port() {
        return socks5Port;
    }

    public static String getServer() {
        return server;
    }

    public static Integer getPort() {
        return port;
    }

    public static String getPassword() {
        return password;
    }

    public static String getMethod() {
        return method;
    }

    public static String getObfs() {
        return obfs;
    }

    public static String getObfsparam() {
        return obfsparam;
    }

    public static Config load(String file) {

        InputStream in = null;
        try {
            in = new FileInputStream(file);
            JsonReader reader;
            reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            Config config = new Gson().fromJson(reader, Config.class);
            reader.close();
            socks5Server = config.getLocalAddress();
            socks5Port = config.getLocalPort();
            server = config.getServer();
            port = config.getPort();
            password = config.getPassword();
            method = config.getMethod();
            obfs = config.getObfs();
            obfsparam = config.getObfsParam();


            return config;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
