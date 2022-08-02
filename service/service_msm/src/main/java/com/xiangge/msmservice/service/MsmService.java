package com.xiangge.msmservice.service;

import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
public interface MsmService {
    boolean send(Map<String, String> map, String phone);


}
