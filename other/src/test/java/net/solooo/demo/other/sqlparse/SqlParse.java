package net.solooo.demo.other.sqlparse;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.Test;

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
}
