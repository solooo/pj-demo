package net.solooo.demo.other.fastdfs;

import org.junit.Test;

/**
 * Description:
 * Author:Eric
 * Date:2017/10/11
 */
public class FdfsClient {

    public String local_filename = "E:\\fastdfs\\test.png";

    @Test
    public void testUpload() {

        try {
            String configPath = this.getClass().getClassLoader().getResource("fdfs_client.conf").getFile();
            System.out.println(configPath);
//            ClientGlobal.init(configPath);
//
//            TrackerClient tracker = new TrackerClient();
//            TrackerServer trackerServer = tracker.getConnection();
//
//            StorageClient storageClient = new StorageClient(trackerServer, null);
//
//            String[] fileIds = storageClient.upload_file(local_filename, "png", null);

//            System.out.println(Arrays.toString(fileIds));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
