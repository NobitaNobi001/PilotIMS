package com.pilot.boot.utils;

import java.util.regex.Pattern;

/**
 * param verify
 * @author ezuy
 * @date 21/1/20 22:46
 */
public class ParamVerifyUtil {


    /**
     * verify name
     * @param username
     * @return
     */
    public static boolean verifyUsername(String username){
        return username.length() <= 10;
    }

    /**
     * verify password valid
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password){

        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

        return Pattern.matches(regex,password);
    }
}
