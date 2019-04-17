package com.boat.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTool {
    public int checkEmailForm(String email){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return 1;
        }
        else {
            return -1;
        }
    }
    public  String getCode(){

        String sum = "";
        for(int i = 0 ; i < 6; i ++){
            String num = (int)(Math.random() * 9.9) + "";
            sum += num;
        }
        return sum ;
    }
}
