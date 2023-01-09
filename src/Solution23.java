import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

}
