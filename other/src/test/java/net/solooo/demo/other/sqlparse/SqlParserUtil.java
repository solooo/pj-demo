package net.solooo.demo.other.sqlparse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 分割sql
 */
public class SqlParserUtil {

    private static final Pattern pattern = Pattern.compile("[^A-Za-z]");

    private static final List<String> sqlKeys = new ArrayList<>();

    static  {
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
    }

    /**
     * 找到语句开始字符，根据语句类型判断结束字符串，遍历所有单词，直至找到结束点或语句结尾，以此分割sql
     * @param str
     * @return
     * @throws Exception
     */
    public static List<String> parserSql(String str) throws Exception {

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
                    if (!";".equals(endStr) && !";".equals(s)) {
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

    public static void main(String[] args) throws Exception {
        String sql = "select * from tt; \n select * from t2";
        List<String> sqlList = SqlParserUtil.parserSql(sql);
        for (String s : sqlList) {
            System.out.println(s);
        }
    }
}
