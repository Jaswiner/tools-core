package com.jaswine.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * 重写@JsonSerializer的实现
 *
 * 保留两位小数
 *
 * @author jaswine
 */
public class CustomBigDecimalSerialize extends JsonSerializer<BigDecimal> {

	private DecimalFormat df = new DecimalFormat("00.00");


	@Override
	public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(df.format(bigDecimal));
	}


}