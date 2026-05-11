/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.exception.NoLoginException
 *  com.bilibili.myblogbackend.util.JWTUtils
 *  io.jsonwebtoken.Claims
 *  io.jsonwebtoken.Jwts
 *  io.jsonwebtoken.Jwts$SIG
 *  io.jsonwebtoken.security.Keys
 *  io.jsonwebtoken.security.SecureDigestAlgorithm
 */
package com.bilibili.myblogbackend.util;

import com.bilibili.myblogbackend.exception.NoLoginException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;

public class JWTUtils {
    private static final String SECRET = "cpy&lbyjdkslahgdiosajhrfioenwaio;bfgjkdshgiosadjf";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor((byte[])"cpy&lbyjdkslahgdiosajhrfioenwaio;bfgjkdshgiosadjf".getBytes());
    private static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    public static String createToken(Integer id) {
        return Jwts.builder().signWith((Key)SECRET_KEY).claim("id", (Object)id).expiration(new Date(System.currentTimeMillis() + 86400000L)).compact();
    }

    public static Integer parseToken(String token) throws Exception {
        if (token == null) {
            throw new NoLoginException("\u8bf7\u5148\u767b\u5f55");
        }
        return Integer.parseInt(String.valueOf(((Claims)Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims((CharSequence)token).getPayload()).get((Object)"id")));
    }
}

