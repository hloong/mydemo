package com.hloong.mydemo.net4volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/7/7.
 */
public class GsonConverter {
    public static Gson GSON;
    static {
        GSON = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
    }

    public static String toJson(ApiPacket apiPacket) {
        return GSON.toJson(apiPacket);
    }

    public static <T extends ApiPacket> ApiResponse fromJson(String json, Type type) {
        return GSON.fromJson(json, type);

    }
}
