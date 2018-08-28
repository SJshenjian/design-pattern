package util;

import junit.framework.Assert;
import org.junit.Test;
import state.service.impl.SoldOutState;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/8/16
 */
public class Base32UtilsTest {

    @Test
    public void testBase32() throws UnsupportedEncodingException {
        String origin = "/bss/ecif/customerDetail/page/index.sdo?ywType=1&ywxf=2&sylx=3&id=4";
        String base32EncoderString = Base32Utils.encode(origin.getBytes("UTF-8"));
        String urlEncoderString = java.net.URLEncoder.encode(base32EncoderString, "UTF-8");

        String urlDecoderString = java.net.URLDecoder.decode(urlEncoderString, "UTF-8");
        String base32DecoderString = new String(Base32Utils.decode(urlDecoderString), "UTF-8");
        Assert.assertEquals(origin, base32DecoderString);
    }

    @Test
    public void test() {
        System.out.println("96e79218965eb72c92a549dd5a330112".length());
    }
}
