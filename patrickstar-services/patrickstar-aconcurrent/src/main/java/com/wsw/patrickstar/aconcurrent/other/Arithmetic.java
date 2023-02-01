package com.wsw.patrickstar.aconcurrent.other;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author WangSongWen
 * @Date: Created in 9:14 2021/1/6
 * @Description:
 */
public class Arithmetic {
    public String arithmeticContinuous(String[] operate, String... Field) {
        if (operate == null || operate.length == 0 || Field == null || Field.length == 0) {
            return null;
        }
        List<String> expression = new ArrayList<>();
        int num = 0;
        for (String field : Field) {
            if (field == null || field.equals("")) {
                throw new ArithmeticException();
            }
            expression.add(field);
            if (num < operate.length) {
                String s = operate[num];
                expression.add(s);
                num++;
            }
        }
        String result = null;
        try {
            result = eval(expression);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String eval(List<String> expression) {
        return arithmetic(convert(expression));
    }

    private String arithmetic(List<String> expression) {
        for (int i = 0; i < expression.size(); i++) {
            String tempExpression = expression.get(i);
            if (Operate.isOperate(tempExpression)) {
                double d1 = Double.parseDouble(expression.get(i - 2));
                double d2 = Double.parseDouble(expression.get(i - 1));
                String resultNum = null;
                BigDecimal num1 = new BigDecimal(d1);
                BigDecimal num2 = new BigDecimal(d2);
                Operate operate = Operate.value(tempExpression);
                switch (operate) {
                    case ADD:
                        resultNum = num1.add(num2).toString();
                        break;
                    case DECREASE:
                        resultNum = num1.subtract(num2).toString();
                        break;
                    case RIDE:
                        resultNum = num1.multiply(num2).toString();
                        break;
                    case DIVIDE:
                        resultNum = num1.divide(num2, 10, BigDecimal.ROUND_DOWN).toString();
                        break;
                    default:
                        break;
                }
                expression.set(i, resultNum);
                expression.remove(i - 1);
                expression.remove(i - 2);
                break;
            }
        }
        if (expression.size() == 1) {
            return expression.get(0);
        }
        return arithmetic(expression);
    }

    private List<String> convert(List<String> expression) {
        List<String> newExpression = new ArrayList<>();
        Stack<String> expressionStack = new Stack<>();
        for (String exp : expression) {
            if (Operate.isOperate(exp)) {
                if (!expressionStack.isEmpty()) {
                    Operate operate1 = Operate.value(expressionStack.peek());
                    Operate operate2 = Operate.value(exp);
                    if (Operate.compareTo(operate1, operate2)) {
                        newExpression.add(expressionStack.pop());
                    }
                }
                expressionStack.push(exp);
            } else {
                newExpression.add(exp);
            }
        }
        while (!expressionStack.isEmpty()) {
            newExpression.add(expressionStack.pop());
        }
        return newExpression;
    }

    public enum Operate {
        ADD('+', 0),
        DECREASE('-', 0),
        RIDE('*', 1),
        DIVIDE('/', 1);

        private final char symbol;
        private final int level;

        Operate(char symbol, int level) {
            this.symbol = symbol;
            this.level = level;
        }

        public char getSymbol() {
            return symbol;
        }

        public int getLevel() {
            return level;
        }

        public static boolean compareTo(Operate operate1, Operate operate2) {
            return operate1.level >= operate2.level;
        }

        private static boolean isOperate(String expression) {
            return expression.equals("+") || expression.equals("-") || expression.equals("*") || expression.equals("/");
        }

        public static Operate value(String symbol) {
            if (symbol == null || symbol.length() > 1) {
                throw new IllegalArgumentException(symbol);
            }
            return value(symbol.charAt(0));
        }

        public static Operate value(char symbol) {
            for (Operate oper : values()) {
                if (symbol == oper.symbol) {
                    return oper;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        String result = arithmetic.arithmeticContinuous(new String[]{"+", "-", "*", "/"}, "1", "-2", "-3", "-4", "-2");
        System.out.println(result);
    }
}
