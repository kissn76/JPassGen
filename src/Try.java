import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Try {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String tmp = new Random().nextInt(Integer.MAX_VALUE) + "" + System.currentTimeMillis();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(tmp.getBytes("utf8"));
        String toReturn = String.format("%040x", new BigInteger(1, digest.digest()));

    }

}
