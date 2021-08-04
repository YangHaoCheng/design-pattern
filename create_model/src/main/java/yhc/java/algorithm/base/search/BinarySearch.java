package yhc.java.algorithm.base.search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] i = {1,3,5,6,7,8,9,10,12,15};
        int i1 = binarySearch(i, 14);
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

}
