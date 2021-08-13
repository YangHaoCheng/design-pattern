package yhc.java.algorithm.base.string;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 */
public class FindLastString {
    public static void main(String[] args) {
        String source = "We Are The World ";
        int lastWord = findLastWord(source);
        System.out.println(lastWord);
    }

    public static int findLastWord(String source) {
        //不为0的计数
        int count = 0;

        for (int i = source.length() - 1; i >= 0; i--) {
            if(' ' != (source.charAt(i))){
                count++ ;
            }else if(' ' == (source.charAt(i)) && count == 0){ //开头为' '的挑过
                continue;
            }else if(' ' ==(source.charAt(i)) && count > 0){ //有计数的 第一个 ' ' 跳出
                break;
            }
        }

        return count;
    }
}
