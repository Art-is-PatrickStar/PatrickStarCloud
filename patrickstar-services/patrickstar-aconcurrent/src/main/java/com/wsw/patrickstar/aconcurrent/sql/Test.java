package com.wsw.patrickstar.aconcurrent.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/2/16 15:37
 */
public class Test {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "select id, title, date from resource_20220101 where media_source in (1, 69) and name like('%wsw%') order by update_time desc, create_time asc";
        System.out.println(test_select_items(sql));
        System.out.println(test_select_table(sql));
        System.out.println(test_select_where(sql));
        System.out.println(test_select_orderby(sql));
    }

    private static List<String> test_select_items(String sql) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectitems = plain.getSelectItems();
        List<String> str_items = new ArrayList<>();
        if (selectitems != null) {
            for (SelectItem selectitem : selectitems) {
                str_items.add(selectitem.toString());
            }
        }
        return str_items;
    }

    private static List<String> test_select_table(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        return tablesNamesFinder.getTableList(selectStatement);
    }

    private static String test_select_where(String sql) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where_expression = plain.getWhere();
        return where_expression.toString();
    }

    private static List<String> test_select_orderby(String sql) throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<OrderByElement> OrderByElements = plain.getOrderByElements();
        List<String> str_orderby = new ArrayList<>();
        if (OrderByElements != null) {
            for (OrderByElement orderByElement : OrderByElements) {
                str_orderby.add(orderByElement.toString());
            }
        }
        return str_orderby;
    }
}
