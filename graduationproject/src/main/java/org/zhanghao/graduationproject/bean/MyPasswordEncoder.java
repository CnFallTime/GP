package org.zhanghao.graduationproject.bean;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 张昊
 * @date 2019/10/6 - 21:46
 **/
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
