package com.hrblizz.qa.pages;

public class MyOwn {
    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(s.contains("India"));
        //div[contains(@class,'day-cell') and contains(text(),23)]
        String m= "23";
        System.out.println("//div[contains(@class,'day-cell') and contains(text(),"+m+")]");
    }
}
