package net.solooo.demo.other.literal;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
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

    public void base64Test() {
        String cookie = "eyJhZG1pbiI6dHJ1ZSwiY2FuTG9jayI6ZmFsc2UsImNyZWF0ZVRpbWUiOjE0OTQzMDg2MzQwMDAs\n" +
                "ImN1cnJlbnRMb2dpblRpbWUiOjE0OTU1MjMzODgwNDYsImVtYWlsIjoiaHRAaHRkYXRhY2xvdWQu\n" +
                "Y29tIiwiaWQiOjEsImxvZ2luQ291bnQiOjE3NDgsIm1vbnRoTG9naW5Db3VudCI6MzQzLCJuYW1l\n" +
                "Ijoi57O757uf566h55CG5ZGYIiwicGFzc3dvcmQiOiIxMjM0NTYiLCJyZW1hcmsiOiLotoXnuqfn\n" +
                "rqHnkIblkZjvvIzmraTotKbmiLfkuI3og73liKDpmaTvvIHkuI3og73kv67mlLnvvIEiLCJ1cGRh\n" +
                "dGVUaW1lIjoxNDg5NzQzNjc5MDAwLCJ1cGRhdGVVc2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4i\n" +
                "fQ==";

    }

    @Test
    public void hashTest() {
        System.out.println("peijian".hashCode());
    }

    @Test
    public void sha1Test() {
        System.out.println(getSha1("this is test"));
    }

    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
