package yhc.java.algorithm.base.string;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ReversStr {
    public static void main(String[] args) {
        String a = "10202044030615074357";
        String s = reverse(a);
        System.out.println(a);
        System.out.println(s);
    }

    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverse(String str){
        if(null == str){
            return null;
        }

        if("".equals(str)){
            return "";
        }

        char[] chars = str.toCharArray();
        char tmp = 'a';
        int length = chars.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            tmp = chars[length - 1 - i];
            chars[length - 1 - i] = chars[i];
            chars[i] = tmp;
        }

        return String.copyValueOf(chars);
    }
}
