import java.util.*;

public class Solution {
    // Inversions
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

    // Ancestral Names
    public static int romanToInt(String s) {
        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        int last = 0;
        for (int i = n - 1; i >= 0; i--) {
            int value = map.get(s.charAt(i));
            if (value >= last) {
                res += value;
            } else {
                res -= value;
            }
        }
        return res;
    }

    class Name implements Comparable<Name> {
        public String str;
        public String name;
        public Integer number; // 这里应该要用Integer形式

        public Name() {
        }

        public Name(String str) {
            this.str = str;
            String[] strs = str.split(" ");
            name = strs[0];
            number = romanToInt(strs[1]);
        }

        @Override
        public int compareTo(Name n) {
            int res = this.name.compareTo(n.name);
            return res == 0 ? this.number.compareTo(n.number) : res;
        }
    }

    public void sortNames(String[] names) {
        int n = names.length;

        Name[] arr = new Name[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Name(names[i]);
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            names[i] = arr[i].str;
        }
    }

    public void testNames() {
        String[] names = new String[]{
                "Steven XL",
                "Steven XVI",
                "David IX",
                "Mary XV",
                "Mary XIII",
                "Mary XX"
        };
        sortNames(names);
        for (String name : names) {
            System.out.println(name);
        }
    }

    // 1. Roman number --> integer
    // 2. sort string


    // shared interest
    public int product(List<Integer> from, List<Integer> to, List<Integer> w) {
        int n = 0;
        for (int i = 0; i < from.size(); i++) {
            n = Math.max(n, from.get(i));
            n = Math.max(n, to.get(i));
        }

        HashMap<Integer, boolean[]> map = new HashMap<>();
        // add to hashmap
        for (int i = 0; i < w.size(); i++) {
            int weight = w.get(i);
            if (!map.containsKey(weight)) {
                map.put(weight, new boolean[n]);
            }
            map.get(weight)[from.get(i) - 1] = true;
            map.get(weight)[to.get(i) - 1] = true;
        }

        HashMap<String, Integer> pairs = new HashMap<>();
        int max = 0;
        int maxProduct = 0;
        for (boolean[] arr : map.values()) {
            for (int i = 0; i < n; i++) {
                if (!arr[i])
                    continue;
                for (int j = i + 1; j < n; j++) {
                    if (!arr[j])
                        continue;
                    String key = (i + 1) + "-" + (j + 1);
                    pairs.put(key, pairs.getOrDefault(key, 0) + 1);
                    if (pairs.get(key) == max) {
                        maxProduct = Math.max(maxProduct, strToProduct(key));
                    } else if (pairs.get(key) > max) {
                        maxProduct = strToProduct(key);
                        max = pairs.get(key);
                    }
                }
            }
            return maxProduct;
        }
    }

    private int strToProduct(String str) {
        String[] strs = str.split("-");
        return Integer.parseInt(strs[0]) * Integer.parseInt(strs[1]);
    }

    public void testProduct() {
        List<Integer> from = Arrays.asList(1, 1, 2, 2, 2);
        List<Integer> to = Arrays.asList(2, 2, 3, 3, 4);
        List<Integer> w = Arrays.asList(1, 2, 1, 3, 3);
        System.out.println(product(from, to, w));
    }
}
