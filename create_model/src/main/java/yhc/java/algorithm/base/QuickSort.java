package yhc.java.algorithm.base;

class QuickSort {
    private static final int INSERTION_SORT_MAX_LENGTH = 7;

    public static void main(String[] args) {
        int[] a = {2,1,3,4,8,6,5,7,9};
        int[] ints = sortArray(a);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public static int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public static void quickSort (int[] nums, int low, int high) {

        if (high - low <= INSERTION_SORT_MAX_LENGTH) {
            insertSort(nums,low,high);
            return;
        }
        int index = partition(nums,low,high);
        quickSort(nums,low,index-1);
        quickSort(nums,index+1,high);
    }

    public static int partition (int[] nums, int low, int high) {
        //三数取中，大家也可以使用其他方法
        int mid = low + ((high-low) >> 1);
        if (nums[low] > nums[high]) swap(nums,low,high);
        if (nums[mid] > nums[high]) swap(nums,mid,high);
        if (nums[mid] > nums[low]) swap(nums,mid,low);
        int pivot = nums[low];
        int start = low;
        while (low < high) {
            while (low < high && nums[high] >= pivot) high--;
            while (low < high && nums[low] <= pivot) low++;
            if (low >= high) break;
            swap(nums, low, high);
        }
        swap(nums,start,low);
        return low;
    }

    public static void insertSort (int[] nums, int low, int high) {

        for (int i = low+1; i <= high; ++i) {
            int temp = nums[i];
            int j;
            for (j = i-1; j >= 0; --j) {
                if (temp < nums[j]) {
                    nums[j+1] = nums[j];
                    continue;
                }
                break;
            }
            nums[j+1] = temp;
        }
    }

    public static void swap (int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //my test
    public int[] quickSortArray(int[] source, int low, int high){

        int target = source[0];
        // 1.确认基准位
        // 2.high指针从后往前找小于基准数的
        // 3.low指针从前往后找大于基准位的
        //low，high指针确认后，进行交换
        while(low < high){
            if(source[high] >= target) high --;
            if(source[low] <= target ) low ++;



        }
        return  new int[]{};
    }



}
