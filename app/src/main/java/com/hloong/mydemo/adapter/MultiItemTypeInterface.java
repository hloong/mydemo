package com.hloong.mydemo.adapter;
/**
 * list多布局接口
 * @author hloong
 * @web http://www.hloong.com
 * 2015-7-23下午2:32:05
 */
public interface MultiItemTypeInterface<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);
}