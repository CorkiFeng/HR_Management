package com.controller.utils;

import java.util.Random;

public class RandomCode {
    public static String random_code(int number){
        String str = "";
        Random random = new Random();
        for (int i = 0;i < number;i++){
            str += random.nextInt(10) + "";
        }
        return str;
    }

}
