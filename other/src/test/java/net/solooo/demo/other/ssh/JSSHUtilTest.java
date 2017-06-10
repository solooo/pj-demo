package net.solooo.demo.other.ssh;

import com.jcraft.jsch.Session;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/10
 */
public class JSSHUtilTest {
    @Test
    public void execCmd() throws Exception {

        Long startTime = 1497097349L;
        Long endTime = 1497097409L;
        String start = "1497097349";
        String end = "1497097409";
        byte[] bytes = startTime.toString().getBytes();
        byte[] bytes1 = start.trim().getBytes();
        System.out.println(new String(bytes, "iso-8859-1"));

        long b = Calendar.getInstance().getTimeInMillis();
        endTime = b / 1000;
        startTime = (endTime - 60);



        System.out.println(startTime);
        System.out.println(String.valueOf(endTime));
        String a = "rrdtool fetch /var/lib/ganglia/rrds/clusterHDC/__SummaryInfo__/cpu_num.rrd  AVERAGE  --start {0} --end {1}";

        String format1 = MessageFormat.format(a,
                new String(startTime.toString().getBytes(), "utf-8"),
                new String(endTime.toString().getBytes(), "utf-8"));

        Session session = JSSHUtil.connect("192.168.1.6", "root", "123456");
        String s = JSSHUtil.execCmd(session, format1);
        System.out.println(s);
    }

}