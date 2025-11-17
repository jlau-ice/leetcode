package zs;

import java.util.*;

public class Solution {

    public static long removeZero(long num) {
        String s = String.valueOf(num);
        s = s.replace("0", "");
        return s.isEmpty() ? 0 : Long.parseLong(s);
    }

    public static long maxAlternatingScore(int[] nums) {
        int n = nums.length;
        int[] abs = new int[n];
        for (int i = 0; i < n; i++) {
            abs[i] = Math.abs(nums[i]);
        }
        Arrays.sort(abs);
        int k = (n + 1) / 2;
        long sum = 0;
        for (int i = n - 1; i >= n - k; i--) {
            sum += (long) abs[i] * abs[i];
        }
        for (int i = 0; i < n - k; i++) {
            sum -= (long) abs[i] * abs[i];
        }
        return sum;
    }

    public long stableArrays(int[] capacity) {
        int n = capacity.length;
        long[] pre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + capacity[i - 1];
        }
        Map<Integer, Map<Long, Integer>> map = new HashMap<>();
        long ans = 0;
        for (int r = 0; r < n; r++) {
            if (r >= 2) {
                int l = r - 2;
                int key = capacity[l];
                long value = pre[l + 1];
                map.putIfAbsent(key, new HashMap<>());
                Map<Long, Integer> innerMap = map.get(key);
                innerMap.put(value, innerMap.getOrDefault(value, 0) + 1);
            }
            int key_r = capacity[r];
            long target = pre[r] - key_r;
            if (map.containsKey(key_r)) {
                Map<Long, Integer> innerMap = map.get(key_r);
                ans += innerMap.getOrDefault(target, 0);
            }
        }
        return ans;
    }

    public long countGoodSubarrays(int[] nums, int k) {
        int n = nums.length;
        long T = 0;
        long prefixSum = 0;
        Map<Long, Integer> countMap = new HashMap<>();
        countMap.put(0L, 1);
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            long r = prefixSum % k;
            if (r < 0) r += k;
            T += countMap.getOrDefault(r, 0);
            countMap.put(r, countMap.getOrDefault(r, 0) + 1);
        }
        List<int[]> segments = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            int L = j - i;
            segments.add(new int[]{nums[i], L});
            i = j;
        }
        long dup = 0;
        for (int[] seg : segments) {
            long v = seg[0];
            int L = seg[1];
            for (int len = 1; len <= L; len++) {
                long product = v * len;
                if (product % k == 0) {
                    dup += (L - len);
                }
            }
        }
        return T - dup;
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        int start = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] a = new int[2];
            if (intervals[i][0] > intervals[i - 1][1]) {
                // 前面的都合并
                a[0] = start;
                a[1] = intervals[i - 1][1];
                list.add(a);
                start = intervals[i][0];
            } else {
                list.add(intervals[i - 1]);
                if (i == intervals.length - 1) {
                    list.add(intervals[i]);
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i;j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

}
