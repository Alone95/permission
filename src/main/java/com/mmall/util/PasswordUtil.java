package com.mmall.util;

import java.util.Date;
import java.util.Random;

/**
 * @author alone95
 * @date 2017/10/30
 */
public class PasswordUtil {

    public final static  String[] word={
            "a","b","c","d","e","f","g",
            "h","j","k","m","n","p","q",
            "r","s","t","u","v","w","x","y","z",
            "A","B","C","D","E","F","G",
            "H","J","K","M","N","P","Q",
            "R","S","T","U","V","W","X","Y","Z"
    };

    public final static String[] num={
            "1","2","3","4","5","6","7","8","9"
    };


    public static String randomPassword(){
        StringBuffer stringBuffer =new StringBuffer();
        Random random =new Random(new Date().getTime());
        boolean flag =false;//判断选取数字还是字母 默认字母
        int length= random.nextInt(3)+8;
        for (int i=0;i<length;i++){
            if (flag){
                stringBuffer.append(num[random.nextInt(num.length)]);
            }else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag=!flag;
        }
        return stringBuffer.toString();
    }

    //测试方法
    public static void main(String[]args) throws  Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println(randomPassword());
            Thread.sleep(100);
        }
    }
}
