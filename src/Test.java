public class Test {

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        Solution22 s22 = new Solution22();
        Solution23 s23 = new Solution23();

        // subarray
//        int[] iArr = new int[]{2, 1, 3, 1, 1, 4};
//        int[] oArr = s23.minSubArraysDivByK(iArr, 5);
//        printArray(oArr);

        // min sum
        int M = 10;
        int[] arr1 = new int[]{1, 3, 5, 7, 9, 8, 4, 2, 6, 2, 3, 4};
        int[] arr2 = new int[]{1, 3, 5, 1, 1, 9, 8, 1, 1, 2, 1, 5, 2, 3};
        int[] arr3 = new int[]{1, 5, 5, 1};
        System.out.println(s23.minSum(arr1, M));
        System.out.println(s23.minSum(arr2, M));
        System.out.println(s23.minSum(arr3, M));
    }


}
