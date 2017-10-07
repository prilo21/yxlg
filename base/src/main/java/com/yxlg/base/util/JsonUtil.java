package com.yxlg.base.util;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.google.gson.Gson;
import com.yxlg.base.constant.Constants;

import net.sf.json.JSONObject;

/**
 * json处理工具类
 * 
 * @author dirk
 * @version <br>
 *          <p>
 *          类的描述
 *          </p>
 */
@Component
public class JsonUtil {
	
	private static final ObjectMapper mapper;
	
	public ObjectMapper getMapper() {
		
		return mapper;
	}
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.DateFormatConstant.TIME_FORMAT);
			
		mapper = new ObjectMapper();
		mapper.setDateFormat(dateFormat);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		//json字符串转对象时,忽略不存在的key
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//null 对象忽略
		mapper.setSerializationInclusion(Include.NON_NULL);  
		mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
			
			private static final long serialVersionUID = -5684967182250938195L;
			
			@Override
			public Object findSerializer(Annotated a) {
				
				if (a instanceof AnnotatedMethod) {
					AnnotatedElement m = a.getAnnotated();
					DateTimeFormat an = m.getAnnotation(DateTimeFormat.class);
					if (an != null) {
						if (!Constants.DateFormatConstant.TIME_FORMAT
								.equals(an.pattern())) {
							return new JsonDateSerializer(an.pattern());
						}
					}
				}
				return super.findSerializer(a);
			}
		});
	}
	
	public static String toJson(Object obj) {
		
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("转换json字符失败!",e);
		}
	}
	
	public static <T> T toObject(String json, Class<T> clazz) {
		
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException("将json字符转换为对象时失败!\n数据："+json,e);
		}
	}
	
	public static <T> List<T> toObjectList(String json, JavaType javaType) {
		
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			throw new RuntimeException("将json字符转换为对象时失败!\n数据："+json,e);
		}
	}
	
	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
			
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
	
	public static class JsonDateSerializer extends JsonSerializer<Date> {
		
		private SimpleDateFormat dateFormat;
		
		public JsonDateSerializer(String format) {
			
			dateFormat = new SimpleDateFormat(format);
		}
		
		@Override
		public void serialize(Date date, JsonGenerator gen,
				SerializerProvider provider)
						throws IOException, JsonProcessingException {
						
			String value = dateFormat.format(date);
			gen.writeString(value);
		}
	}
	public static Map<String, String> toMap(JSONObject jsonObject) 
	    { 
	        Map<String, String> result = new HashMap<String, String>(); 
	        if(jsonObject==null)
	        	return result;
	        @SuppressWarnings("unchecked")
			Set<String> set = jsonObject.keySet(); 
	        Iterator<String>iterator= set.iterator();
	        String key = null; 
	        String value = null; 
	        while (iterator.hasNext()) 
	        { 
	            key = iterator.next(); 
	            value = jsonObject.getString(key); 
	            result.put(key.toUpperCase(), null==value?StringUtils.EMPTY:value); 
	        } 
	        return result; 
	    }
	public static String listToJsonByNetSf(List<String> list) {
		return net.sf.json.JSONArray.fromObject(list).toString();
	}
	public static String listToJsonByOrgJson(List<String> list) {
		org.json.JSONArray jsonArr = new org.json.JSONArray();
		for (String string : list) {
			jsonArr.put(string);
		}
		return jsonArr.toString();
	}
	public static String listToJsonByGson(List<String> list) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
	public static String listToJsonByFastJson(List<String> list) {
		return com.alibaba.fastjson.JSONArray.toJSONString(list);
	}

}
