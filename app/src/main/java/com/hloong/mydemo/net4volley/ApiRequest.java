package com.hloong.mydemo.net4volley;

import com.hloong.mydemo.UrlConstants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ApiRequest extends ApiPacket{
    private static final int modifiers = Modifier.TRANSIENT | Modifier.STATIC;

    private transient String methodName;

    public ApiRequest() {

    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    /**
     * 拼接POST_URL
     * @return
     */
    public String toPost_Url() {
        StringBuilder buff = new StringBuilder();
        buff.append(UrlConstants.API_URL);
        buff.append(methodName);
        return buff.toString();
    }

    /**
     * 拼接URL
     * @return
     */
    public String toGet_Url() {
        StringBuilder buff = new StringBuilder();
        buff.append(UrlConstants.API_URL);
        buff.append(methodName);

        Class<?> clazz = getClass();
        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 该字段是否被忽略
                boolean ignore = excludeField(field);
                if (ignore) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    // if(field.get(this)!=null)
                    Object fieldValue = field.get(this);
                    if (fieldValue != null) {
                        buff.append(field.getName())
                                .append("=")
                                .append(URLEncoder.encode("" + fieldValue, "UTF-8")).append("&");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
        buff.deleteCharAt(buff.length() - 1);

        return buff.toString();

    }


    /**
     * 该字段是否需要被忽略
     *
     * @param field
     * @return
     */
    public boolean excludeField(Field field) {
        Exclude exclude = field.getAnnotation(Exclude.class);
        if (exclude != null) {
            return exclude.value();
        }
        if ((modifiers & field.getModifiers()) != 0) {
            return true;
        }

        if (field.isSynthetic()) {
            return true;
        }

        if (isInnerClass(field.getType())) {
            return true;
        }

        if (isAnonymousOrLocal(field.getType())) {
            return true;
        }
        return false;
    }
    /**
     * 利用反射将请求对象参数转成map，给post请求
     *
     * @param obj
     *          表单对象
     * @return
     *         Map<String, String
     */
    public Map<String, String> getValueMap(Object obj) {

        Map<String, String> map_string = new HashMap<String, String>();
        Map<String, Object> map_object = new HashMap<String, Object>();
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null){
                    //map_string.put(varName, o.toString());
                    if (!varName.equals("methodName") && !varName.equals("modifiers")) {//屏蔽方法传输
                        map_object.put(varName, o);
                    }
                }
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
//        map_object.put("channelId", UrlConstants.CHANNELID);
        map_object.put("imei", "");
        String timeStamp = Long.toString(System.currentTimeMillis());
        map_object.put("timeStamp", timeStamp);
        return map_string;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ApiRequest other = (ApiRequest) obj;
        if (methodName == null) {
            if (other.methodName != null) {
                return false;
            }
        } else if (!methodName.equals(other.methodName)) {
            return false;
        }
        return equals(other);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        return result;
    }
    /**
     * 是枚举或者是native 的类
     *
     * @param clazz
     * @return
     */
    private boolean isAnonymousOrLocal(Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    /**
     * 是内部类
     *
     * @param clazz
     * @return
     */
    private boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !isStatic(clazz);
    }

    /**
     * 是静态的类
     *
     * @param clazz
     * @return
     */
    private boolean isStatic(Class<?> clazz) {
        return (clazz.getModifiers() & Modifier.STATIC) != 0;
    }
}
