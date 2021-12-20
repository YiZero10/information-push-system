package com.njupt.system.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.njupt.system.enums.Permission;
import com.njupt.system.exception.CustomError;
import com.njupt.system.exception.LocalRuntimeException;
import com.njupt.system.mapper.AdminMapper;
import com.njupt.system.mapper.UserMapper;
import com.njupt.system.model.User;
import com.njupt.system.service.JwtAuthService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * @author Elaine Huang
 * @date 2021/12/20 11:56 AM
 * @signature Do it while you can!
 */
@Component
public class JwtAuthServiceImpl implements JwtAuthService {
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Logger logger = LoggerFactory.getLogger(JwtAuthService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String GeneratorToken(Integer subject, int expireDay) {
        Instant ttl = Instant.now().plusSeconds((long) expireDay * 24 * 3600);
        return Jwts.builder().setSubject(String.valueOf(subject)).setExpiration(Date.from(ttl)).signWith(KEY).compact();
    }

    @Override
    public JSONObject ParseToken(String token, int permission) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            if (permission <= Permission.COMMON.getCode()){
               return (JSONObject) JSONObject.toJSON(adminMapper.selectByPrimaryKey(Integer.valueOf(claims.getSubject())));
            }
            return (JSONObject) JSONObject.toJSON(userMapper.selectByPrimaryKey(Integer.valueOf(claims.getSubject())));
        }catch (ExpiredJwtException e){
            throw new LocalRuntimeException(CustomError.TOKEN_EXPIRED, "Token过期");
        }catch (SignatureException e){
            throw new LocalRuntimeException(CustomError.TOKEN_KEY_NOT_MATCH, "密钥不匹配");
        }catch (JwtException e){
            logger.info("Token解析异常: " + token + e);
            throw new LocalRuntimeException(CustomError.TOKEN_PARSE_FAIL, "Token解析异常");
        }
    }
}
