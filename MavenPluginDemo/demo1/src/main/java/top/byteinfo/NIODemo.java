package top.byteinfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIODemo {
    private static ExecutorService bootstrapExecutorserverTest = Executors.newSingleThreadExecutor();
    private static ExecutorService bootstrapExecutorclientTest = Executors.newSingleThreadExecutor();
    public static int count = 0;

    private volatile static String ClientMsg;

    public static int lengthEnd = 0;

    private static boolean flag = true;


    public static void main(String[] args) {

        System.out.println("hello");
        System.out.println("NIODemo Start");

        bootstrapExecutorserverTest.submit(NIODemo::serverTest);
        bootstrapExecutorclientTest.submit(NIODemo::clientTest);

        System.out.println("NIODemo Start successful");
        System.out.println("world");
    }


    static void serverTest() {
        try (
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
        ) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 61666);
            serverSocketChannel.bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (true) {
                boolean b = selector.select(3 << 10) == 0;
                if (b) {
                    System.out.println("无链接");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();


                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1 << 20);
                        socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);
                    }
                    if (selectionKey.isReadable()) {



                        if (!flag) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            Object attachment = selectionKey.attachment();
                            ByteBuffer byteBuffer = (ByteBuffer) attachment;
                            int read = socketChannel.read(byteBuffer);
                            lengthEnd += ClientMsg.length();
                            TestS(byteBuffer, lengthEnd, ClientMsg);
                        }else {
                            continue;
                        }
//                        lengthEnd = TestS(byteBuffer, lengthEnd, ClientMsg);

//                        int lengthStart = 0;
//                        lengthEnd += ClientMsg.length();
//                        lengthStart = lengthEnd;
//                        System.out.println("bytes from client-----" + new String(byteBuffer.array()).substring(lengthStart - ClientMsg.length(), lengthEnd));


                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static void clientTest() {

        try (
                SocketChannel socketChannel = SocketChannel.open()
        ) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 61666);
            socketChannel.configureBlocking(false);
            boolean connect = socketChannel.connect(inetSocketAddress);

            if (!connect) {
                while (!socketChannel.finishConnect()) {
                    {

                        try {
                            Thread.sleep(1 << 7);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("need more time");
                    }
                }
            }


            while (true) {

                String defaultString = "hello world:";

                StringBuilder sb = new StringBuilder();



//                ClientMsg = sb.append(defaultString).append(count).toString();
//
//                ByteBuffer byteBuffer = ByteBuffer.wrap(ClientMsg.getBytes());


                    TestC(socketChannel,sb, defaultString);

//                socketChannel.write(buffer);



                try {
                    Thread.sleep(1 << 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static  void TestC(SocketChannel socketChannel,StringBuilder sb, String defaultString) {

        if (flag) {
            ClientMsg = sb.append(defaultString).append(count).toString();

            ByteBuffer byteBuffer = ByteBuffer.wrap(ClientMsg.getBytes());

            try {
                socketChannel.write(byteBuffer);
                flag = false;
                System.out.println("client::count:" + count);
                ++count;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            return;
        }

    }

    static  void TestS(ByteBuffer byteBuffer, int lengthEnd, String ClientMsg) {
        if (!flag ) {
            int lengthTemp = 0;
            lengthTemp = lengthEnd;

            String messageFromClient = new String(byteBuffer.array());

            int lengthStart = lengthTemp - ClientMsg.length();
            String s = messageFromClient.substring(lengthStart+12, lengthEnd);
            System.out.println(s);
            System.out.println("bytes from client-----" + messageFromClient.substring(lengthStart, lengthEnd));
        }else {
            return ;
        }

        flag=true;

    }
}
