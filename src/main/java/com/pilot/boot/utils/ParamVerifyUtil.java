package com.pilot.boot.utils;

import java.util.regex.Pattern;

/**
 * param verify
 *
 * @author ezuy
 * @date 21/1/20 22:46
 */
public class ParamVerifyUtil {


    /**
     * verify name
     *
     * @param username
     * @return
     */
    public static boolean verifyUsername(String username) {
        return username.length() <= 10 && username != null;
    }

    /**
     * verify password valid
     *
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password) {

        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

        return Pattern.matches(regex, password);
    }

    /**
     * verify card valid
     *
     * @param card
     * @return
     */
    public static boolean verifyCard(String card) {
        return card.length() == 18;
    }

    /**
     * verify phone valid
     *
     * @param phone
     * @return
     */
    public static boolean verifyPhone(String phone) {
        String regex = "(^((1[0-9][0-9]))\\d{8}$)||(^$)";
        return Pattern.matches(regex, phone);
    }

    /**
     * verify email valid
     *
     * @param email
     * @return
     */
    public static boolean verifyEmail(String email) {

        String regex = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)|(^\\s*$)||(^$)";

        return Pattern.matches(regex, email);
    }

    /**
     * verify deptName
     *
     * @param deptName
     * @return
     */
    public static boolean verifyDeptName(String deptName) {
        return deptName.length() <= 20 && deptName.length() >= 2;
    }
}
