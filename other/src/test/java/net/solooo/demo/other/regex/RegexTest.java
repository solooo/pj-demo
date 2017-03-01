package net.solooo.demo.other.regex;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/2/28-028
 * History:
 * his1:
 */
public class RegexTest {

    @Test
    public void test1() {
        String limitRegex = "limit\\s+\\d+\\s*(?:,\\s*\\d+|)";
        Pattern p = Pattern.compile(limitRegex);
        String sql = "select * from pj limit 5  , 56    ";
        System.out.println(sql.split("limit")[0]);
        Matcher matcher = p.matcher(sql);
        if (matcher.find()) {
            System.out.println(matcher.group());
            sql = sql.replaceAll(limitRegex, "");
            System.out.println(sql);
        }

    }

    @Test
    public void getText() throws JSQLParserException {
        Pattern useDbPattern = Pattern.compile("use (.*)");
        String sql = "use   \n\r    ht_test;  \n    seLEcT  \n *  \n\r  \t FrOm     pj p WhEre" +
                " id=0 and name=(selECt t FROm t0 t wheRE t.id=p.tid)     Group bY name  ;" +
                "select t1,   t2,    t3    , t5 from     t0 ;";
        String[] sqls = sql.split(";");
        List<String> list = new ArrayList<>();
        if (sqls.length > 0) {
            for (int i = 0; i < sqls.length; i++) {
                String s = sqls[i];
                s = s.trim().toLowerCase().replaceAll("\\s+", " ");
                Matcher matcher = useDbPattern.matcher(s);
                if (matcher.find()) {
                    list.add(s);
                } else {
                    if (i == 0) {
                        list.add("use ht_test");
                    }
                    Statement stmt = CCJSqlParserUtil.parse(s);
                    if (stmt instanceof Select) {
                        Select select = (Select) stmt;
                        TablesNamesFinder tn = new TablesNamesFinder();
                        List<String> tableList = tn.getTableList(select);

                    }

                    list.add(stmt.toString());

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append("; ");
        }
        System.out.println(sb.toString());

    }

    @Test
    public void messageFormat() {
        System.out.println(MessageFormat.format("select count(1) from ({0})", "select * from pj"));
    }
}
