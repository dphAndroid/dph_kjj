package kj.dph.com.util;

/**
 * Created by ${lgy} on 2017/8/1611:55
 * 邮箱1343168198@qq.com
 * 描述： 正规则表达式
 * 修改内容：
 */

public class RegularUtils {

    public final static String HANZI_ZIMU_SHUZI = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";//只含有汉字、数字、字母

    public final static String LOGIN_PWD = "^([\\d]+$)([a-zA-Z]+$)([\\da-zA-Z]+$)\\S{6,16}$";//包括 字符 数字 和特殊符号   除了汉字和空格

    public final static String PHONE = "^[1][0-9]{10}+$";//手机号

    public final static String HANZI = "^[\\u4e00-\\u9fa5]{0,}$";//只含有汉字
    //判断是否是18位身份证号身份证号
    public final static String IDCARDNUM = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    public final static String NAMBER = "^[0-9]*$";//只能是数字
}
