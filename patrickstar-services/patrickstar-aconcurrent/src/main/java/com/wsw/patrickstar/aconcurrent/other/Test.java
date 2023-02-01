package com.wsw.patrickstar.aconcurrent.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author WangSongWen
 * @Date: Created in 16:52 2020/12/31
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String string = "-1530900000000+44700000000/-3100000000+-138700000000*-62600000000--1186200000000--77100000000";
        List<String> result = expr2Infix(string);
        System.out.println(result);
    }

    private static List<String> expr2Infix(String expr) {
        Objects.requireNonNull(expr);
        Pattern pattern_expr = Pattern.compile("(\\(|\\)|\\+|\\-|\\*|/|\\d+(\\.\\d+)?)");
        Matcher matcher = pattern_expr.matcher(expr);
        List<String> exprs = new ArrayList<>();
        while (matcher.find()) {
            exprs.add(matcher.group(1));
        }
        List<String> newExprs = new ArrayList<>();
        for (int i = 0; i < exprs.size(); i++) {
            String now = exprs.get(i);
            if (i == 0 && now.equals("-")) {
                String next = exprs.get(i + 1);
                newExprs.add(now + next);
                i++;
            } else if (now.equals("-") && (exprs.get(i - 1).equals("+") || exprs.get(i - 1).equals("-")
                    || exprs.get(i - 1).equals("*") || exprs.get(i - 1).equals("/"))) {
                String next = exprs.get(i + 1);
                newExprs.add(now + next);
                i++;
            } else {
                newExprs.add(now);
            }
        }

        return newExprs;
    }
}
