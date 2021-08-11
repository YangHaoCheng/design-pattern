package yhc.java.algorithm.base.string;

public class MyIndexOfStr {

    public static void main(String[] args) {

    }

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
}
