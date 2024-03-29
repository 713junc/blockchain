import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchProviderException;
import java.security.MessageDigest;

public class Cryptography {
    public static String newHash(String data) { 
      try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));

            StringBuffer hexStr = new StringBuffer();
            for(int i=0; i<hash.length; i++){
              String hex = Integer.toHexString(0xff & hash[i]);
              if(hex.length() == 1) {
                hexStr.append('0');
              }
              hexStr.append(hex);
            }
            return hexStr.toString();
          } catch(Exception e) {
            throw new RuntimeException(e);
          }
    }

    public static KeyPair ellipticCurveCrypto() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
            keyPairGenerator.initialize(params, secureRandom); 
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] applyECDSASignature(PrivateKey privateKey, String input) {
        Signature signature;
        byte[] output = new byte[0];
        try {
            signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            byte[] strByte = input.getBytes();
            signature.update(strByte);
            byte[] realSignature = signature.sign();
            output = realSignature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public static boolean verifyECDSASignature(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaSignature = Signature.getInstance("ECDSA", "BC");
            ecdsaSignature.initVerify(publicKey);
            ecdsaSignature.update(data.getBytes());
            return ecdsaSignature.verify(signature);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
