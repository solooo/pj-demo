package net.solooo.demo.other.regex;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.junit.Test;

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
    public void getText() throws JSQLParserException {
        Pattern useDbPattern = Pattern.compile("use(.*)");
        String sql = "uSE Ht_test    \n\r;  \n    seLEcT  \n *  \n\r  \t FrOm     pj p WhEre" +
                " id=0 and name=(selECt t FROm t0 t wheRE t.id=p.tid)     Group bY name  ;" +
                "select t1,   t2,    t3    , t5 from     t0 ;";
        String[] sqls = sql.split(";");
        List<String> list = new ArrayList<>();
        if (sqls.length > 0) {
            for (String s : sqls) {
                s = s.trim().toLowerCase();
                Matcher matcher = useDbPattern.matcher(s);
                if (matcher.find()) {
                    list.add(s.replaceAll("\\s+", " "));
                } else {
                    Statement stmt = CCJSqlParserUtil.parse(s);
                    list.add(stmt.toString());
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append(";");
        }
        System.out.println(sb.toString());

    }
}
