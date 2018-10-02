package com.cfcp.incc.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;


/**
 *  此注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，比如序列化一个double时在其后面限制两位小数点。
 *  @JsonSerialize(using = CustomDoubleSerialize.class)
 * Created by Rambo on 2017/1/11 0011.
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

	private DecimalFormat df = new DecimalFormat("##0.00");

	@Override
	public void serialize(Double value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		jgen.writeString(df.format(value));
	}
}