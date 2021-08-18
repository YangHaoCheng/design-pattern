package yhc.java.algorithm.base.mytry;

public class MyQuickSort {
    public static void main(String[] args) {
        int[] a = {6,12,3,4,1,7,5,8,9,10};
        int[] ints = arraySort(a);
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
    }

    public static int[] arraySort(int[] nums){
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public static int[] quickSort(int[] nums, int low, int high){

        if(low < high){
            int index = partition(nums,low,high);
            quickSort(nums,low,index-1);
            quickSort(nums,index + 1,high);
        }

        return nums;
    }

    private static int partition(int[] nums, int low, int high) {

        //记录选取的标值
        int target = nums[low];

        while (low < high){

            //从后往前数找到第一个小于标值的数 记下下标
            while(low < high && nums[high] > target){
                high -- ;
            }

            //替换low值到标值位置
            if(low < high){
                nums[low] = nums[high];
            }

            //从前往后数，发现第一大于标值的数 记录下标
            while (low < high && nums[low] < target){
                low ++ ;
            }

            //替换大值到记录的high位置
            if(low < high){
                nums[high] = nums[low];
            }
        }

        //标值填坑
        nums[low] = target;

        return low;
    }


}
