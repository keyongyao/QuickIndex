package com.example.future.quickindex;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Author: Future <br>
 * QQ: <br>
 * Description:获取拼音工具类<br>
 * date: 2016/10/26  11:08.
 */

public class PinYinUtil {

    public static String getPinYin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  // 设置声调
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  //设置大小写
        char[] chars = chinese.toUpperCase().toCharArray();
        String pinyin = ""; // 字符串的拼音
        for (int i = 0; i < chars.length; i++) {
            if (Character.isSpaceChar(chars[i])) continue;
            if (chars[i] > 127) {
                try {
                    String[] charPiyin = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                    if (charPiyin != null) {
                        pinyin += charPiyin[0]; // 多音字
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            } else {
                pinyin += chars[i];
            }
        }
        return pinyin;
    }

}
