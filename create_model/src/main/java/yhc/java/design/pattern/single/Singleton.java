package yhc.java.design.pattern.single;

/**
 * 嵌套类单例
 */
public class Singleton {

    private Singleton() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton instance = new Singleton();
    }
    public static Singleton getInstance() {
        return Holder.instance;
    }
}

class TestSingle {
    private TestSingle(){}
    private String name;
    private String gender;

    private static class Holder{
        private static TestSingle instance = new TestSingle();


    }

    public static TestSingle getInstance(){ return Holder.instance; }

    public static void main(String[] args) {



    }
}
