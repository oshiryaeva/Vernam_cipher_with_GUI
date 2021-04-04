import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class VernamEncryptor {

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toCharArray();

    /*
     * Генерирует ключ заданной длины
     */
    public String generateKey(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder key = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Builds the key.
        for (int i = 0; i < length; i++) {
            int randomValue = secureRandom.nextInt(26);
            key.append(ALPHABET[randomValue]);
        }
        return key.toString();
    }

    /*
     * Шифрует сообщение с помощью исключающего ИЛИ (XOR) и переданного ключа
     */
    public String encrypt(String plainText, String key) {
        //Необходимо, чтобы длины ключа и текста совпадали
        if (plainText.getBytes().length != key.length()) {
            return null;
        }
        byte[] plainTextBytes = plainText.getBytes();
        byte[] keyBytes = key.getBytes();
        byte[] encryptedText = new byte[plainTextBytes.length];
        for (int i = 0; i < plainTextBytes.length; i++) {
            encryptedText[i] = (byte) (keyBytes[i] ^ plainTextBytes[i]);
        }
        return new String(encryptedText);
    }

    /*
     * Дешифрует сообщение с помощью исключающего ИЛИ (XOR) и переданного ключа
     */
    public String decrypt(String cipherText, String key) {
        //Необходимо, чтобы длины ключа и текста совпадали
        if (cipherText.getBytes().length != key.length()) {
            return null;
        }
        byte[] cipherTextBytes = cipherText.getBytes();
        byte[] keyBytes = key.getBytes();
        byte[] decryptedText = new byte[cipherTextBytes.length];
        for (int i = 0; i < cipherTextBytes.length; i++) {
            decryptedText[i] = (byte) (keyBytes[i] ^ cipherTextBytes[i]);
        }
        return new String(decryptedText).toUpperCase();
    }
}
