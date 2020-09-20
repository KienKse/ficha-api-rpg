package ks.roleplaying.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    /**
     * Generate a hash using BCrypt.
     *
     * @param password
     * @return String
     */
    public static String gerarBCrypt(String password) {
        if (password == null) {
            return password;
        }

        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(password);
    }

    /**
     * Verifica se a password é válida.
     *
     * @param password
     * @param passwordEncoded
     * @return boolean
     */
    public static boolean validPassword(String password, String passwordEncoded) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.matches(password, passwordEncoded);
    }

}
