
import java.security.MessageDigest;

public class SHA256 { // The SHA256 Hashes are the fingerprints of the blocks

    public static String hashing(String data) {
      try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));

            // we need hexadecimal values not bytes
            StringBuffer hexStr = new StringBuffer();

            for(int i=0; i<hash.length; i++){
              String hex = Integer.toHexString(0xff & hash[i]);
              if(hex.length() == 1) {
                hexStr.append('0');
              }
              hexStr.append(hex);
            }

            return hexStr.toString();
          }
          catch(Exception e) {
            throw new RuntimeException(e);
          }
      }
}
