package cz.cvut.fit.corsys.bl.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    /**
     * Not to reveal users password, we need to encode the raw password into a hash.
     * This hash is stored as String
     *
     * @param rawPassword
     * @return String representation of hashed password
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return HashUtil.getPasswordHash(rawPassword.toString());
    }

    /**
     * Checks if given passwords matches. Using hash
     *
     * @param rawPassword     of the input
     * @param encodedPassword from the database
     * @return true if match
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return HashUtil.getPasswordHash(rawPassword.toString()).equals(encodedPassword);
    }

}
