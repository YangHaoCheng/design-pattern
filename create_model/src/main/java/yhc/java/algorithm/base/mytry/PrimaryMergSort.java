package yhc.java.algorithm.base.mytry;

public class PrimaryMergSort {
    public static void main(String[] args) {
        int[] nums = {1,9,8,7};
        int[] ints = sortArray(nums);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(i + " ");
        }
    }

    public static int[] sortArray(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return nums;
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }
    //归并
    public static void merge(int[] arr,int left, int mid, int right) {
        //第一步，定义一个新的临时数组
        int[] temparr = new int[right -left + 1];
        int temp1 = left, temp2 = mid + 1;
        int index = 0;
        //对应第二步，比较每个指针指向的值，小的存入大集合
        while (temp1 <= mid && temp2 <= right) {
            if (arr[temp1] <= arr[temp2]) {
                temparr[index++] = arr[temp1++];
            } else {
                temparr[index++] = arr[temp2++];
            }
        }
        //对应第三步，将某一小集合的剩余元素存到大集合中
        if (temp1 <= mid) System.arraycopy(arr, temp1, temparr, index, mid - temp1 + 1);
        if (temp2 <= right) System.arraycopy(arr, temp2, temparr, index, right -temp2 + 1);     //将大集合的元素复制回原数组
        System.arraycopy(temparr,0,arr,0+left,right-left+1);
    }
}
