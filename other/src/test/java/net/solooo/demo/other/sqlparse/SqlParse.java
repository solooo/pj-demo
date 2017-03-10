package net.solooo.demo.other.sqlparse;

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
public class SqlParse {

    @Test
    public void parse() {
        try {
            Statement stmt = CCJSqlParserUtil.parse("sElecT *        FROM          \n\r\t     tab1 where     id =   0 and t=(select name from t) order by name desc");
            if (stmt instanceof Select) {
                System.out.println("select");
            }
            System.out.println(stmt.toString());
        } catch (JSQLParserException e) {
            e.printStackTrace();
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
        String sql3 = "use ht_test; CREATE OR REPLACE PACKAGE BODY testpackage12 \n" +
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

        List<String> sqlList = new ArrayList<>();
        List<SearchBean> searchList = new ArrayList<>();
        String execSql = "";
        String sqls = sql3.replaceAll("\\s+", " ").trim();





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

    @Test
    public void parserTest() {
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

        String sql3 = "use ht_test; CREATE OR REPLACE PACKAGE BODY testpackage12 \n" +
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
        this.parser(sql);
    }

    Pattern useDbPattern = Pattern.compile("^use (.*)");
    Pattern setPattern = Pattern.compile("^set (.*)");
    Pattern selectPattern = Pattern.compile("^select (.*)");

    private void parser(String str) {

        String[] strs = str.replaceAll("\\s+", " ").trim().split("\\s+");

        for (int i = 0; i < strs.length; i++) {

            System.out.println(strs[i]);

        }

        str = str.replaceAll("\\s+", " ").trim();
        Matcher useMatcher = useDbPattern.matcher(str);
        Matcher setMatch = setPattern.matcher(str);
        if (useMatcher.find()) {
            int i = str.indexOf(";");
            System.out.println(str.substring(0, i));
            str = str.substring(i + 1, str.length()).trim();
        } else if (setMatch.find()) {
            int i = str.indexOf(";");
            System.out.println(str.substring(0, i));
            str = str.substring(i + 1, str.length()).trim();
        }



    }

    Pattern pattern = Pattern.compile("[^A-Za-z]");
    @Test
    public void iterStr() throws Exception {

        String str1 = "   use    ht_test  ; set transaction.type=inceptor; set plsqlUseSlash true;\n " +
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

        String str2 = "CREATE OR REPLACE PACKAGE BODY testpackage12 \n" +
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
                "\tEND;\n";

        String str3 = "\tCREATE OR REPLACE PACKAGE testpackage12 \n" +
                "IS\n" +
                "TYPE record_type IS RECORD(key int,value string); \n" +
                "TYPE cur IS REF CURSOR; \n" +
                "PROCEDURE testprocedure(); \n" +
                "END;\n";


        String str4 = "\n use ht_test;select * from ht_test.tt;";

        String str5 = "\nbegin\n" +
                "\t\texample_proc()\n" +
                "    end;";

        String str6 = "\n create table tt(id int);";

        String str7 = "begin\n" +
                "testLast.testprocedure1();\n" +
                "end;";

        String str8 = "\nCREATE OR REPLACE PACKAGE testLast2\n" +
                "IS\n" +
                "PROCEDURE testprocedure1(); \n" +
                "PROCEDURE test_procedure1(test_name string);\n" +
                "END;\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE PACKAGE BODY testLast2\n" +
                "IS\n" +
                "PROCEDURE  testprocedure1() is\n" +
                "BEGIN\n" +
                "dbms_output.put_line('是个好同学'); \n" +
                "dbms_output.put_line('this is test'); \n" +
                "END;\n" +
                "\n" +
                "PROCEDURE  test_procedure1(test_name string) is\n" +
                "BEGIN\n" +
                "dbms_output.put_line('test'); \n" +
                "END;\n" +
                "END;";

        String str9 = "\nDECLARE\n" +
                "CURSOR cur(tacc_num string) IS SELECT * FROM ppap WHERE key=tacc_num;\n" +
                "transactions_type ppap%rowtype;\n" +
                "BEGIN\n" +
                "OPEN cur('1');\n" +
                "LOOP\n" +
                "FETCH cur INTO transactions_type;\n" +
                "EXIT WHEN cur%NOTFOUND;\n" +
                "DBMS_OUTPUT.PUT_LINE(transactions_type.key);\n" +
                "END LOOP;\n" +
                "CLOSE cur;\n" +
                "EXCEPTION\n" +
                "WHEN OTHERS THEN\n" +
                "DBMS_OUTPUT.PUT_LINE('Something unexpected happened!!');\n" +
                "CLOSE cur;\n" +
                "END;";

        String str = str1 + str2 + str3 + str4 + str5 + str6 + str8 + str9;
        List<String> sqlList = parserSql(str);
        for (String s : sqlList) {
            System.out.println(s);
        }
    }

    List<String> sqlKeys = new ArrayList<>();

    /**
     * 找到语句开始字符，根据语句类型判断结束字符串，遍历所有单词，直至找到结束点或语句结尾，以此分割sql
     * @param str
     * @return
     * @throws Exception
     */
    private List<String> parserSql(String str) throws Exception {

        sqlKeys.add("create");
        sqlKeys.add("select");
        sqlKeys.add("alter");
        sqlKeys.add("insert");
        sqlKeys.add("update");
        sqlKeys.add("drop");
        sqlKeys.add("merge");
        sqlKeys.add("truncate");
        sqlKeys.add("use");
        sqlKeys.add("set");
        sqlKeys.add("begin");
        sqlKeys.add("declare");

        String sql = str.toLowerCase().replaceAll("\\s+", " ").replaceAll(";", " ; ").trim();
        String[] strs = sql.split("\\s+");

        StringBuilder sb = new StringBuilder();
        List<String> sqlList = new ArrayList<>();
        String startStr = null, endStr = null, preStr = null;
        int stack = 0;
        for (int i = 0; i < strs.length; i++) {
            boolean clearPreStr = false; // 是否将 preStr 设为null
            String s = strs[i];
            if (sb.length() == 0 && pattern.matcher(s).find()) {
                continue;
            }
            sb.append(s).append(" ");

            if (startStr == null && !sqlKeys.contains(s)) {
                int len = strs.length;
                String errorSql = "";
                while (++i < len) {
                    errorSql += strs[i];
                }
                throw new Exception("语法错误" + errorSql);
            }

            if (startStr == null && sqlKeys.contains(s)) {
                startStr = s;
                endStr = "begin".equals(s) || "declare".equals(s) ? "end" : ";"; // begin开关语句以end结尾
                stack++;
            } else if ("create".equals(startStr) && ("procedure".equals(s) || "package".equals(s)) ) {
                // 创建存储过程或包时，结束符以"end"终止
                String createStatement = sb.toString().trim();
                if (("create or replace " + s).equals(createStatement)
                        || ("create " + s).equals(createStatement)) {
                    endStr = "end";
                    if ("procedure".equals(s)) {
                        stack--;
                    }
                }

            } else if (s.equals("begin") || (!"end".equals(preStr) && "loop".equals(s))) {
                // begin/loop 关键字结束符都为 end, 所以需要+1
                stack++;
            } else if ("transaction".equals(s) && "begin".equals(preStr)) {
                stack--;
            } else if (s.equals(endStr) || i == strs.length - 1) {
                stack--;
                if (stack == 0) {
                    startStr = null;
                    clearPreStr = true;
                    if (!";".equals(endStr)) {
                        sb.append(";");
                    }
                    sqlList.add(sb.toString());
                    sb.setLength(0);
                }
            }
            // 设置上一个关键字内容
            if (clearPreStr) {
                preStr = null;
            } else {
                preStr = s;
            }
        }

        return sqlList;

    }

    private List<ExecBean> packSql(List<String> sqlList) {

        List<String> confSql = new ArrayList<>();
        for (String sql : sqlList) {
            if (sql.startsWith("use")) {
                continue;
            } else if (sql.startsWith("set")) {
                confSql.add(sql);
            }
        }

        return null;
    }


    private class ExecBean {
        private List<String> sql;

        private List<SearchBean> searchBeens;

        public ExecBean(List<String> sql, List<SearchBean> searchBeens) {
            this.sql = sql;
            this.searchBeens = searchBeens;
        }

        public List<String> getSql() {
            return sql;
        }

        public void setSql(List<String> sql) {
            this.sql = sql;
        }

        public List<SearchBean> getSearchBeens() {
            return searchBeens;
        }

        public void setSearchBeens(List<SearchBean> searchBeens) {
            this.searchBeens = searchBeens;
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
}
