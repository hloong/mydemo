package com.hloong.mydemo.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * retrieve phone info 
 * 1. 手机的IMEI 2.手机的制式类型，GSM OR CDMA 手机 3.手机网络国家编码
 * 4.手机网络运营商ID 5.手机网络运营商名称 6.手机的数据链接类型 
 * 7.是否有可用数据链接 8.当前的数据链接类型 9.手机剩余内存 10.手机总内存
 * 11.手机CPU型号 12.CPU序列号 13.手机MAC地址 
 * 14.手机名称 15.手机型号 16.手机设备制造商名称 
 * 注意：需要如下权限 <uses-permission android:name="android.permission.READ_PHONE_STATE"/> 
 *               <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * 
 * @hloong
 */

public class PhoneInfoUtil {
    private static final String TAG = PhoneInfoUtil.class.getSimpleName();

    private static final String FILE_MEMORY = "/proc/meminfo";

    private static final String FILE_CPU = "/proc/cpuinfo";

    public String mIMEI;

    public int mPhoneType;

    public int mSysVersion;

    public String mNetWorkCountryIso;

    public String mNetWorkOperator;

    public String mNetWorkOperatorName;

    public int mNetWorkType;

    public boolean mIsOnLine;

    public String mConnectTypeName;

    public long mFreeMem;

    public long mTotalMem;

    public String mCpuInfo;
    
    public String mCpuSerial;

    public String mMACAddress;

    public String mProductName;

    public String mModelName;

    public String mManufacturerName;

    /**
     * private constructor
     */
    public PhoneInfoUtil() {
        // TODO Auto-generated constructor stub

    }

    /**
     * get IMEI of phone 手机唯一标识
     * 
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Activity.TELEPHONY_SERVICE);
        // check if has the permission
        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
            return manager.getDeviceId();
        } else {
            return null;
        }
    }

    /**
     * get phone type ,like 手机的制式类型，GSM OR CDMA 手机
     * 
     * @param context
     * @return
     */
    public static int getPhoneType(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getPhoneType();
    }

    /**
     * get phone 系统 version
     * 
     * @return
     */
    public static int getSysVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * Returns the ISO country code equivalent of the current registered
     * operator's MCC (Mobile Country Code).手机网络国家编码
     * 
     * @param context
     * @return
     */
    public static String getNetWorkCountryIso(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso();
    }

    /**
     * Returns the numeric name (MCC+MNC) of current registered operator.may not
     * work on CDMA phone 手机网络运营商ID。
     * 
     * @param context
     * @return
     */
    public static String getNetWorkOperator(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getNetworkOperator();
    }

    /**
     * Returns the alphabetic name of current registered operator.may not work
     * on CDMA phone 手机网络运营商名称
     * 
     * @param context
     * @return
     */
    public static String getNetWorkOperatorName(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getNetworkOperatorName();
    }

    /**
     * get type of current network 手机的数据链接类型
     * 
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        return manager.getNetworkType();
    }

    /**
     * is webservice aviliable ,是否有可用数据链接
     * 
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * get current data connection type name ,like 数据连接类型，wifi还是3g
     * 
     * @param context
     * @return
     */
    public static String getConnectTypeName(Context context) {
        if (!isOnline(context)) {
            return "OFFLINE";
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.getTypeName();
        } else {
            return "OFFLINE";
        }
    }

    /**
     * get free memory of phone, in M 手机剩余内存
     * 
     * @param context
     * @return
     */
    public static long getFreeMem(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        MemoryInfo info = new MemoryInfo();
        manager.getMemoryInfo(info);
        long free = info.availMem / 1024 / 1024;
        return free;
    }

    /**
     * get total memory of phone , in M 手机总内存
     * 
     * @param context
     * @return
     */
    public static long getTotalMem(Context context) {
        try {
            FileReader fr = new FileReader(FILE_MEMORY);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split("\\s+");
            Log.w(TAG, text);
            return Long.valueOf(array[1]) / 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * get CPU of phone 手机CpuInfo
     * 
     * @return
     */
    public static String getCpuInfo() {
        try {
            FileReader fr = new FileReader(FILE_CPU);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
                Log.w(TAG, "......" + array[i]);
            }
            Log.w(TAG, text);
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * get CpuSerial of phone 获取CPU序列号
     * 
     * @return CPU序列号(16位) 读取失败为"0000000000000000"
     */
    public static String getCpuSerial() {
        String str = "", strCPU = "", cpuAddress = "";
        try {
            // 读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            // 查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    // 查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        // 提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        // 去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    // 文件结尾
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * get MAC of phone 手机MAC地址
     * 
     * @return
     */
    public static String getMacAddress() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return macSerial;
    }

    /**
     * get product name of phone 手机名称
     * 
     * @return
     */
    public static String getProductName() {
        return Build.PRODUCT;
    }

    /**
     * get model of phone 手机型号
     * 
     * @return
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * get Manufacturer Name of phone 手机设备制造商名称(ROM制造商)
     * 
     * @return
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    public static PhoneInfoUtil getPhoneInfo(Context context) {
        PhoneInfoUtil result = new PhoneInfoUtil();
        result.mIMEI = getIMEI(context);
        result.mPhoneType = getPhoneType(context);
        result.mSysVersion = getSysVersion();
        result.mNetWorkCountryIso = getNetWorkCountryIso(context);
        result.mNetWorkOperator = getNetWorkOperator(context);
        result.mNetWorkOperatorName = getNetWorkOperatorName(context);
        result.mNetWorkType = getNetworkType(context);
        result.mIsOnLine = isOnline(context);
        result.mConnectTypeName = getConnectTypeName(context);
        result.mFreeMem = getFreeMem(context);
        result.mTotalMem = getTotalMem(context);
        result.mCpuInfo = getCpuInfo();
        result.mCpuSerial = getCpuSerial();
        result.mMACAddress = getMacAddress();
        result.mProductName = getProductName();
        result.mModelName = getModelName();
        result.mManufacturerName = getManufacturerName();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IMEI : " + mIMEI + "\n");
        builder.append("mPhoneType : " + mPhoneType + "\n");
        builder.append("mSysVersion : " + mSysVersion + "\n");
        builder.append("mNetWorkCountryIso : " + mNetWorkCountryIso + "\n");
        builder.append("mNetWorkOperator : " + mNetWorkOperator + "\n");
        builder.append("mNetWorkOperatorName : " + mNetWorkOperatorName + "\n");
        builder.append("mNetWorkType : " + mNetWorkType + "\n");
        builder.append("mIsOnLine : " + mIsOnLine + "\n");
        builder.append("mConnectTypeName : " + mConnectTypeName + "\n");
        builder.append("mFreeMem : " + mFreeMem + "M\n");
        builder.append("mTotalMem : " + mTotalMem + "M\n");
        builder.append("mCupInfo : " + mCpuInfo + "\n");
        builder.append("mCpuSerial : " + mCpuSerial + "\n");
        builder.append("mMACAddress : " + mMACAddress + "\n");
        builder.append("mProductName : " + mProductName + "\n");
        builder.append("mModelName : " + mModelName + "\n");
        builder.append("mManufacturerName : " + mManufacturerName + "\n");
        return builder.toString();
    }

}
