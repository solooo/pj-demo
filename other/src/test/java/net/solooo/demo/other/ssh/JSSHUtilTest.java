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

        Long startTime;
        Long endTime;

        long b = Calendar.getInstance().getTimeInMillis();
        endTime = b / 1000;
        startTime = (endTime - 60);



        System.out.println(startTime);
        System.out.println(String.valueOf(endTime));
        String a = "rrdtool fetch /var/lib/ganglia/rrds/clusterHDC/__SummaryInfo__/cpu_num.rrd  AVERAGE  --start {0} --end {1}";

        StringBuilder sb = new StringBuilder();
        sb.append("rrdtool fetch /var/lib/ganglia/rrds/clusterHDC/__SummaryInfo__/cpu_num.rrd  AVERAGE  --start ");
        sb.append(startTime);
        sb.append(" --end ");
        sb.append(endTime);

        String format1 = MessageFormat.format(a, startTime.toString(), endTime.toString()
//                new String(startTime.toString().getBytes("iso8859-1")),
//                new String(endTime.toString().getBytes("iso8859-1"))
        );

//        format1 = sb.toString();
        System.out.println(format1);
        Session session = JSSHUtil.connect("192.168.1.6", "root", "123456");
        String s = JSSHUtil.execCmd(session, format1.getBytes("utf-8"));
        System.out.println(s);
    }

}