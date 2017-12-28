package org.mose.boot.util.web;


import org.mose.boot.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符串操作，用于保存和Web输入输出有关的方法
 *
 * @author 靳磊
 * @date 2017-05-23
 */
public class WebUtil {
    private static Logger logger = LoggerFactory.getLogger("exceptionLogger");

    /**
     * 在controller或action里面打印字符串，返回给前台
     *
     * @param response
     * @param str
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static void out(HttpServletResponse response, String str) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(str);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 输出application/json字符串
     *
     * @param response 响应
     * @param obj      需要转换为String的对象
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static void outJson(HttpServletResponse response, Object obj) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(JsonUtil.toString(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 根据字符串转换，如果为null，则变成""
     *
     * @param obj
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static String getSafeStr(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultStr
     *
     * @param obj
     * @param strDefault 为空默认值
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static String getSafeStr(Object obj, String strDefault) {
        return obj == null ? strDefault : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static int getSafeInt(Object obj) {
        return obj == null || obj.toString().equals("") ? 0 : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultInt
     *
     * @param obj
     * @param nDefualt 为空的默认值
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static int getSafeInt(Object obj, int nDefualt) {
        return obj == null || obj.toString().equals("") ? nDefualt : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static double getSafeDouble(Object obj) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param obj
     * @param nDefualt
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static double getSafeDouble(Object obj, double nDefualt) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param obj
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static float getSafeFloat(Object obj) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param obj
     *
     * @return
     *
     * @author 靳磊
     * @date 2017-05-23
     */
    public static float getSafeFloat(Object obj, float nDefualt) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }


    /**
     * what:    将给定视图名转为地址. <br/>
     * when:    (这里描述这个方法适用时机 – 可选).<br/>
     * how:     (这里描述这个方法的执行流程或使用方法 – 可选).<br/>
     * warning: (这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param viewName
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/8
     */
    public static String viewName2Url(String viewName) {
        return viewName + ".htm";
    }
}
