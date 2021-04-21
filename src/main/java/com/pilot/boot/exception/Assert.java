package com.pilot.boot.exception;

import com.pilot.boot.utils.CommonResult;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 断言表达式
 *
 * @author ezuy
 * @date 21/4/12 11:59
 */
public class Assert {

    /**
     * 断言对象不为null
     * 如果对象为null则抛出异常
     *
     * @param object
     * @param commonResult
     */
    public static void notNull(Object object, CommonResult commonResult) {
        if (object == null) {
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言对象为null
     * 如果不为null则抛出异常
     *
     * @param object
     * @param commonResult
     */
    public static void isNull(Object object, CommonResult commonResult) {
        if (object != null) {
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言flag不为true
     * 若为false则抛出异常
     * @param flag
     * @param commonResult
     */
    public static void notTrue(boolean flag,CommonResult commonResult){
        if (flag){
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言表达式为真
     * 如果不为真则抛出异常
     *
     * @param flag
     * @param commonResult
     */
    public static void isTrue(boolean flag, CommonResult commonResult) {
        if (!flag) {
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言字符串不为空
     * 如果为空则抛出异常
     *
     * @param s
     * @param commonResult
     */
    public static void notEmpty(String s, CommonResult commonResult) {
        if (StringUtils.isEmpty(s)) {
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言object1 equal object2
     * 如果不等 则抛出异常
     * @param object1
     * @param object2
     * @param commonResult
     */
    public static void isEqual(Object object1,Object object2,CommonResult commonResult){
        if (!object1.equals(object2)){
            throw new MyException(commonResult);
        }
    }

    private static final Integer USERNAME_LENGTH = 20;

    /**
     * 断言用户名合法
     * 若不合法则抛出异常
     *
     * @param username
     * @param commonResult
     */
    public static void validName(String username, CommonResult commonResult) {
        if (!(username.length() <= USERNAME_LENGTH && username != null)) {
            throw new MyException(commonResult);
        }
    }

    private static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    /**
     * 断言密码合法
     * 若不合法则抛出异常
     *
     * @param password
     * @param commonResult
     */
    public static void validPassword(String password, CommonResult commonResult) {
        if (!(Pattern.matches(PASSWORD_REGEX, password))) {
            throw new MyException(commonResult);
        }
    }

    private static final Integer CARD_LENGTH = 18;

    /**
     * 断言身份证号码合法
     * 若不合法则抛出异常
     *
     * @param card
     * @param commonResult
     */
    public static void validCard(String card, CommonResult commonResult) {
        if (card.length() != CARD_LENGTH) {
            throw new MyException(commonResult);
        }
    }

    private static final String PHONE_REGEX = "(^((1[0-9][0-9]))\\d{8}$)||(^$)";

    /**
     * 断言手机号合法
     * 若不合法则抛出异常
     *
     * @param phone
     * @param commonResult
     */
    public static void validPhone(String phone, CommonResult commonResult) {
        if (!(Pattern.matches(PHONE_REGEX, phone))) {
            throw new MyException(commonResult);
        }
    }

    private static final String EMAIL_REGEX = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)|(^\\s*$)||(^$)";

    /**
     * 断言邮箱合法
     * 若不合法则抛出异常
     *
     * @param email
     * @param commonResult
     */
    public static void validEmail(String email, CommonResult commonResult) {
        if (!(Pattern.matches(EMAIL_REGEX, email))) {
            throw new MyException(commonResult);
        }
    }

    /**
     * 断言部门名称合法
     * 若不合法则抛出异常
     * @param deptName
     * @param commonResult
     */
    public static void validDeptName(String deptName,CommonResult commonResult){
        if (!(deptName.length() <= 20 && deptName.length() >= 2)){
            throw new MyException(commonResult);
        }
    }
}
