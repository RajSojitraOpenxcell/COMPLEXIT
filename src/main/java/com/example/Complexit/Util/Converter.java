package com.example.Complexit.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Converter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Autowired
	private ObjectMapper objectMapper;

	public Converter(){
		super(MediaType.APPLICATION_JSON_UTF8,
				new MediaType("application", "*+json", DEFAULT_CHARSET));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz,
	                              HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return objectMapper.readValue(decrypt(inputMessage.getBody()), clazz);
		//return new JSONObject();
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
	}

	private InputStream decrypt(InputStream inputStream){
		// do your decryption here
		JSONObject json=new JSONObject();
		String plainText = "";
		byte[] plainBytes = new byte[0];
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(
					new InputStreamReader(inputStream, "UTF-8"));

			System.out.println("In decrpt json obj="+jsonObject);
			String ciperText=jsonObject.getAsString("data");
			//String ciperText=new String(String.valueOf(inputStream));
			plainText=AESEncryption.decrypt(ciperText);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | ParseException e) {
			e.printStackTrace();
		}
		InputStream targetStream = new ByteArrayInputStream(plainText.getBytes(StandardCharsets.UTF_8));

		return targetStream;
	}

	private byte[] encrypt(byte[] bytesToEncrypt){
		// do your encryption here
		byte[] ciperBytes = new byte[0];
		try {
			String plainText=new String(bytesToEncrypt);
			String ciperText=AESEncryption.encrypt(plainText);

			JSONObject json = new JSONObject();
			json.put("data",ciperText);

			System.out.println("ciper text="+ciperText);
			ciperBytes = json.toJSONString().getBytes("utf-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ciperBytes;
	}
}
