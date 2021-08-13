package yhc.java.algorithm.base.search;

/**
 * 给你一个整数数组 nums ，和一个整数 target 。

 该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。

 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 */
public class UnRepetitionalBinarySearch {
    public static void main(String[] args) {
        int[] source = {4,5,6,7,8,0,1,2,3};
        int target = 1;
        int targetIndex = findTarget(source, target);
        System.out.println(targetIndex);
    }

    public static int findTarget(int[] source, int target){
        int left = 0;
        int right = source.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(target == source[mid]){
                return mid;
            }

            //不同数组的情况 只能一步步移动
            if(source[left] > source[mid]){
                if(target > source[left]){
                    left = left + 1;
                }else if(target > source[mid]){
                    left = mid;
                }else if(target < source[mid]){
                    right = right - 1;
                }
            }else if(source[left] <= source[mid]){
                //落在有序数组内 则二分查找
                if(target < source[mid] && target > source[left]){
                    right = mid;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
