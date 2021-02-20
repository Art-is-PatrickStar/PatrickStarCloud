package com.wsw.patrickstar.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author WangSongWen
 * @Date: Created in 14:38 2021/2/20
 * @Description: 对称加密---AES加密
 */
@Component
public class AESSecurity {
    // 加密算法
    private static final String algorithm = "AES";
    // 转换模式
    private static final String transform = "AES";
    // 密钥
    private final SecretKeySpec secretKeySpec;

    public AESSecurity() throws Exception {
        // 实例化秘钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        // 初始化秘钥长度
        keyGenerator.init(256); // JDK8支持128位、192位和256位长度的AES秘钥
        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 实例化AES秘钥材料
        secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), algorithm);
        //System.out.println("AES密钥: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }

    /**
     * @description: 加密
     * @author: wangsongwen
     * @date: 2021/2/20 14:42
     **/
    public byte[] encrypt(String value) throws Exception {
        // 实例化密码对象
        Cipher encryptCipher = Cipher.getInstance(transform);
        // 设置模式(ENCRYPT_MODE: 加密模式; DECRYPT_MODE: 解密模式)和指定秘钥
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 加密
        byte[] encrypt = encryptCipher.doFinal(value.getBytes());
        //System.out.println("AES加密结果: " + Base64.getEncoder().encodeToString(encrypt));
        return encrypt;
    }

    /**
     * @description: 解密
     * @author: wangsongwen
     * @date: 2021/2/20 14:44
     **/
    public String decrypt(byte[] encrypt) throws Exception {
        // 实例化密码对象
        Cipher decryptCipher = Cipher.getInstance(transform);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 解密
        byte[] decrypt = decryptCipher.doFinal(encrypt);
        return new String(decrypt);
    }
}
