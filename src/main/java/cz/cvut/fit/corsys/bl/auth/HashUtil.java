package cz.cvut.fit.corsys.bl.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

    public static String getPasswordHash(String password) {
        MessageDigest m;
        try {
            byte[] data = password.getBytes();
            m = MessageDigest.getInstance("MD5");
            m.update(data, 0, data.length);
            byte[] hash = m.digest();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
