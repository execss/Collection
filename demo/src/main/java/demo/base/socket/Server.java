package demo.base.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket =new ServerSocket(18000);
        Socket accept ;

             accept = serverSocket.accept();


            InputStream acceptInputStream = accept.getInputStream();
            OutputStream acceptOutputStream = accept.getOutputStream();
            acceptOutputStream.write("hello world".getBytes());



//        while (true){
//
//            byte[] bytes = new byte[512];
//            int len = -1;
//
//            while ((len=acceptInputStream.read(bytes))>0){
//                acceptOutputStream.write(bytes,0,len);
//            }
//            acceptOutputStream.write("hello world".getBytes());
//        }

    }
}
