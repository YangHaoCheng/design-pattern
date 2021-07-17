package yhc.java.algorithm.base;

public class KMPSolugtion {

    public static void main(String[] args) {
        String source = "abcbbcbcda";
        String target = "bcbc";
//        int[] next = getNext(str.toCharArray(), str.length());
//        for (int i : next) {
//            System.out.print(i + " ");
//        }

        int index = findStr(source, target);
        System.out.println(index);

    }

    public static int findStr(String source, String target){
        if(null == source || null == target){
            return -1;
        }

        if(source.length() < target.length()){
            return -1;
        }

        int[] next = getNext(target.toCharArray(), target.length());
        int index = 0;

        for (int i = 0; i < source.length(); i++) {
            //进行回溯相同元素长度的位数
            while(index != 0 && source.charAt(i) != target.charAt(index)){
                //获取相同元素长度的前一位下表，得到相同元素的下标，然后从下一位开始继续匹配
                index = next[index-1] + 1;
            }

            if(source.charAt(i) == target.charAt(index)){
                index += 1;
            }

            if(index == target.length()){
                return i - index + 1;
            }
        }

        return  -1;
    }

    public static int[] getNext(char[] target,int len){
        int[] next = new int[len];

        //元素相同的长度，如果相同元素为1则一定是上一位，体现在下标需要减一，所以初始化为 -1
        int index = -1;
        next[0] = -1;

        //对目标str的char[]进行遍历
        for (int i = 1; i < target.length; i++) {
            //下标不是-1 且 不和相同长度前的数相同 则长度等同于相同长度前的长度
            while (index != -1 && target[index + 1] != target[i]){
                index = next[index];
            }

            //和前位元素相同则长度加一
            if(target[index + 1] == target[i]){
                index += 1;
            }

            //添加对应相同元素的长度
            next[i] = index;
        }

        return next;
    }


    //这一块比较难懂，不想看的同学可以忽略，了解大致含义即可，或者自己调试一下，看看运行情况
    //我会每一步都写上注释
    public static int[] next (char[] needle,int len) {
        //定义 next 数组
        int[] next = new int[len];
        // 初始化
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < len; ++i) {
            //我们此时知道了 [0,i-1]的最长前后缀，但是k+1的指向的值和i不相同时，我们则需要回溯
            //因为 next[k]就时用来记录子串的最长公共前后缀的尾坐标（即长度）
            //就要找 k+1前一个元素在next数组里的值,即next[k+1]
            while (k != -1 && needle[k + 1] != needle[i]) {
                k = next[k];
            }
            // 相同情况，就是 k的下一位，和 i 相同时，此时我们已经知道 [0,i-1]的最长前后缀
            //然后 k + 1 又和 i 相同，最长前后缀加1，即可
            if (needle[k+1] == needle[i]) {
                ++k;
            }
            next[i] = k;

        }
        return next;
    }
}
