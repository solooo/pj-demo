package net.solooo.demo.other.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/26
 */
public class QueueTest {

    @Test
    public void queueTest() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(3);
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            queue.put(i + "test");
        }
        System.out.println(queue.size());
    }
}
