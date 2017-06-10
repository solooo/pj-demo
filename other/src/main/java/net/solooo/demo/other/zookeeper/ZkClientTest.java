package net.solooo.demo.other.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/12
 */
public class ZkClientTest {

    public static final String PATH = "/zkClient";

    public static void main(String[] args) {
        ZkClient client = new ZkClient("192.168.1.6:2181");
        client.subscribeDataChanges(PATH, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("handleDataChange s: " + s + " data: " + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("handleDataDeleted s:" + s);
            }
        });

        client.create(PATH, "school", CreateMode.PERSISTENT);
        client.writeData(PATH, "城中");
        System.out.println((String) client.readData(PATH));

        client.create(PATH + "/child", "child", CreateMode.PERSISTENT);
        System.out.println(client.getChildren(PATH));
        client.writeData(PATH + "/child", "高三三班");
        System.out.println((String) client.readData(PATH + "/child"));

        client.delete(PATH + "/child");
        client.delete(PATH);
    }
}
