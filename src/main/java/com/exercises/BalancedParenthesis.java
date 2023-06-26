package com.exercises;

public class BalancedParenthesis {

    public static void main (String [] args) {
        BalancedParenthesis balancedParenthesis = new BalancedParenthesis();
        String[] balancedCases = {"((({{{[[[]]]}}})))", "{{{[][][]}}}", "()(){{{}}}[][]", "()()()()"};
        String[] unbalancedCases = {"(((())})", ")((()))()()", "((()()()))(()"};
        balancedParenthesis.runTestCases(balancedCases);
        balancedParenthesis.runTestCases(unbalancedCases);
    }

    private void runTestCases(String[] testCases) {
        for (String testCase: testCases) {
            System.out.println("String " + testCase + " Is balanced? --->" + isBalanced(testCase));
        }

    }

    private boolean isBalanced(String input) {
        input = input.trim();
        while (input.contains("[]") || input.contains("{}") || input.contains("()")) {
            input = input.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");

        }
        return input.length() == 0;
    }



}

