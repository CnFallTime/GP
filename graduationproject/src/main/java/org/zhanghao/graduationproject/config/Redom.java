package org.zhanghao.graduationproject.config;
import org.springframework.stereotype.Component;

@Component
public class Redom {

    private String PWD;

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String CreateRedom(){
        int i = (int) (((Math.random() * 9) + 1) * 100000);
        String s = String.valueOf(i);
        setPWD(s);
       return  s;
    }

}
