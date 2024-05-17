package PhysioClinicEncription;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encription {
	
	private static final String ALGRTHM = "AES";
    //private static final String SECRET_KEY = "claveSecreta123451234567"; 
    private static final String SECRET_KEY = "18563829ch2&b%bnd(&be/2?"; 
	
	 public static byte[] encrypt(String passwd) throws Exception {
	        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGRTHM);
	        Cipher ciph = Cipher.getInstance(ALGRTHM);
	        ciph.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encryptedText = ciph.doFinal(passwd.getBytes());
	        return encryptedText; 
	    }

	 public static String decrypt(byte[] encryptedPasswd) throws Exception {
	        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGRTHM);
	        Cipher ciph = Cipher.getInstance(ALGRTHM);
	        ciph.init(Cipher.DECRYPT_MODE, key);
	        byte[] passwd = ciph.doFinal(encryptedPasswd);
	        return new String(passwd);
	    }
	 
	 public static String toStringCifrado(byte[] encryptedPasswd) throws Exception {
		 return  Base64.getEncoder().encodeToString(encryptedPasswd);
	 }
	 
}

