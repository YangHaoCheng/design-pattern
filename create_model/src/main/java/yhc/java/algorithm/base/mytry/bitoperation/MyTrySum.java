package yhc.java.algorithm.base.mytry.bitoperation;

public class MyTrySum {
    public static void main(String[] args) {
        int i = delNums(2);
        System.out.println(i);
    }

    public static int sumNums(int n) {
        System.out.println("start : " + n);
        boolean b = n > 0 && ((n  += sumNums(n - 1)) > 0);
        System.out.println("end : " + n);
        return n;
    }

    public static int delNums(int n) {
        System.out.println("start : " + n);
        boolean b = n > 0 && ((n  -= delNums(n - 1)) > 0);
        System.out.println("end : " + n);
        return n;
    }

}
