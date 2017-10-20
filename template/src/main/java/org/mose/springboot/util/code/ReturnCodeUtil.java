package org.mose.springboot.util.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 设置公共返回码
 *
 * 100*成功系列
 *
 * 200*失败系列
 *
 * 300*其他部分
 *
 * @Author: 靳磊
 * @Date: 2017/10/18 13:45
 */
public class ReturnCodeUtil {
    private static Map<Integer, String> map;

    /**
     * 保存成功
     */
    public static final int SUCCESS__SAVE = 10001;
    /**
     * 更新成功
     */
    public static final int SUCCESS__UPDATE = 10002;
    /**
     * 删除成功
     */
    public static final int SUCCESS__DELETE = 10003;

    /**
     * 保存失败
     */
    public static final int FAIL__SAVE = 20001;
    /**
     * 删除失败：包含下级节点
     */
    public static final int FAIL__DELETE_HAVE_CHILDREN = 20002;
    /**
     * 没有数据可更新
     *
     * @return
     */
    public static final int FAIL__UPDATE_NULL = 20003;

    //初始化map
    private static Map<Integer, String> createMap() {
        map = new HashMap<>();
        map.put(SUCCESS__SAVE, "保存成功");
        map.put(SUCCESS__UPDATE, "更新成功");
        map.put(SUCCESS__DELETE, "删除成功");
        map.put(FAIL__SAVE, "保存失败");
        map.put(FAIL__DELETE_HAVE_CHILDREN, "包含下级节点");
        map.put(FAIL__UPDATE_NULL, "没有数据可更新");

        return map;
    }

    /**
     * 获取返回的中文说明
     *
     * @param returnCode
     * @return
     */
    public static String getMsg(int returnCode) {
        map = map == null ? createMap() : map;
        return map.containsKey(returnCode) ? map.get(returnCode) : "";
    }

    /**
     * 返回编码是否是成功
     *
     * @param returnCode
     * @return
     */
    public static boolean isSuccess(int returnCode) {
        return returnCode < 20000 && returnCode > 9999;
    }

    /**
     * 返回编码是否是失败
     *
     * @param returnCode
     * @return
     */
    public static boolean isFail(int returnCode) {
        return returnCode < 30000 && returnCode > 19999;
    }
}
