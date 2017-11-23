package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Encrypt {
    private static final Logger logger = LoggerFactory.getLogger(Encrypt.class);

    public static String md5(String txt) {

        return encrypt(txt, "MD5");
    }

    public static String sha(String txt) {

        return encrypt(txt, "SHA");
    }

    public static String sha1(String txt) {

        return encrypt(txt, "SHA1");
    }

    private static String encrypt(String txt, String algorithName) {

        if (txt == null || txt.trim().length() == 0) {
            return null;
        }

        if (algorithName == null || algorithName.trim().length() == 0) {
            algorithName = "MD5";
        }

        String result = null;

        try {
            MessageDigest m = MessageDigest.getInstance(algorithName);
            m.reset();
            m.update(txt.getBytes(Charset.forName("UTF-8")));
            byte[] bts = m.digest();

            return hex(bts);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    private static String hex(byte[] bts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bts.length; i++) {
            sb.append(Integer.toHexString((bts[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return sb.toString();
    }

    /**
     * HmacMD5算法
     *
     * @param msg       加密信息
     * @param keyString 秘钥
     * @return digest 结果
     */
    public static String hmac(String msg, String keyString) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("UTF-8"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

//    public static boolean checkSign(HttpServletRequest request, String appKey, String sign) throws UnsupportedEncodingException {
//        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
//        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
//            if (!entry.getKey().equals("sign"))
//                sortedMap.put(entry.getKey(), entry.getValue()[0]);
//        }
//        StringBuilder sb = new StringBuilder();
//        Iterator<Map.Entry<String, Object>> iterator = sortedMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//            sb.append(entry.getKey())
//                    .append("=")
//                    .append(entry.getValue().toString());
//            if (iterator.hasNext()) {
//                sb.append("&");
//            }
//        }
//        sb.append(appKey);
//        String mySign = Encrypt.md5(sb.toString());
//        logger.debug("checkSign: sign={},mySign={},str={}", sign, mySign, sb.toString());
//        return sign.toLowerCase().equals(mySign.toLowerCase());
//    }
//
//    public static String genSign(HttpServletRequest request, String appKey) throws UnsupportedEncodingException {
//        SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
//        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
//            if (!entry.getKey().equals("sign"))
//                sortedMap.put(entry.getKey(), entry.getValue()[0]);
//        }
//        StringBuilder sb = new StringBuilder();
//        Iterator<Map.Entry<String, Object>> iterator = sortedMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Object> entry = iterator.next();
//            sb.append(entry.getKey())
//                    .append("=")
//                    .append(entry.getValue().toString());
//            if (iterator.hasNext()) {
//                sb.append("&");
//            }
//        }
//        sb.append(appKey);
//        String mySign = Encrypt.md5(sb.toString());
//        logger.debug("genSign: Sign={},str={}", mySign, sb.toString());
//        return mySign;
//    }

//    public static boolean checkReportSign(Integer time, String sign) throws UnsupportedEncodingException {
//        StringBuilder sb = new StringBuilder();
//        sb.append("huyagame");
//        sb.append(time);
//        String mySign = Encrypt.md5(sb.toString());
//        logger.debug("checkReportSign: sign={},mySign={},str={}", sign, mySign, sb.toString());
//        return sign.toLowerCase().equals(mySign.toLowerCase());
//    }
}
