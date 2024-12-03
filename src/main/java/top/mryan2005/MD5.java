package top.mryan2005;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5 {
    public Hash generateMd5Hash(String input) {
        Hash hash = new Hash();
        hash.hash = DigestUtils.md5Hex(input);
        hash.isSalted = false;
        return hash;
    }

    public Hash generateMd5HashPlusSalt(String input) {
        Hash hash = new Hash();
        hash.salt = new Random().nextInt(90000) + 10000 + "";
        hash.hash = DigestUtils.md5Hex(input + hash.salt);
        hash.isSalted = true;
        return hash;
    }

    public boolean compareMd5Hash(String input, String inputSalt, String hash) {
        return DigestUtils.md5Hex(input + inputSalt).equals(hash);
    }

    public boolean compareMd5Hash(String input, String hash) {
        return DigestUtils.md5Hex(input).equals(hash);
    }

    public static void main(String[] args) {
        MD5 md5 = new MD5();
        Hash hash = md5.generateMd5Hash("password");
        System.out.println(hash.hash);
        System.out.println(hash.isSalted);
        Hash hashSalt = md5.generateMd5HashPlusSalt("password");
        System.out.println(hashSalt.hash);
        System.out.println(hashSalt.salt);
        System.out.println(hashSalt.isSalted);
        System.out.println(md5.compareMd5Hash("password", hash.hash));
        System.out.println(md5.compareMd5Hash("password", hashSalt.salt, hashSalt.hash));
    }
}
