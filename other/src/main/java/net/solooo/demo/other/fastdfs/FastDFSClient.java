package net.solooo.demo.other.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * Description:
 * Author:Eric
 * Date:2017/10/11
 */
public class FastDFSClient {
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    //使用StorageClient1进行上传
    private StorageClient1 storageClient1 = null;

    /**
     * Instantiates a new Fast dfs client.
     *
     * @param conf the conf
     * @throws Exception the exception
     */
    public FastDFSClient(String conf) throws Exception {
        //获取classpath路径下配置文件"fdfs_client.conf"的路径
        //conf直接写相对于classpath的位置，不需要写classpath:
        String configPath = this.getClass().getClassLoader().getResource(conf).getFile();
        System.out.println(configPath);
        ClientGlobal.init(configPath);

        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient1 = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * MultipartFile picFile: file_buff = picFile.getBytes()
     *
     * @param file_buff     the file buff
     * @param file_ext_name the file ext name
     * @return the string
     * @throws Exception the exception
     */
    public String uploadFile(byte[] file_buff, String file_ext_name) throws Exception {
        String result = storageClient1.upload_file1(file_buff, file_ext_name, null);
        return result;
    }

    /**
     * Upload file string.
     *
     * @param local_filename the local filename
     * @param file_ext_name  the file ext name
     * @return the string
     * @throws Exception the exception
     */
    public String uploadFile(String local_filename, String file_ext_name) throws Exception {
        String result = storageClient1.upload_file1(local_filename, file_ext_name, null);
        return result;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
//test
    public static void main(String[] args) throws Exception {
        FastDFSClient client = new FastDFSClient("fdfs_client.conf");
        String result = client.uploadFile("E:\\fastdfs\\test.png", "png");
        System.out.println(result);
    }
}
