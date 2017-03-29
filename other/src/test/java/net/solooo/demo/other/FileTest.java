package net.solooo.demo.other;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/3/28-028
 * History:
 * his1:
 */
public class FileTest {
    @Test
    public void fileList() throws IOException {
        Path path = Paths.get("D:/backup/");

        Map<Path, Long> files = new TreeMap<>();
        for (Path p : Files.newDirectoryStream(path)) {
            FileTime creationTime = Files.getLastModifiedTime(p);
            long modifiedTime = creationTime.toMillis();
            files.put(p, modifiedTime);
        }

        int count = files.size();
        if (count > 7) {
            Iterator<Map.Entry<Path, Long>> iterator = files.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Path, Long> entry = iterator.next();
                Long value = entry.getValue();
                if (System.currentTimeMillis() - value > 7*24*60*60*1000) {
                    System.out.println("删除文件：" + entry.getKey().getFileName().toString());
                    Files.deleteIfExists(entry.getKey());
                    count--;
                    if (count <= 7) {
                        break;
                    }
                }
            }
        }
    }
}
