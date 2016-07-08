package com.hloong.mydemo.net4volley;
import java.util.List;

/**
 * DataArrayResponse表示data为一个json数组
 * 
 * @author hloong
 * 
 * @param <T>
 */
public class DataArrayResponse<T extends ApiPacket> extends ApiResponse {
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public boolean hasData() {
		return data != null && data.size() > 0;
	}

	public int sizeOfReturened() {
		return data != null ? data.size() : 0;
	}
}
