package com.tsystems.javaschool.tasks.calculator;

import sun.awt.SunToolkit;

import java.text.DecimalFormat;
import java.util.Objects;

public class Calculator {
    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        try {
            if (statement.contains("..") || statement.contains("++") || statement.contains("**") || statement.contains("--")
                    || statement.contains("//") || statement.contains(",") || statement.length() < 3) {
                return null;
            }

            statement = statement.replaceAll("-", "+-");
            StringBuilder a = new StringBuilder(statement);
            String strAnswer = operateBracket(a, a.indexOf("" + '('));

            if (strAnswer.equals("Infinity") || strAnswer == null) {
                return null;
            }
            if (strAnswer.lastIndexOf("0") != strAnswer.length() - 1) {
                int temp = strAnswer.length() - strAnswer.indexOf(".");
                if (temp >= 3) {
                    return strAnswer.substring(0, strAnswer.indexOf(".") + temp);
                } else return strAnswer;
            } else {
                return strAnswer.substring(0, strAnswer.indexOf("."));
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String operateBracket(StringBuilder s, int startIndex) {
        if (startIndex == -1) {
            return (operate(s.toString()));
        } else {
            int k = 1;
            int openParentheses = 0;
            int closeParentheses = 0;
            char temp1;
            char temp2;
            for (int j = 0; j < s.length(); j++) {
                temp1 = s.charAt(j);
                if (temp1 == ('(')) {
                    openParentheses++;
                }
                temp2 = s.charAt(j);
                if (temp2 == (')')) {
                    closeParentheses++;
                }
            }
            if (openParentheses != closeParentheses) {
                return null;
            }
            if ((startIndex + 1) < s.length()) {
                for (int i = startIndex + 1; i < s.length(); i++) {
                    if (s.charAt(i) == '(')
                        k++;
                    else if ((s.charAt(i) == ')')) {
                        if (k == 1) {
                            String newBracket = s.substring(startIndex + 1, i);
                            s.replace(startIndex, i + 1, Objects.requireNonNull(operateBracket(new StringBuilder(newBracket),
                                    newBracket.indexOf("" + '('))));
                        }
                        k--;
                    }
                }
                return operate(s.toString());
            } else return null;
        }
    }

    public static String operate(String expression) {
        String[] num;
        int index;
        char priorityOperator = '/';
        String operators;
        while (!((operators = expression.replaceAll("[^*+/]", "")).isEmpty())) {
            if ((index = operators.indexOf('/')) == -1) {        // choose priority of operations
                priorityOperator = '*';
                if ((index = operators.indexOf('*')) == -1) {
                    priorityOperator = operators.charAt(0);
                    index = operators.indexOf(priorityOperator);
                }
            }
            num = expression.split("[^0-9\\-.]");
            expression = expression.replaceFirst(num[index] + "\\" + priorityOperator + num[index + 1],
                    Objects.requireNonNull(calc(num[index], num[index + 1], "" + priorityOperator)));
        }
        return expression;
    }

    public static String calc(String a, String b, String operator) {
        switch (operator) {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b) + "";
            case "-":
                return Double.parseDouble(a) - Double.parseDouble(b) + "";
            case "*":
                return Double.parseDouble(a) * Double.parseDouble(b) + "";
            case "/":
                return Double.parseDouble(a) / Double.parseDouble(b) + "";
        }
        return null;
    }
}