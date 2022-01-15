package com.tsystems.javaschool.tasks.calculator;

import sun.awt.SunToolkit;

import java.text.DecimalFormat;
import java.util.Objects;

public class Calculator {
    // test №17: In my opinion in this situation my answer is correct, because people can write statement "4-(-3)", like this "4--3".
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
        System.out.println(c.evaluate("7*6/2+8")); // Result: 29
        System.out.println(c.evaluate("(1+3.8)*4-5/3")); // Result: 17.53333333333333
        System.out.println(c.evaluate("-12)1//(")); // Result: null
        System.out.println(c.evaluate("1...1")); // Result: null
        System.out.println(c.evaluate("10/(5-5)")); // Result: null
    }

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
            StringBuilder a = new StringBuilder(minusToTilda(statement));
            String strAnswer = operateBracket(a, a.indexOf("" + '('));
            if (strAnswer.equals("Infinity")) {
                return null;
            } else {
                if (strAnswer != null) {
                    if (strAnswer.lastIndexOf("0") != strAnswer.length() - 1) {
                        if (strAnswer.length() - strAnswer.indexOf(".") >= 3) {
                            return strAnswer.substring(0, strAnswer.indexOf(".") + (strAnswer.length() - strAnswer.indexOf(".")));
                        } else return strAnswer;
                    } else {
                        return strAnswer.substring(0, strAnswer.indexOf("."));
                    }
                } else return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static String minusToTilda(String s) {
        if (s.length() < 3) {
            return null;
        } else {
            StringBuilder result = new StringBuilder("" + s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                if ((s.charAt(i) == '-') && ("+-*(/~".indexOf(s.charAt(i - 1)) == -1))   // If next symbol isn't operator, we change "-", to "~"
                    result.append("" + '~');
                else result.append(s.charAt(i));
            }
            return result.toString();
        }
    }

    public static String operateBracket(StringBuilder s, int startIndex) {
        if (s.indexOf("..") != -1 || s.indexOf("++") != -1 || s.indexOf("**") != -1
                || s.indexOf("//") != -1 || s.indexOf(",") != -1 || s.length() < 3) {
            return null;
        } else {
            if (startIndex == -1) {
                return (operate(s.toString()));
            } else {
                int k = 1;
                int openParenthese = 0;
                int closeParenthese = 0;
                char temp1;
                char temp2;
                for (int j = 0; j < s.length(); j++) {
                    temp1 = s.charAt(j);
                    if (temp1 == ('(')) {
                        openParenthese++;
                    }
                    temp2 = s.charAt(j);
                    if (temp2 == (')')) {
                        closeParenthese++;
                    }
                }
                if (openParenthese != closeParenthese) {
                    return null;
                } else {
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

        }
    }

    public static String operate(String expression) {
        String[] num;
        int index;
        char priorityOperator = '/';
        String operators;
        while (!((operators = expression.replaceAll("[^*+/~]", "")).isEmpty())) {
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
            case "~": // To make a difference between negative numbers (for example: -4) and operator ("-"). I change operator to tilda ("~")
                return Double.parseDouble(a) - Double.parseDouble(b) + "";
            case "*":
                return Double.parseDouble(a) * Double.parseDouble(b) + "";
            case "/":
                return Double.parseDouble(a) / Double.parseDouble(b) + "";
        }
        return null;
    }
}


