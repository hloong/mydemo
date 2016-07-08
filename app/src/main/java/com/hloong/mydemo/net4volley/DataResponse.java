package com.hloong.mydemo.net4volley;
/**
 * DataResponse表示data为一个json对象
 * 
 * @author hloong
 * 
 * @param <T>
 */
public class DataResponse<T extends ApiPacket> extends ApiResponse{
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataResponse [data=" + data + "]";
	}

}
