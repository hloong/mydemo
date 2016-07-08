package com.hloong.mydemo.net4volley;
import java.lang.reflect.Type;
import java.util.Hashtable;

/**求 数据类型
 * 返回结果类型注册
 * @author hloong
 *
 */
public class ResponseTypeProvider {

	private static Hashtable<String, Type> responseMap = new Hashtable<String, Type>();
	static {
	    //每一个请求，都需要在此处添加对应的hashmap put
	    //三种反射方式，当data下是一个数组列表的时候 用DataArrayResponse，
	    //如果data下是一个具体的数据对象则用DataResponse，如果没有data就用ApiResponse,
//		responseMap.put(LoginRequest.class.toString(), new TypeToken<DataResponse<LoginData>>() {}.getType());
//		//钱富宝 赎回
//		responseMap.put(QfbBackRequest.class.toString(), new TypeToken<ApiResponse>() {}.getType());
//		//零钱明细
//		responseMap.put(PageBaseRequest.class.toString(), new TypeToken<DataArrayResponse<LooseDetailData>>() {}.getType());
	}

	public static Type getApiResponseType(Class<? extends ApiRequest> clazz) {
		Type responseType = responseMap.get(clazz.toString());
		return responseType != null ? responseType : ApiResponse.class;
	}

	public static void registerResponseType(Class<? extends ApiRequest> clazz, Type responseType) {
		responseMap.put(clazz.toString() , responseType);
	} 
}
