package yhc.java.algorithm.base.mytry;

/**
 * @author ：_my
 * @date ：Created in 2021/12/9 10:03
 * @description： 从数列中挑出一个元素，称为“基准”（pivot）;
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 * 递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。
 * 虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
 * @version: 1.0
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int source[] = {3,5,4,2,6,8,7,9,11,10};
    }

    public static void sortArray(int[] source){
        sort(source, 0,source.length - 1);
    }

    private static void sort(int[] source,int left, int right) {
        if(left < right){
            int pivot = getPivot(source,left, right);


        }

    }

    private static int getPivot(int[] source,int left, int right) {
        int mid = left + ((right - left) >> 2);
        int pivot = source[mid];
        int low,high;

        for(int i = left;i < right-1;i++){
            if(source[i] > pivot){
                high = i;
            }

        }


        return mid;
    }
}
