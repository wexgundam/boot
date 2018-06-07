package org.mose.util.code;

import java.util.HashMap;
import java.util.Map;

/**
 * what:    设置公共返回码. <br/>
 * when:    控制器、服务、持久化等操作结束后，通过返回码统一标识方法调用结果.<br/>
 * how:     100*成功系列.<br/>
 *          200*失败系列.<br/>
 *          300*其他部分.<br/>
 * warning: 返回消息与返回码对应，可以设置自定义的返回消息.<br/>
 *
 * @author 靳磊 created on ${DATE}
 */
public class ReturnCodeUtil {
    /**
     * 缓存返回码和对应消息
     */
    private static Map<Integer, String> map;
    /**************************************************
     * 10100~10199 插入成功
     * 10200~10299 更新成功
     * 10300~10399 删除成功
     *************************************************/
    /**
     * 保存成功
     */
    public static final int SUCCESS__INSERT = 10100;
    /**
     * 更新成功
     */
    public static final int SUCCESS__UPDATE = 10200;
    /**
     * 删除成功
     */
    public static final int SUCCESS__DELETE = 10300;

    /**************************************************
     * 20101~20199 插入失败
     * 20201~20299 更新失败
     * 20301~20399 删除失败
     *************************************************/
    /**
     * 保存失败
     */
    public static final int FAIL__INSERT = 20100;
    /**
     * 保存失败，已存在相同数据
     */
    public static final int FAIL__INSERT_EXIST = 20101;
    /**
     * 没有数据可更新
     *
     * @return
     */
    public static final int FAIL__UPDATE_NONE = 20200;
    /**
     * 没有数据可删除
     *
     * @return
     */
    public static final int FAIL__DELETE_NONE = 20300;
    /**
     * 删除失败：包含下级节点
     */
    public static final int FAIL__DELETE_HAVE_CHILDREN = 20301;

    //初始化map
    private static Map<Integer, String> createMap() {
        map = new HashMap<>();
        map.put(SUCCESS__INSERT, "保存成功");
        map.put(SUCCESS__UPDATE, "更新成功");
        map.put(SUCCESS__DELETE, "删除成功");
        map.put(FAIL__INSERT, "保存失败");
        map.put(FAIL__INSERT_EXIST, "保存失败，已存在相同数据");
        map.put(FAIL__UPDATE_NONE, "没有数据可更新");
        map.put(FAIL__DELETE_NONE, "没有数据可删除");
        map.put(FAIL__DELETE_HAVE_CHILDREN, "包含下级节点");

        return map;
    }

    public static void setReturnCode(int returnCode, String message) {
        map.put(returnCode, message);
    }

    /**
     * 获取返回的说明
     *
     * @param returnCode
     *
     * @return
     */
    public static String getMessage(int returnCode) {
        map = map == null ? createMap() : map;
        return map.containsKey(returnCode) ? map.get(returnCode) : "";
    }

    /**
     * 返回编码是否是成功
     *
     * @param returnCode
     *
     * @return
     */
    public static boolean isSuccess(int returnCode) {
        return returnCode < 20000 && returnCode > 9999;
    }

    /**
     * 返回编码是否是失败
     *
     * @param returnCode
     *
     * @return
     */
    public static boolean isFail(int returnCode) {
        return returnCode < 30000 && returnCode > 19999;
    }
}
