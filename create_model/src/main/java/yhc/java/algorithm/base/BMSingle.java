package yhc.java.algorithm.base;

public class BMSingle {

    public static void main(String[] args) {
        String source = "useabeuseabc";
        String target = "abeu";
        int str = findStr(source, target);
        int strIndex = strStr(source, target);
        System.out.println(str);
        System.out.println(strIndex);
    }

    public static int findStr(String source, String target){
        if(null == source){
            return -1;
        }

        if(source.length() <= 1 || source.length() < target.length()){
            return -1;
        }

        int i,j;
        int index = 0;

        for ( i = 0, j = 0 ; i < source.length() && j < target.length(); i++) {
            if(source.charAt(i) == target.charAt(j)){
                j += 1;
            }else{
                i = i - j;
                j = 0;
            }
        }

        if(j == target.length()){
            index = i - j;
        }else {
            index = -1;
        }

        return index;
    }

    public static int strStr(String haystack, String needle) {
        if(null == haystack){
            return -1;
        }

        if(haystack.length() <= 1 || haystack.length() < needle.length()){
            return -1;
        }

        //i代表主串指针，j模式串
        int i,j;
        //主串长度和模式串长度
        int halen = haystack.length();
        int nelen = needle.length();
        //循环条件，这里只有 i 增长
        for (i = 0 , j = 0; i < halen && j < nelen; ++i) {
            //相同时，则移动 j 指针
            if (haystack.charAt(i) == needle.charAt(j)) {
                ++j;
            } else {
                //不匹配时，将 j 重新指向模式串的头部，将 i 本次匹配的开始位置的下一字符
                i -= j;
                j = 0;
            }
        }
        //查询成功时返回索引，查询失败时返回 -1；
        int renum = j == nelen ? i - nelen : -1;
        return renum;

    }
}
