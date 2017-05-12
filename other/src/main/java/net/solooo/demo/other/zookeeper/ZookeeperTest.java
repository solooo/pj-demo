package net.solooo.demo.other.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/12
 */
public class ZookeeperTest {

    public static void main(String[] args) {
        String content = "this is test测试";
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, null);
            zooKeeper.register(new ZkWatcher(zooKeeper, "/root"));
            zooKeeper.create("/root", "root data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.setData("/root", content.getBytes(), -1);
            Stat stat = new Stat();
            System.out.println(new String(zooKeeper.getData("/root", false, stat)));

            zooKeeper.create("/root/child", "child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.delete("/root/child", -1);
            zooKeeper.delete("/root", -1);

        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public static class ZkWatcher implements Watcher {

        private ZooKeeper zooKeeper;
        private String path;

        public ZkWatcher(ZooKeeper zooKeeper, String path) {
            this.zooKeeper = zooKeeper;
            this.path = path;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("Watcher: " + watchedEvent.getType());
        }
    }
}
