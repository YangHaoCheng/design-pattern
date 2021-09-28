package yhc.java.algorithm.base.mytry;

public class MyMergSort {
    public static void main(String[] args) {
        int[] p = {1,5,8,2,4,6,7,9,3};
        int[] ints = sortArray(p);
        System.out.println("++++++++++++++++++++++++++++");
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public static int[] sortArray (int[] nums) {
        //代表子集合大小，1，2，4，8，16.....
        int k = 1;
        int len = nums.length;
        while (k < len) {
            mergePass(nums,k,len);
            k *= 2;
            System.out.println("----------"+ k +"--------- \n");
            for (int num : nums) {
                System.out.print(num+" ");
            }
        }
        return nums;

    }

    public static void mergePass (int[] array, int k, int len) {

        int i;
        for (i = 0; i < len-2*k; i += 2*k) {
            int right = i;
            int mid = i+k-1;
            int left = i+2*k-1;
            //归并
            merge(array,i,i+k-1,i+2*k-1);
        }
        //归并最后两个序列
        if (i + k < len) {
            merge(array,i,i+k-1,len-1);
        }

    }

    public static void merge (int[] arr,int left, int mid, int right) {
        //第一步，定义一个新的临时数组
        int[] temparr = new int[right - left + 1];
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
        if (temp1 <= mid){

            System.arraycopy(arr, temp1, temparr, index, mid - temp1 + 1);
        }
        if (temp2 <= right) {

            System.arraycopy(arr, temp2, temparr, index, right -temp2 + 1);
        }

        System.out.print("temparr : \n");
        for (int i : temparr) {
            System.out.print(i + " ");
        }
        //将大集合的元素复制回原数组
        System.arraycopy(temparr,0,arr,0+left,right-left+1);
        System.out.print("arr : \n");
        for (int i : temparr) {
            System.out.print(i + " ");
        }
    }
}