package rs.raf.rafnews.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class Security {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static String hashPassword(String password) {
        byte[] salt = generateSalt();
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            byte[] combined = new byte[SALT_LENGTH + hash.length];
            System.arraycopy(salt, 0, combined, 0, SALT_LENGTH);
            System.arraycopy(hash, 0, combined, SALT_LENGTH, hash.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        byte[] storedCombined = Base64.getDecoder().decode(hashedPassword);
        byte[] storedSalt = Arrays.copyOfRange(storedCombined, 0, SALT_LENGTH);
        byte[] storedHash = Arrays.copyOfRange(storedCombined, SALT_LENGTH, storedCombined.length);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), storedSalt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();

            int diff = hash.length ^ storedHash.length;
            for (int i = 0; i < hash.length && i < storedHash.length; i++) {
                diff |= hash[i] ^ storedHash[i];
            }
            return diff == 0;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return false;
        }
    }

    private static byte[] generateSalt() {
        Random random = new Random();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
}
