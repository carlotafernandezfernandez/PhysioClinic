package PhysioClinicEncription;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encription {
	
	private static final String ALGORITMO = "AES";
    private static final String CLAVE_SECRETA = "claveSecreta123451234567"; 
	
	 public static String encrypt(String contraseña) throws Exception {
	        SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), ALGORITMO);
	        Cipher cifrador = Cipher.getInstance(ALGORITMO);
	        cifrador.init(Cipher.ENCRYPT_MODE, clave);
	        byte[] textoCifrado = cifrador.doFinal(contraseña.getBytes());
	        return Base64.getEncoder().encodeToString(textoCifrado);
	    }
	 
	 public static String decrypt(String contraseñaCifrada) throws Exception {
	        SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), ALGORITMO);
	        Cipher cifrador = Cipher.getInstance(ALGORITMO);
	        cifrador.init(Cipher.DECRYPT_MODE, clave);
	        byte[] textoPlano = cifrador.doFinal(Base64.getDecoder().decode(contraseñaCifrada));
	        return new String(textoPlano);
	    }
	 
}

