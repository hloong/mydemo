package com.hloong.mydemo.net;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface Cache<K, V> {

    public V get(K key);

    public void put(K key, V value);

    public void remove(K key);
}
