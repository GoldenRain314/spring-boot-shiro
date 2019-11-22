package cn.qiucode.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wuming on 2017/12/15.
 */
public class MD5util {

    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @return md5后的密文
     */
    public static String md5(String input) {
        byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }
    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @param salt 随机数
     * @return string
     */
    public static String generatePasswordMD5(String input, String salt) {
        if(StringUtils.isEmpty(salt)) {
            salt = "";
        }
        return md5(md5(salt + md5(input+salt)));
    }

    public static void main(String [] args){
        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations = 2;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
