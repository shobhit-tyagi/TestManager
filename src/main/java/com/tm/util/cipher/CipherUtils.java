package com.tm.util.cipher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.tm.util.exceptions.CipherException;
import com.tm.util.exceptions.FileLoadException;
import com.tm.util.file.PropertyUtils;

public class CipherUtils {
	
	public static final String DEFAULT_ENCODING="UTF-8";
	
	private static Base64.Encoder enc = Base64.getEncoder();
	private static Base64.Decoder dec = Base64.getDecoder();

	public static String encrypt(String strToEncrypt) throws FileLoadException, CipherException {
		final String key = PropertyUtils.loadProperties("	app-config.properties").getProperty("encryption.key");
		String encrypted = null;
		try {
			strToEncrypt = xorMessage(strToEncrypt, key);
			encrypted = base64encode(strToEncrypt);
		} catch(final Exception e) {
			throw new CipherException("Encryption failed", e);
		}
		return encrypted;
	}
	
	public static String decrypt(String strToDecrypt) throws FileLoadException, CipherException {
		final String key = PropertyUtils.loadProperties("app-config.properties").getProperty("encryption.key");
		String decrypted = null;
		try {
			strToDecrypt = base64decode(strToDecrypt);
			decrypted = xorMessage(strToDecrypt, key);
		} catch(final Exception e) {
			throw new CipherException("Decryption failed", e);
		}
		
		return decrypted;
	}
	
	private static String base64encode(final String text) throws UnsupportedEncodingException {
		return enc.encode(text.getBytes(DEFAULT_ENCODING)).toString();
	}

	private static String base64decode(final String text) throws UnsupportedEncodingException, IOException {
		return new String(dec.decode(text), DEFAULT_ENCODING);
	}
	
	private static String xorMessage(final String message, final String key) {
		if (message == null || key == null)
			return null;
		char[] keys = key.toCharArray();
		char[] mesg = message.toCharArray();
		final int ml = mesg.length;
		final int kl = keys.length;
		final char[] newmsg = new char[ml];
		for (int i = 0; i < ml; i++) {
			newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
		}
		mesg = null;
		keys = null;
		return new String(newmsg);
	}
}
