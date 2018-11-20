package cz.cvut.fit.corsys.bl.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CorsysPasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return HashUtil.getPasswordHash(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return HashUtil.getPasswordHash(rawPassword.toString()).equals(encodedPassword);
    }

}
