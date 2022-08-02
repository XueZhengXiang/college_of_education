package com.xiangge.eduorder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 祥哥
 * @version 1.0
 */
public class OrderNoUtil {
    public static String getOrderNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = dateFormat.format(new Date());
        String res = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            res += random.nextInt(100);
        }
        return newDate + res;
    }
}
