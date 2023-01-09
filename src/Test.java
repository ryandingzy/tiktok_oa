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
        int[] iArr = new int[]{2, 1, 3, 1, 1, 4};
        int[] oArr = s23.minSubArraysDivByK(iArr, 5);
        printArray(oArr);
    }


}
