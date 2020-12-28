package com.sebc.api.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sebc.api.VO.CurrentUser;
import com.sebc.api.util.LogUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            //类级别的设置，JsonInclude.Include.NON_EMPTY标识只有非NULL的值才会被纳入json string之中，其余的都将被忽略
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            //禁止使用出现未知属性之时，抛出异常
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            //转化后的json的key命名格式
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

    public static final String TOKEN_HEADER = "Authorization";
    // Token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 签名主题
    public static final String SUBJECT = "piconjo";
    // 过期时间
    private static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
    // 应用密钥
    private static final String APPSECRET_KEY = "piconjo_secret";
    // 角色权限声明
    private static final String ROLE_CLAIMS = "role";

    // 当前用户信息
    private static final String CURRENT_USER_INFO = "currentUser";

    /**
     * 生成Token
     */
    public static String createToken(String username, String role, CurrentUser currentUser) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);
        try {
            map.put(CURRENT_USER_INFO, OBJECT_MAPPER.writeValueAsString(currentUser));
        } catch (JsonProcessingException e) {
            LogUtil.error("格式转换失败", e);
        }

        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 校验Token
     */
    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Token中获取username
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 从Token中获取用户角色
     */
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

    /**
     * @param token
     * @return
     */
    public static CurrentUser getCurrentUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        try {
            return OBJECT_MAPPER.readValue(claims.get(CURRENT_USER_INFO).toString(), CurrentUser.class);
        } catch (IOException e) {
            LogUtil.error("从token中获取当前用户信息失败", e);
        }
        return new CurrentUser();
    }
}