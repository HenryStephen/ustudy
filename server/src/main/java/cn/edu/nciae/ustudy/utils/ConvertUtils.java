package cn.edu.nciae.ustudy.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;


/**
 * 类型转换工具类
 *
 * @author tang 2019-06-03 17:26:22
 */
public class ConvertUtils {

    /**
     * POJO间的类型转换，使用Spring的BeanUtils.copyProperties()实现，
     *  <b>并非所有情况都是深拷贝<b/>
     *  <b>无法拷贝一些集合属性</b>
     * @param src 原实例
     * @param clazz 要转换的类型
     */
    public static <T,V> T from(@NonNull V src, Class<T> clazz) {
        try{
            T res = clazz.getConstructor().newInstance();
            BeanUtils.copyProperties(src, res);
            return res;
        } catch (Exception e) {
            throw new ClassCastException("Convert failed."+e.getMessage());
        }
    }

    public static <T, V> void copy(@NonNull V source, @NonNull T target) {
        BeanUtils.copyProperties(source, target);
    }

}
