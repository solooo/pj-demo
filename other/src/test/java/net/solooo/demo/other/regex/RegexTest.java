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
                    System.out.println("dbname : " + matcher.group(1) + "  " + matcher.group());
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
    public void buildSql() {
        String sql = "   use    ht_test  ; set transaction.type=inceptor; set plsqlUseSlash true;\n " +
                "create or replace procedure example_proc()\n" +
                "\tis\n" +
                "\tdeclare\n" +
                "\tcnt int:=0;\n" +
                "\tBEGIN\n" +
                "\tbegin transaction;\n" +
                "\tupdate kx.cbcbcbc set value='gaojianxun' where key=1;\n" +
                "\tcommit;\n" +
                "\tbegin transaction;\n" +
                "\tupdate kx.cbcbcbc set value='gaojianxun1' where key=1;\n" +
                "\tcommit;\n" +
                "\tEXCEPTION\n" +
                "\tWHEN others THEN\n" +
                "\trollback;\n" +
                "\tdbms_output.put_line('action faild');\n" +
                "\tEND;";
        String sql2 = "use    ht_test  ;select * from pj;select * from tt";
        String sql3 = "CREATE OR REPLACE PACKAGE BODY testpackage12 \n" +
                "\tIS\n" +
                "\tv_record_type record_type; \n" +
                "\tsqlcur cur; \n" +
                "\tPROCEDURE testprocedure() IS \n" +
                "\tBEGIN\n" +
                "\t\tOPEN sqlcur FOR\n" +
                "\t\tSELECT key,value\n" +
                "\t\tFROM cbcbcbc\n" +
                "\t\tWHERE key=1; \n" +
                "\t\tLOOP\n" +
                "\t\tFETCH sqlcur INTO v_record_type; \n" +
                "\t\tEXIT WHEN sqlcur%notfound ; \n" +
                "\t\tdbms_output.put_line(v_record_type.key||' '||v_record_type.value); \n" +
                "\t\tEND LOOP;\n" +
                "\t\tCLOSE sqlcur; \n" +
                "\tEND;\n" +
                "\tEND;";
        String databaseName = "ht_test";
        int start = 0;
        int pageSize = 10;

        Pattern useDbPattern = Pattern.compile("^use (.*)");
        Pattern setPattern = Pattern.compile("^set (.*)");
        Pattern selectPattern = Pattern.compile("^select (.*)");

        List<Object> sqlList = new ArrayList<>();
        String useStatement;
        String execSql = "";
        String[] sqls = sql3.split(";");
        for (String s : sqls) {
            String str = s.replaceAll("\\s+", " ").trim();
            Matcher useMatcher = useDbPattern.matcher(str);
            Matcher setMatcher = setPattern.matcher(str);
            Matcher selectMatcher = selectPattern.matcher(str);

            if (useMatcher.find()) { // use 语句处理
                useStatement = useMatcher.group();
                sqlList.add(useStatement);
            } else if (setMatcher.find()) { // set 属性设置处理
                sqlList.add(setMatcher.group());
            } else if (selectMatcher.find()) { // select 查询语句
                SearchBean searchBean = new SearchBean(databaseName, selectMatcher.group(), start, pageSize);
                sqlList.add(searchBean);
            } else { // create 创建语句
                execSql += str + "; ";
            }
        }
        sqlList.add(execSql);

        for (Object obj : sqlList) {
            if (obj instanceof String) {
                System.out.println(obj);
            } else if (obj instanceof SearchBean) {
                System.out.println("-----------------------------");
                System.out.println("sql:      "+ ((SearchBean) obj).getSql());
                System.out.println("countSql: "+ ((SearchBean) obj).getCountSql());
                System.out.println("-----------------------------");
            }
        }
    }

    /**
     * 保存sql处理结果
     */
    private class SearchBean {

        private int start;

        private int pageSize;

        private String databaseName;

        private String sql;

        public SearchBean(String databaseName, String sql, int start, int pageSize) {
            this.databaseName = databaseName;
            this.sql = sql;
            this.start = start;
            this.pageSize = pageSize;
        }

        public String getCountSql() {

            return MessageFormat
                    .format("USE {0};SELECT COUNT(1) FROM ({1})", databaseName, sql);
        }

        public String getSql() {
            return MessageFormat
                    .format("USE {0};SELECT * FROM ({1}) LIMIT {2},{3}",
                            databaseName, sql, start, pageSize);
        }

    }

    @Test
    public void messageFormat() {
        System.out.println(MessageFormat.format("select count(1) from ({0})", "select * from pj"));
    }
}
