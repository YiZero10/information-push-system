package com.njupt.system.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:56 AM
 * @signature Do it while you can!
 */
public interface JwtAuthService{

    String GeneratorToken(String Subject, int expireDay);

    JSONObject ParseToken(String token, Integer permission);
}
