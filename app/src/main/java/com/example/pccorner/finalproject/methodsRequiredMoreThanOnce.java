package com.example.pccorner.finalproject;

import java.util.Random;

public class methodsRequiredMoreThanOnce {
    public static String generatePassword(){
        String pass="";
        String ALLPossible="aAbBEeRrTtYyUu$%&#*123896574JjKkLlMmNnBbVvCcXxZz";
        int index=0;
        for(int i=0;i<6;i++){
            index=new Random().nextInt(ALLPossible.length());
            pass+=ALLPossible.charAt(index);
        }

        return pass;
    }
    public static boolean isCorrectName(String name){
        if(name.length()>3){
            return false;
        }
        for(int i=0;i<name.length();i++){
            if(!Character.isAlphabetic(name.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static boolean isNumber(String number){
        for(int i=0;i<number.length();i++){
            if(!Character.isDigit(number.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
