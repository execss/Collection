package demo.base.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", 18000);
        InputStream clientSocketInputStream = clientSocket.getInputStream();
        OutputStream clientSocketOutputStream = clientSocket.getOutputStream();

        clientSocketOutputStream.write("hello".getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream("NetIO.txt");

        byte[] bytes = new byte[512];
        int len = -1;


        while ((len = clientSocketInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }


//        while ((len=clientSocketInputStream.read(bytes))>0){
//            fileOutputStream.write(bytes,0,len);
//
//        }
    }

}
