package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author alone95
 * @date 2017/10/27
 */
public class LevelUtil {

    public final static  String SEPARATOR = ".";

    public final static  String ROOT = "0";

    // 0
    // 0.1
    // 0.1.3
    // 0.2

    public static String calculateLevel(String parentLevel, int parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
