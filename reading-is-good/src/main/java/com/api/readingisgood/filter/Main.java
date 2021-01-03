package com.api.readingisgood.filter;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*int counter = 0;
        String input = "0110111";
        String[] a = input.split("0");
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i <= a[j].length()) {
                  counter += nPr(a[j].length(), i);
                }
            }
        }
        System.out.println(counter);*/
        String[] nums = new String[] {"1","9","10"};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static int fact(int n)
    {
        if (n <= 1)
            return 1;
        return n * fact(n - 1);
    }

    static int nPr(int n, int r)
    {
        return fact(n) / (fact(r) *
            fact(n - r));
    }
}
