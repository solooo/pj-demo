package net.solooo.demo.other.literal;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author:Eric
 * Date:2017/5/4
 */
public class TestLiteral {

    @Test
    public void toStringTest() {
        Hello hello = new Hello();
        int i = 5;
        while (i > 0) {
            hello.setName("name" + i);
            System.out.println(hello.toString());
            i--;
        }
    }

    @Test
    public void asciiToString() {
        int a= 97;
        System.out.println((char) a);
    }

    @Test
    public void index() {
        String val = ",index,name";
        System.out.println(val.indexOf("."));
    }

    /**
     * 匹配引号外面的内容
     */
    @Test
    public void regex() {
        String str = "SELECT * from car_data_hbase_index_new_copy where id =  '20170502022403_云BE9213' AND 1=1 AND id = '123ABCS'";
        System.out.println(str);
        Matcher matcher = Pattern.compile("[A-Z](?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)").matcher(str);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group().toLowerCase());
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
        System.out.println(getHdSerialInfo());
    }

    public String getHdSerialInfo() {

        String line = "";
        String HdSerial = "";//记录硬盘序列号

        try {

            Process proces = Runtime.getRuntime().exec("cmd /c dir c:");//获取命令行参数
            BufferedReader buffreader = new BufferedReader(
                    new InputStreamReader(proces.getInputStream()));

            while ((line = buffreader.readLine()) != null) {

                if (line.indexOf("卷的序列号是 ") != -1) {  //读取参数并获取硬盘序列号

                    HdSerial = line.substring(line.indexOf("卷的序列号是 ")
                            + "卷的序列号是 ".length(), line.length());
                    break;
                    // System.out.println(HdSerial);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return HdSerial; // 返回硬盘序列号
    }
}
