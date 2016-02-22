package com.ocellus.core.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.geronimo.mail.util.Base64;

public class EncryptUtil
{
	public static final String AES = "AES";
	public static String encryptAes(String content, String key)
	{
		String result = null;
		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance(AES);
			kgen.init(128, new SecureRandom(key.getBytes()));
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), AES));  
			byte[] after = cipher.doFinal(content.getBytes("UTF-8"));
			result = new String(Base64.encode(after));
	          
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		String result = encryptAes("admin","admin");
		System.out.println(result);
	}
}
