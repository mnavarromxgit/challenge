package com.exercises;

public class Anagram {
    public static void main (String [] args) {
        Anagram anagram = new Anagram();
        System.out.println("Running Test 1: heart/earth " + anagram.isAnagram("heart","earth"));
        System.out.println("Running Test 2: star/rats " + anagram.isAnagram("star","rats"));
        System.out.println("Running Test 3: I am Lord Voldemort/Tom Marvolo Riddle " +
                anagram.isAnagram("I am Lord Voldemort","Tom Marvolo Riddle"));

    }

    private boolean isAnagram(String strOne, String strTwo) {
        String sortedStrOne = getSortedStr(strOne);
        String sortedStrTwo = getSortedStr(strTwo);
        return sortedStrOne.equals(sortedStrTwo);

    }

    private String getSortedStr(String str) {
        return str.toLowerCase().replaceAll("\\s", "")
                .chars().sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


}
