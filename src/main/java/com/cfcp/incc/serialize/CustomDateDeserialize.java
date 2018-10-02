package com.cfcp.incc.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此注解用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码
 *  // 反序列化一个固定格式的Date
 *  @JsonDeserialize(using = CustomDateDeserialize.class)
 *
 * Created by Rambo on 2017/1/11 0011.
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		Date date = null;
		try {
			date = sdf.parse(jsonParser.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
