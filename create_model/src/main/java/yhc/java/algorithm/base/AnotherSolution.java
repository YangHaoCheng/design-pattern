package yhc.java.algorithm.base;

class AnotherSolution {
    public int strStr(String origin, String aim) {
        if (origin == null || aim == null) {
            return 0;
        }
        if (origin.length() < aim.length()) {
            return -1;
        }
        //目标串匹配索
        int originIndex = 0;
        //模式串匹配索引
        int aimIndex = 0;
        // 成功匹配完终止条件：所有aim均成功匹配
        while (aimIndex < aim.length()) {
            // 针对origin匹配完，但aim未匹配完情况处理 如 mississippi sippia
            if (originIndex > origin.length() - 1) {
                return -1;
            }
            if (origin.charAt(originIndex) == aim.charAt(aimIndex)) {
                // 匹配则index均加1
                originIndex++;
                aimIndex++;
            } else {
                //在我们上面的样例中，第一次计算值为6，第二次值为13
                int nextCharIndex = originIndex - aimIndex + aim.length();
                //判断下一个目标字符（上面图里的那个绿框框）是否存在。
                if (nextCharIndex < origin.length()) {
                    // 判断目标字符在模式串中匹配到，返回最后一个匹配的index
                    int step = aim.lastIndexOf(origin.charAt(nextCharIndex));
                    if (step == -1) {
                        // 不存在的话，设置到目标字符的下一个元素
                        originIndex = nextCharIndex + 1;
                    } else {
                        // 存在的话，移动对应的数字（参考上文中的存在公式）
                        originIndex = nextCharIndex - step;
                    }
                    //模式串总是从第一个开始匹配
                    aimIndex = 0;
                } else {
                    return -1;
                }
            }
        }
        return originIndex - aimIndex;
    }
}
