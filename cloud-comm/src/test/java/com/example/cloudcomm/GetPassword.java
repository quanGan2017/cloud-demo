package com.example.cloudcomm;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.jupiter.api.Test;

public class GetPassword {

    @Test
    public void druidEncrypt() throws Exception {
        //密码明文
        String password = "Gq20210703";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];

        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }
}
