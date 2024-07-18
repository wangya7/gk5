package wang.bannong.gk5.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class RSAUtils {

    final static String RSA = "RSA";

    /**
     * 生成2048位的RSA秘钥串
     */
    public static void generateRsaPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        System.out.println("公钥：" + base64Encode(keyPair.getPublic().getEncoded()));
        System.out.println("私钥：" + base64Encode(keyPair.getPrivate().getEncoded()));
    }

    /**
     * 将公钥字符串转成 RSAPublicKey 实例
     */
    public static RSAPublicKey getPublicKey(String publicKeyStr) {
        try {
            byte[] buffer = base64Decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将私钥字符串转成 RSAPrivateKey 实例
     */
    public static RSAPrivateKey getPrivateKey(String privateKeyStr) {
        try {
            byte[] buffer = base64Decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密
     */
    public static byte[] encrypt(String content, PublicKey publicKey) {
        Cipher cipher = null;
        byte[] bytes = null;
        try {
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            bytes = cipher.doFinal(content.getBytes(Constant.UTF8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * 私钥解密
     */
    public static String decrypt(byte[] content, PrivateKey privateKey) {
        String s = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            s = new String(cipher.doFinal(content), Constant.UTF8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
    }

    public static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String... args) throws UnsupportedEncodingException {

        String publicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsLp2NLUOqRdWItvH3zWJsdWvveUvWj/B\n" +
                        "+XRzRuzDTvsaMgHAfE7zpjEqFj4/BVw4LThOseLXwsxt4RHxEpykL/OMpMUz8MJmN3gKVID43tN7\n" +
                        "5H0b6IL46ymMNwOcPt9EfEGdnUaTJEwxG4VpKIGWxDOUnEybS7QuUztJlNMByxEYR69dLUSSOCM+\n" +
                        "TbQNIVeVVXixPhI43pb5umR/3mQ+XHTuAZ2zoMA4OsrEO/052Wdv66dmNtaK8eTwwrGcmHOCi5fQ\n" +
                        "SWT2gi3Vq592461w3H8cbdfHU+3cgS0HkG4u3D/cU8eK8lUSUKxIY9wcv2nDdNWpYOwi5oY06sNF\n" +
                        "9UpSwQIDAQAB";
        String privateKey =
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwunY0tQ6pF1Yi28ffNYmx1a+9\n" +
                        "5S9aP8H5dHNG7MNO+xoyAcB8TvOmMSoWPj8FXDgtOE6x4tfCzG3hEfESnKQv84ykxTPwwmY3eApU\n" +
                        "gPje03vkfRvogvjrKYw3A5w+30R8QZ2dRpMkTDEbhWkogZbEM5ScTJtLtC5TO0mU0wHLERhHr10t\n" +
                        "RJI4Iz5NtA0hV5VVeLE+Ejjelvm6ZH/eZD5cdO4BnbOgwDg6ysQ7/TnZZ2/rp2Y21orx5PDCsZyY\n" +
                        "c4KLl9BJZPaCLdWrn3bjrXDcfxxt18dT7dyBLQeQbi7cP9xTx4ryVRJQrEhj3By/acN01alg7CLm\n" +
                        "hjTqw0X1SlLBAgMBAAECggEBAKXDj6IqfYDO2cVCfrv3fJroQroMIRk2mlvABWxuIVeQoWZsTD6M\n" +
                        "0257yO0qH3SIpkyL9wxVKGmX1Cx65rK9aCdt+uqF6g0WWkgG+TF/828V0KxlSOsTBiWVR+ZGJaS3\n" +
                        "SIaACTV+//7Y1Grq/NK4klCfzPG5zSP58kWxY3gLXzA23PIDw9phTmDXJj0n7Xg73Ydf9wPbjpih\n" +
                        "etDVazvbJckZoyFhtmSBhsN6vEEwGo8MvuiCLa8zBD4iJ49DZ9mHzsZ5WPmVpP32uhjsEkpZUUcI\n" +
                        "U+TKY5fyOfZgYhqyVFPsYRblZNiMHDqHKJNtfNSHHx1PMB2PqnlAGGWoLGvP9kECgYEA6Pi2d2bw\n" +
                        "cJefQJsSr2yj4ongiCCvR4iHH/QR2nQGsqFJpXalUjG4MIpK/C/992oGkYwW9o6++djv0lh2BHb5\n" +
                        "sYBcH6SOM/OVjNomEOVHq5UV1eUE03Qoxnq7OVzBBY+2rS+1zRUaGvY2wKZrEBK8hPuITPUpxDYL\n" +
                        "UBWk8+HJHikCgYEAwjKHjAYIiHMT9sAAhyWTsdlTuhJRoPSCxR8OZrEbd9k00btc9tDWJmDLrXXp\n" +
                        "utHMXVVLRS9O9CN4q53y0WkUIh5TwmOvlN6DmDm5nonYcYhBUiyHGyC989gVSrSThA1YDxKVniwN\n" +
                        "obb5KuSK3VToodnff33ll/gaorVj8YiF8tkCgYEAuFzyUOVWi1OBxGezzr1jqAfCdxeefjZt8Hx+\n" +
                        "kGjXUA9RL68Hv1b3Uvptwsrb6SqvjgjVt0JZoXDqwIQeyQO6L5zLyoK3wicWh0OvchlvsTom8IDv\n" +
                        "Y8L97av8c7WSTqtfPgiOj4AOi52i0Kwh97NMGtTPFU/CQLMgGr02L4XTWZkCgYBPs09AH4XHAnvS\n" +
                        "DFDrJnfsSQZ82mHYDiHRZwKREOT2FhYAccYgoesSCy3sffU0MECQmZ3vwg/EUP6IaKWsXlTd/9Ze\n" +
                        "dG0uSvh1NFPDBk3nooU0hYPZlWGAP1HVvDDCxHs8jGMNbIUXtrGicfOwDNYA/xskBvaAnVnJJ2ri\n" +
                        "SSa4WQKBgBrL1+GRnY3N6Oxcbr0qikXedox2vVDhPg+9XVWX/L2/Wu8LoJQHJrn3OZXeCSJplxhf\n" +
                        "1V+H4Tu5E7BRhOb+96gb+hMsk/xh57ou3sIr4tU2izjXnEwyLv46ZRIIo/iP+5Buq0Pdf+6PQk+V\n" +
                        "HVZcyGWLbfjvf+1oIjpBP2qlg3Eo";

        String content = "Hello Bannong, 欢迎您入住我们酒店:)";

        byte[] bytes = encrypt(content, getPublicKey(publicKey));
        System.out.println("加密:\t" + new String(bytes, Constant.UTF8));
        System.out.println("加密:\t" + base64Encode(bytes));

        String puk = decrypt(bytes, getPrivateKey(privateKey));
        System.out.println("解密:" + puk);
    }
}
