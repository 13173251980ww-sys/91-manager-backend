package com.amadeus;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("name","AmaneKanata");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "QW1hbmVLYW5hdGEK")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiQW1hbmVLYW5hdGEiLCJpZCI6MSwiZXhwIjoxNzY3NzA4Mjg1fQ.lw9hO_QZx_oT5ppAMXQLNKesRzif7qjmD1jcdfsq4PE";
        Claims claims = Jwts.parser()
                .setSigningKey("QW1hbmVLYW5hdGEK")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
    }
