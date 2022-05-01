package utilities;

import org.jasypt.util.text.BasicTextEncryptor;


/**
 * This page object class is used to keep the Encryption/decryption functions
 *
 * author Jey
 */
public class TextEncryptor {

    /**
     * This method is used to get the decrypted password
     * @param password
     * @return decrypted password
     */
    public static String getPassword(String password) {
        ConfigReader reader = new ConfigReader();
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(reader.getKey());
        String decryptedPasswd = textEncryptor.decrypt(password);
        return decryptedPasswd;
    }
}
