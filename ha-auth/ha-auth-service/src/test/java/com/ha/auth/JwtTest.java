package com.ha.auth;

import com.ha.auth.utils.RsaUtils;
import org.junit.Test;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
public class JwtTest {

   // private static final String pubKeyPath = "G:\\tmp\\rsa\\rsa.pub";
   // private static final String priKeyPath = "G:\\tmp\\rsa\\rsa.pri";
    private static final String pubKeyPath = "/Users/weirdo/zml/ha-car/rsa.pub";

    private static final String priKeyPath = "/Users/weirdo/zml/ha-car/rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "hAfc!^88*");
    }

    //@Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }


    @Test
    public void date(){
        System.out.println(new Date());
    }
}