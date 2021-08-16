package yhc.java.algorithm.base;

public class QuickSort {

    public static void main(String[] args) {
        int[] source = {2,1,4,6,7,10,0,8,9};
        int[] sort = sortArray(source);
        for (int i : sort) {
            System.out.print(i + " ");
        }
    }

    public static int[] sortArray(int[] nums) {

        quickSort(nums,0,nums.length-1);
        return nums;

    }
    public static void quickSort (int[] nums, int low, int high) {

        if (low < high) {
            int index = partition(nums,low,high);
            quickSort(nums,low,index-1);
            quickSort(nums,index+1,high);
        }

    }
    public static int partition (int[] nums, int low, int high) {

        int pivot = nums[low];
        while (low < high) {
            //移动hight指针
            while (low < high && nums[high] >= pivot) {
                high--;
            }
            //填坑
            if (low < high) nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) {
                low++;
            }
            //填坑
            if (low < high) nums[high] = nums[low];
        }
        //基准数放到合适的位置
        nums[low] = pivot;
        return low;
    }
}
