package es.charlye.coches.Encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptMD5 {
	private static MessageDigest md;
	
	public static String encryptMD5(String password){
		
		try { md = MessageDigest.getInstance("MD5");
			byte[] passBytes = password.getBytes();
			md.reset();
			passBytes= md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<passBytes.length;i++){
				sb.append(Integer.toHexString(0xff & passBytes[i]));
			}
		
		return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(EncryptMD5.class.getName()).log(Level.SEVERE, null, ex);
		}
	return null; 
	}
}