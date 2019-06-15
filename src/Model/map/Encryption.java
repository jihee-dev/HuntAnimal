package model.map;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String sha256(String pw) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(pw.getBytes());
        return CryptoUtil.byteToHexString(sha.digest());
    }
}
