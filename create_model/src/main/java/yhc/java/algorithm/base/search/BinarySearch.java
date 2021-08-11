package yhc.java.algorithm.base.search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] i = {1,3,5,6,7,8,9,10,12,15};
        int i1 = binarySearch1(i, 13, 0, i.length - 1);
        System.out.println(i1);

    }

    public static int binarySearch(int[] source, int target) {

        int left = 0;
        int right = source.length - 1;
        //计算中间下标
        int mid = left + (right - left) / 2;

        while(true){
            int midSource = source[mid];
            if (!(midSource != target || left < right)) break;
            if(midSource < target){
                left = mid;
            }else if(midSource > target){
                right = mid;
            }else{
                return mid;
            }

            if(left <= right){
                mid = left + (right - left) / 2;
            }else{
                return -1 ;
            }
        }

        return mid;
    }

    public static int binarySearch1(int[] source, int target, int left, int right){

        if(right > left){
            int mid = left + (right - left) / 2;
            if(target == source[mid]){
                return mid;
            }else if(target > source[mid]){
                return binarySearch1(source, target, mid + 1, right);
            }else {
                return binarySearch1(source, target, left, mid - 1);
            }
        }

        return -1;
    }

    public static int binarySearch2(int[] nums,int target,int left, int right) {

        if (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                //查找成功
                return  mid;
            }else if (nums[mid] > target) {
                //新的区间,左半区间
                return binarySearch2(nums,target,left,mid-1);
            }else if (nums[mid] < target) {
                //新的区间，右半区间
                return binarySearch2(nums,target,mid+1,right);
            }
        }
        //不存在返回-1
        return -1;
    }
}
