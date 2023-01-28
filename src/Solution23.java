import java.util.*;

public class Solution23 {
    // 子序列之和整除，HashMap
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<Integer, Integer>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem : nums) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % k + k) % k;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }

    // 返回最短的subarray
    public int[] minSubArraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<>();
        int curLen = Integer.MAX_VALUE;
        int[] res = new int[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int r = (nums[i] % k + k) % k;
            if (r == 0) {
                return new int[]{nums[i]};
            }
            sum += nums[i];
            r = (sum % k + k) % k;
            if (r == 0 && curLen > i + 1) {
                curLen = i + 1;
                res = Arrays.copyOfRange(nums, 0, i + 1);
            }
            if (record.containsKey(r) && curLen > i - record.get(r)) {
                curLen = i - record.get(r);
                res = Arrays.copyOfRange(nums, record.get(r) + 1, i + 1);
            }
            record.put(r, i);
        }
        return res;
    }

    // Divide an array into subarrays with sum no greater than M
    public int minSum(int[] nums, int M) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int l = i;
            int sum = nums[i];
            int max = nums[i];
            dp[i] = dp[i-1] + max;
            while (l - 1 >= 0 && nums[l-1] + sum <= M) {
                l--;
                sum += nums[l];
                max = Math.max(max, nums[l]);
                if (l == 0) {
                    dp[i] = Math.min(dp[i], max);
                } else {
                    dp[i] = Math.min(dp[i], dp[l-1] + max);
                }
            }
        }
        return dp[n-1];
    }

    // merge intervals
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    // Tom and Jerry
//    public int[] tomNJerry(int[][] catSessions, int[][] mouseSessions) {
//        // select
//        int mouseMin = Integer.MAX_VALUE;
//        int mouseMax = Integer.MIN_VALUE;
//        for (int[] mouse: mouseSessions) {
//            mouseMin = Math.min(mouseMin, mouse[0]);
//            mouseMax = Math.max(mouseMax, mouse[1]);
//        }
//        List<int[]> cat = new ArrayList<>();
//        int catMin = Integer.MAX_VALUE;
//        int catMax = Integer.MIN_VALUE;
//        for (int[] c : catSessions) {
//            if (c[0] < mouseMax && c[1] > mouseMin) {
//                cat.add(c);
//                catMin = Math.min(catMin, c[0]);
//                catMax = Math.max(catMax, c[1]);
//            }
//        }
//
//        List<int[]> mouse = new ArrayList<>();
//        for (int[] m : mouseSessions) {
//            if (m[0] < catMax && m[1] > catMin) {
//                mouse.add(m);
//            }
//        }
//
//        // sort
//        Collections.sort(cat, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) return o1[1] - o2[1];
//                return o1[0] - o2[0];
//            }
//        });
//
//        Collections.sort(mouse, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) return o1[1] - o2[1];
//                return o1[0] - o2[0];
//            }
//        });
//
//    }

    // Junie's Birthday

    private static int getNumOfSubarray(List<Integer> nums, int start, int end, int d) {
        Map<Integer, Integer> pMap = new HashMap<>();
        pMap.put(0, 1);
        int res = 0;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums.get(i);
            int rem = (sum % d + d) % d;
            int count = pMap.getOrDefault(rem, 0);
            res += count;
            pMap.put(rem, count + 1);
        }
        return res;
    }
    public List<Integer> birthday(List<Integer> nums, List<List<Integer>> questions) {
        List<Integer> res = new ArrayList<>();
        for (List<Integer> q : questions) {
            int start = q.get(0);
            int end = q.get(1);
            int d = q.get(2);
            res.add(getNumOfSubarray(nums, start, end, d));
        }
        return res;
    }

    // Minimum Resistance
    public int minResist(List<List<Integer>> matrix) {
        int n = matrix.size();
        int[] minSum = new int[n];
        for (int i = 0; i < n; i++) {
            minSum[i] = matrix.get(i).get(n-1);
        }
        for (int j = n - 2; j >= 0; j--) {
            int[] newArr = new int[n];
            newArr[0] = Math.min(minSum[0], minSum[1]) + matrix.get(0).get(j);
            newArr[n-1] = Math.min(minSum[n-1], minSum[n-2]) + matrix.get(n-1).get(j);
            for (int i = 1; i < n - 1; i++) {
                newArr[i] = Math.min(Math.min(minSum[i-1], minSum[i]), minSum[i+1]) + matrix.get(i).get(j);
            }
            minSum = newArr;
        }
        int res = minSum[0];
        for (int i = 1; i < n; i++) {
            res = Math.min(minSum[i], res);
        }
        return res;
    }
}
