/**
 * 
 */
package com.xjb.xld.monitor.common.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * JSON 辅助
 * 
 * @author yuhui.tang 2013-7-18 下午2:58:14
 */
public class JSONAssister {

	private static final SerializeConfig config;
	static {
		config = new SerializeConfig();
		SimpleDateFormatSerializer dataFormatSerializer = new SimpleDateFormatSerializer(
				"yyyy-MM-dd HH:mm:ss");
		config.put(java.util.Date.class, dataFormatSerializer);
		config.put(java.sql.Date.class, dataFormatSerializer);
	}

	private static final SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> jsonObjectToMap(JSONObject obj) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Iterator<String> keyIter = obj.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			Object value = obj.get(key);
			resultMap.put(key, value);
		}
		return resultMap;
	}
}
