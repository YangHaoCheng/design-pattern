package yhc.java.algorithm.base;

public class HeapSort {
    public static void main(String[] args) {

        int[] a = {3,1,23,12,10,8,11,9,18,13,7,6};
        int[] ints = sortHeap(a);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] sortHeap(int[] source) {
        int[] ints = sortHeap(source, source.length-1);
        return ints;
    }

    public static int[] sortHeap(int[] source,int index) {
        int tmp = 0;
        int i = 1;
        for(i = index; i >= 1 ; i--){
            if(i > 1 && source[i] < source[i/2]){
                tmp = source[i];
                source[i] = source[i/2];
                source[i/2] = tmp;
            }
        }
        return source;
    }
}
