package yhc.java.algorithm.base.search;

/**
 * 给你一个包含重复整数的数组 nums ，和一个整数 target 。
 *
 * 该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,1,2,2,3,4,5,6,7] 可能变为 [3,4,5,6,7,0,1,1,2,2] ）。
 *
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 */
public class RepetitionalBinarySearch {
    public static void main(String[] args) {

    }

    public static int findTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left+((right-left)>>1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }
            if (nums[mid] > nums[left]) {
                if (nums[mid] > target && target >= nums[left]) {
                    right = mid - 1;
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                }

            }else if (nums[mid] < nums[left]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
