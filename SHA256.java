
import java.security.MessageDigest;

public class SHA256 { // The SHA256 Hashes are the fingerprints of the blocks

    public static String hash(String data) {
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

      public static void main(String[] args) {

        // Bitcoin uses "double-SHA256" algorithm
        System.out.println(SHA256.hash(SHA256.hash("Bit-Coin")));
        System.out.println(SHA256.hash("blockchain"));
        System.out.println(SHA256.hash("blockchain!"));
      }
}

/*
Features of Hashing Algorithms
"SHA256"
1) deterministic : if we apply to same hash-function(SHA256) on the exact same input then output must be the same.
2) one-way : it is easy to generate the hash with the given hashing algorithm but,
             it is extremely hard, time-consuming, to restore the original input.
3) collision-free : no collisions in SHA256 (okay but with extremely low prob)
                    It means no two different inputs share the same output hash.
                    this is how we identify a block in the blockchain.
4) avalanche effect : a little change in the input results in a completely different output hash.
                      Otherwise a cryptoanalyst can make predictions about the input based on the output exclusively.
*/


/*
[Output]
c0a2803c0033c770a4d225e0f63fc64f4484eab88b0348e555aa9a7abb3847e2
ef7797e13d3a75526946a3bcf00daec9fc9c9c4d51ddc7cc5df888f74dd434d1
e1a7169b0d59bce211f532e400d82d0e13b97f39794b26d1e9ec8ad7143a5e15
*/
