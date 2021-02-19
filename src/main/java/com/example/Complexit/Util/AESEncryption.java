package com.example.Complexit.Util;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class AESEncryption {

	static String algorithm="AES/CBC/PKCS5Padding";
//	static SecretKey key;
//
//
//	static {
//		try {
//			key = generateKey(128);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//	}
//	final static SecretKey keys = key;
//
//	//Salted__¨®'+ýª…\u001BC…Š\u0007\u0019ÌMª×¨\"°%é¶E;
//	static final IvParameterSpec iv=generateIv();
//			//bdb271ce5235996a0709e09c;

	//static String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
	//static String ivstr = Base64.getEncoder().encodeToString(iv.getIV());

//	static {
//		try {
//			key = AESEncryption.generateKey(128);
//			iv=AESEncryption.generateIv();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//	}

	static byte[] encodedKey=new String("Ng8UybKkb5cA<?#y").getBytes();
	static SecretKey key = new SecretKeySpec(encodedKey,"AES");

	static byte[] ivarr=new String("vSJ&:[F)229dfkN^").getBytes();
	static IvParameterSpec iv=new IvParameterSpec(ivarr);

	public AESEncryption() throws NoSuchAlgorithmException {
	}

//	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
//		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//		keyGenerator.init(n);
//		SecretKey key = keyGenerator.generateKey();
//		return key;
//	}
//
//	public static IvParameterSpec generateIv() {
//		byte[] iv = new byte[16];
//		new SecureRandom().nextBytes(iv);
//		return new IvParameterSpec(iv);
//	}

	public static String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException {
		//log.info("{}, {}",key.toString(), iv);
		System.out.println("secreate key={ "+encodedKey+" }");

		String ivArr=Base64.getEncoder().encodeToString(iv.getIV());
		System.out.println("Iv vector={ "+ivArr+" }");

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder()
				.encodeToString(cipherText);
	}

	public static String decrypt(String cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException, ParseException {

		System.out.println("In Decryption...."+cipherText);

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder()
				.decode(cipherText));
		System.out.println("In Decryption....plain text="+new String(plainText)+" ");
		return new String(plainText);
	}
}


/*
// create new key
SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
// get base64 encoded version of the key
String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

ex key: DjlhaAT0RoyKKNtXyFcvdQ==
ex iv:

// decode the base64 encoded string
byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
// rebuild key using SecretKeySpec
SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

**/
