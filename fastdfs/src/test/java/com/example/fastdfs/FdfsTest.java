package com.example.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.*;
import java.util.Arrays;

/**
 * @author chengdu
 * @date 2019/7/13.
 */
@SpringBootTest
public class FdfsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FdfsTest.class);

    private static final String CONF_NAME = "fdfstest.conf";

    private StorageClient storageClient;

    private TrackerServer trackerServer;

//    @Before
    @BeforeAll
    public void initStorageClient() throws Exception {
        ClientGlobal.init(CONF_NAME);
        LOGGER.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        LOGGER.info("charset=" + ClientGlobal.g_charset);
        TrackerClient tracker = new TrackerClient();
        trackerServer = tracker.getTrackerServer();
        StorageServer storageServer = null;
        storageClient = new StorageClient(trackerServer, storageServer);
    }

//    @After
    @AfterAll
    public void closeClient() {
        LOGGER.info("close connection");
        if(storageClient != null){
            try {
               storageClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }

    public void writeByteToFile(byte[] fbyte, String fileName) throws IOException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = new File(fileName);
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fbyte);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Test
    public void upload() throws Exception{
        NameValuePair[] metaList = new NameValuePair[1];
        String local_filename = "build.PNG";
        metaList[0] = new NameValuePair("fileName", local_filename);

        File file = new File("C:/Users/chengdu/Desktop/build.PNG");
        InputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);

        String[] result = storageClient.upload_file(bytes, null, metaList);
        LOGGER.info("result {}", Arrays.asList(result));
        Assert.isTrue(2==result.length);
//        Assert.assertEquals(2, result.length);
    }

    @Test
    public void download() throws Exception {
        String[] uploadresult = {"group1", "M00/00/00/wKgBZV0phl2ASV1nAACk1tFxwrM3814331"};
        byte[] result = storageClient.download_file(uploadresult[0], uploadresult[1]);
        String local_filename = "build.PNG";
        writeByteToFile(result, local_filename);
        File file = new File(local_filename);
//        Assert.assertTrue(file.isFile());
    }

    @Test
    public void testUploadDownload() throws Exception {
        NameValuePair[] metaList = new NameValuePair[1];
        String local_filename = "build.PNG";
        metaList[0] = new NameValuePair("fileName", local_filename);
        File file = new File("C:/Users/chengdu/Desktop/build.PNG");
        InputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte[] bytes = new byte[length];
        inputStream.read(bytes);
        String[] result = storageClient.upload_file(bytes, null, metaList);
//        Assert.assertTrue(storageClient.isConnected());
        // pool testOnborrow  isAvaliable
//        Assert.assertTrue(storageClient.isAvaliable());
        LOGGER.info("result {}", Arrays.asList(result));
        byte[] resultbytes = storageClient.download_file(result[0], result[1]);
        writeByteToFile(resultbytes, local_filename);
        File downfile = new File(local_filename);
//        Assert.assertTrue(downfile.isFile());
    }

}
