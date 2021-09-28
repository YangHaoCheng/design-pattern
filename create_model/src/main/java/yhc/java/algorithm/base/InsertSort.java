package yhc.java.algorithm.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class InsertSort {

    public static void main(String[] args) {
        int[] source = {2,1,7,3,6,4,5,9,8};
        int[] sortedArray = sortArray(source);

        for (int i : sortedArray) {
            System.out.println(i + " ");
        }
    }

    public static int[] sortArray(int[] nums) {
        //注意 i 的初始值为 1，也就是第二个元素开始
        for (int i = 1; i < nums.length; ++i) {
            //待排序的值
            int temp = nums[i];
            //需要注意
            int j;
            for (j = i-1; j >= 0; --j) {
                //找到合适位置
                if (temp < nums[j]) {
                    nums[j+1] = nums[j];
                    continue;
                }
                //跳出循环
                break;
            }
            //插入到合适位置，这也就是我们没有在 for 循环内定义变量的原因
            nums[j+1] = temp;
        }
        return nums;
    }

    public static int[] mySortArray(int[] source){

        for (int i = 1; i < source.length; i++) {
            int tmp = source[i];
            int j;
            for(j = i-1; j >= 0; j--){
                if(tmp < source[j]){
                    source[j+1] = source[j];
                    continue;
                }
                break;
            }
            source[j] = tmp;
        }

        return new int[0];
    }

}
class Solution {
    public int[] sortArray(int[] nums) {
        //注意 i 的初始值为 1，也就是第二个元素开始
        for (int i = 1; i < nums.length; ++i) {
            //待排序的值
            int temp = nums[i];
            //需要注意
            int j;
            for (j = i-1; j >= 0; --j) {
                //找到合适位置
                if (temp < nums[j]) {
                    nums[j+1] = nums[j];
                    continue;
                }
                //跳出循环
                break;
            }
            //插入到合适位置，这也就是我们没有在 for 循环内定义变量的原因
            nums[j+1] = temp;
        }
        return nums;
    }
}
