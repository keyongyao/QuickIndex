package com.example.future.quickindex;

/**
 * Author: Future <br>
 * QQ: <br>
 * Description:<br>
 * date: 2016/10/26  12:07.
 */

public class Name implements Comparable<Name> {
    public String name;
    public String pinyin;

    public Name(String name) {
        this.name = name;
        pinyin = PinYinUtil.getPinYin(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

    @Override
    public int compareTo(Name other) {
        if (!Character.isLetter(other.pinyin.charAt(0))) {
            return 1;
        }

        return pinyin.compareTo(other.pinyin);
    }
}
