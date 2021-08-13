package yhc.java.algorithm.base.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 查找第一个不重复的字母
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 */
public class FindFirstNonDuplicateStr {

    public static void main(String[] args) {
        int getsource = findIndex("getgsource");
        System.out.println(getsource);
    }

    public static int findIndex(String source) {
        HashMap<Character, Integer> map = new HashMap<>(26);
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Integer index = map.getOrDefault(chars[i], 0);
            index += 1;
            map.put(chars[i],index);
        }

        for (int i = 0; i < chars.length; i++) {
            if(map.get(chars[i]) == 1){
                return i;
            }
        }

        return -1;
    }
}
