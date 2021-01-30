package com.pilot.boot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * token
 *
 * @author ezuy
 * @date 21/1/20 21:40
 */
public class TokenUtil {

    /**
     * key
     */
    private static final String TOKEN_SECRET = "userId";

    /**
     * signature
     *
     * @param userId
     * @return
     */
    public static String signature(Long userId) {

        String token = null;

        try {

            //1.produce token
            token = JWT.create()
                    .withIssuer("auth0")
                    //name
                    .withClaim("userId", userId)
                    // token password algorithm
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }


}
