package com.pilot.boot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * token
 *
 * @author ezuy
 * @date 21/1/20 21:40
 */
public class TokenUtil {

    /**
     * valid time
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * key
     */
    private static final String TOKEN_SECRET = "demo";

    /**
     *
     * signature
     * @param name
     * @return
     */
    public static String signature(String name) {

        String token = null;

        try {

            //1.produce a expire
            Date expire = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            //2.produce token
            token = JWT.create().withIssuer("auth0").withClaim("name", name).withExpiresAt(expire).sign(Algorithm.HMAC256(TOKEN_SECRET));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
