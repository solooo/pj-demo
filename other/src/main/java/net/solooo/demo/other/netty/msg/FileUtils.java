package net.solooo.demo.other.netty.msg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Description:
 * Author:Eric
 * Date:2017/7/18
 */
public class FileUtils {

    public static void insert(String fileName, long pos, String insertContent) throws IOException {
        File file = File.createTempFile("tmp", null);
        file.deleteOnExit();
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        raf.seek(pos);
        byte[] buff = new byte[64];
        int hasRead = 0;
        while ((hasRead = raf.read(buff)) > 0) {
            fileOutputStream.write(buff);
        }
        raf.seek(pos);
        raf.write(insertContent.getBytes());
        //追加文件插入点之后的内容
        while ((hasRead = fileInputStream.read(buff)) > 0) {
            raf.write(buff, 0, hasRead);
        }
        raf.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
