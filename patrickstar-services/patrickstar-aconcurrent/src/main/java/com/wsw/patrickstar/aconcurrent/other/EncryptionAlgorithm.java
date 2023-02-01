package com.wsw.patrickstar.aconcurrent.other;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @Author WangSongWen
 * @Date: Created in 9:24 2021/2/20
 * @Description: 加密算法
 * <p>
 * 对称加密算法加密和解密使用的是同一份秘钥，解密是加密的逆运算。对称加密算法加密速度快，密文可逆，一旦秘钥文件泄露，就会导致原始数据暴露。
 * 对称加密的结果一般使用Base64算法编码，便于阅读和传输。不同的算法秘钥长度不同，秘钥长度越长，加密安全性越高。
 * <p>
 * 非对称加密和对称加密算法相比，多了一把秘钥，为双秘钥模式，一个公开称为公钥，一个保密称为私钥。遵循公钥加密私钥解密，或者私钥加密公钥解密。
 * <p>
 * 一般推荐使用非对称加密算法传输对称加密秘钥，双方数据加密用对称加密算法加解密。
 */
public class EncryptionAlgorithm {
    public static final String value = "wsw summer15";

    // 对称加密---DES算法
    public static void desSymmetry() throws Exception {
        System.out.println("待加密的原文: " + value);
        // 加密算法
        String algorithm = "DES";
        // 转换模式
        String transformation = "DES";
        // 实例化秘钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        // 初始化秘钥长度
        keyGenerator.init(56); // JDK8仅支持56位长度的DES秘钥
        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 实例化DES秘钥材料
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getEncoded());
        // 实例化秘钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);
        // 生成DES秘钥
        SecretKey desSecretKey = secretKeyFactory.generateSecret(desKeySpec);
        System.out.println("DES密钥: " + Base64.getEncoder().encodeToString(desSecretKey.getEncoded()));
        // 实例化密码对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 设置模式(ENCRYPT_MODE: 加密模式; DECRYPT_MODE: 解密模式)和指定秘钥
        cipher.init(Cipher.ENCRYPT_MODE, desSecretKey);
        // 加密
        byte[] encrypt = cipher.doFinal(value.getBytes());
        System.out.println("DES加密结果: " + Base64.getEncoder().encodeToString(encrypt));
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, desSecretKey);
        byte[] decrypt = cipher.doFinal(encrypt);
        System.out.println("DES解密结果: " + new String(decrypt));
    }

    // 对称加密---AES算法
    public static void aesSymmetry() throws Exception {
        System.out.println("待加密的原文: " + value);
        // 加密算法
        String algorithm = "AES";
        // 转换模式
        String transformation = "AES";
        // 实例化秘钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        // 初始化秘钥长度
        keyGenerator.init(256); // JDK8支持128位、192位和256位长度的AES秘钥
        // 生成秘钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 实例化AES秘钥材料
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), algorithm);
        System.out.println("AES密钥: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        // 实例化密码对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 设置模式(ENCRYPT_MODE: 加密模式; DECRYPT_MODE: 解密模式)和指定秘钥
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 加密
        byte[] encrypt = cipher.doFinal(value.getBytes());
        System.out.println("AES加密结果: " + Base64.getEncoder().encodeToString(encrypt));
        // 解密
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decrypt = cipher.doFinal(encrypt);
        System.out.println("AES解密结果: " + new String(decrypt));
    }

    // 非对称加密---RSA算法
    public static void dissymmetry() throws Exception {
        System.out.println("待加密的原文: " + value);
        // 加密算法
        String algorithm = "RSA";
        // 转换模式
        String transform = "RSA/ECB/PKCS1Padding";
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化,秘钥长度512~16384位,64倍数
        keyPairGenerator.initialize(512);
        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("RSA公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        // 公钥加密,私钥解密
        Cipher cipher = Cipher.getInstance(transform);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] pubEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA公钥加密结果: " + Base64.getEncoder().encodeToString(pubEncryptBytes));
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] priDecryptBytes = cipher.doFinal(pubEncryptBytes);
        System.out.println("RSA私钥解密结果: " + new String(priDecryptBytes));
        // 私钥加密,公钥解密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] priEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA私钥加密结果: " + Base64.getEncoder().encodeToString(priEncryptBytes));
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] pubDecryptBytes = cipher.doFinal(priEncryptBytes);
        System.out.println("RSA公钥解密结果: " + new String(pubDecryptBytes));
    }

    public static void main(String[] args) throws Exception {
        /*desSymmetry();
        aesSymmetry();*/
        dissymmetry();
    }
}
