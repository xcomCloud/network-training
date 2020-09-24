package com.xue.study.utils;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
public class HexConvertUtil {
    //hex转ascii
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }

    //ascii转hex
    public static String convertStrToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    //将16进制的byte数组转换成字符串
    public static String getBufHexStr(byte[] raw) {
        String HEXES = "0123456789ABCDEF";
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }
}
