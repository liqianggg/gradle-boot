package com.liqiang.gradle.gradleboot.util.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Man {

    public static void main(String[] args) {
//        WangPo wangPo = new WangPo(new LiuShiShi());
//        wangPo.flayEye();
//        wangPo.eat();

    }
    public static boolean validatePin(String pin) {
        // Your code here...
        if(pin.length()==4||pin.length()==6){
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(pin);
            if( !isNum.matches() ){
                return false;
            }
            return true;
        }
        return false;
    }

}
