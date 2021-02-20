package com.wsw.patrickstar.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @Author WangSongWen
 * @Date: Created in 14:38 2021/2/20
 * @Description: 非对称加密---RSA加密
 */
@Component
public class RSASecurity {
    // 加密算法
    private static final String algorithm = "RSA";
    // 转换模式
    private static final String transform = "RSA/ECB/PKCS1Padding";
    // 秘钥对
    private final KeyPair keyPair;
    private final Cipher cipher;

    public RSASecurity() throws Exception {
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化,秘钥长度512~16384位,64倍数
        keyPairGenerator.initialize(512);
        // 生成秘钥对
        keyPair = keyPairGenerator.generateKeyPair();
        cipher = Cipher.getInstance(transform);
    }

    /**
     * @description: 加密
     * @author: wangsongwen
     * @date: 2021/2/20 16:05
     **/
    public byte[] encrypt(String value) throws Exception {
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] pubEncryptBytes = cipher.doFinal(value.getBytes());
        //System.out.println("RSA公钥加密结果: " + Base64.getEncoder().encodeToString(pubEncryptBytes));
        return pubEncryptBytes;
    }

    /**
     * @description: 解密
     * @author: wangsongwen
     * @date: 2021/2/20 16:05
     **/
    public String decrypt(byte[] encrypt) throws Exception {
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] priDecryptBytes = cipher.doFinal(encrypt);
        //System.out.println("RSA私钥解密结果: " + new String(priDecryptBytes));
        return new String(priDecryptBytes);
    }
}
