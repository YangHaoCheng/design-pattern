package yhc.java.algorithm.base.string;

/**
 * 旋转字符串匹配是否存在匹配字符串,
 */
public class MyIndexOfStr {

    public static void main(String[] args) {
        String source = "baccd";
        String target = "ccdba";

        int repetition = getRepetition(source, target);
        System.out.println(repetition);
    }

    /**
     * 获取匹配字符串的重复值
     * @param target
     * @return
     */
    public static int[] getNext(String target){
        int[] next = new int[target.length()];
        next[0] = -1;
        int index = -1;

        for (int i = 1; i < target.length(); i++) {
            //重复元素后一位和当前位比较
            if(index != -1 && target.charAt(index + 1) != target.charAt(i)){
                //不相同 则回溯标为上一个重复元素的长度 + 1
                index = next[index];
            }

            if(target.charAt(index + 1) == target.charAt(i)){
                index += 1;
            }

            next[i] = index;
        }

        return next;
    }

    public static int getRepetition(String source, String target){

        //向后调整源字符串顺序可以看做是 source + source 的字符串
        String newSource = source + source;
        int[] next = getNext(target);
        char[] chars = newSource.toCharArray();
        int index = 0;

        for (int i = 0; i < chars.length; i++) {
            while(index != 0 && target.charAt(index) != chars[i]){
                index = next[index - 1] + 1;
            }

            if(target.charAt(index) == chars[i]){
                index += 1;
            }

            if(index == target.length()){
                return i - index + 1;
            }
        }

        return -1;
    }
}
