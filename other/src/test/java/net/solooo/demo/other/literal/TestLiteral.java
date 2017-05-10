package net.solooo.demo.other.literal;

import org.junit.Test;

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
    }
}
