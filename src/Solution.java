import java.util.Arrays;

public class Solution {
    // swap
    private static long countInversions(int[] a) {
        int n = a.length;
        // base case
        if (n <= 1) return 0;

        // recursive case
        int mid = n >> 1;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);
        long inversions = countInversions(left) + countInversions(right);

        int range = n - mid;
        int iLeft = 0;
        int iRight = 0;
        for (int i = 0; i < n; i++) {
            if (iLeft < mid && (iRight >= range || left[iLeft] <= right[iRight])) {
                a[i] = left[iLeft++];
                inversions += iRight;
            } else if (iRight < range) {
                a[i] = right[iRight++];
            }
        }
        return inversions;
    }


    // Circular Printer
    public static long getTime(String s) {
        char curr = 'A';
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            char next = s.charAt(i);
            int gap = Math.abs(next - curr);
            if (gap <= 13) res += gap;
            else res += 26 - gap;
            curr = next;
        }
        return res;
    }

    public static void testGetTime() {
        String str = "AZGB";
        System.out.println(getTime(str));
    }
}
