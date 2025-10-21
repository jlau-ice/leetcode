import model.ListNode;
import model.TreeNode;

import java.util.*;

public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            if (!map.containsKey(sortStrings(str))) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortStrings(str), list);
            } else {
                map.get(sortStrings(str)).add(str);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(new ArrayList<>(entry.getValue()));
        }
        return ans;
    }

    public String sortStrings(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 1;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int count = 1;
                while (set.contains(num + 1)) {
                    count++;
                    num++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    //25题
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1 || head.next == null) {
            return head;
        }
        List<ListNode[]> list = new ArrayList<>();
        List<ListNode> list1 = new ArrayList<>();
        ListNode cur = head;
        ListNode curHead = head;
        int amount = 1;
        while (cur != null) {
            boolean flag = false;
            if (amount % k == 0) {
                flag = true;
                ListNode temp = cur.next;
                cur.next = null;
                list1.add(curHead);
                curHead = temp;
                cur = temp;
            }
            if (!flag) {
                cur = cur.next;
            }
            amount++;
        }
        for (ListNode a : list1) {
            list.add(reverse(a));
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i)[1].next = list.get(i + 1)[0];
        }
        list.get(list.size() - 1)[1].next = curHead;
        return list.get(0)[0];
    }

    public static ListNode[] reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{pre, head};
    }

    // 23
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        while (!checkNull(lists)) {
            pre.next = minHeader(lists);
            pre = pre.next;
        }
        return cur.next;
    }

    public static ListNode minHeader(ListNode[] lists) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            if (lists[i].val < min) {
                min = lists[i].val;
                minIndex = i;
            }
        }
        ListNode ListNode = lists[minIndex];
        lists[minIndex] = lists[minIndex].next;
        return ListNode;
    }

    public static boolean checkNull(ListNode[] lists) {
        int n = lists.length;
        int count = 0;
        for (ListNode cur : lists) {
            if (cur == null) {
                count++;
            }
        }
        return count == n;
    }

    // 148
    public ListNode sortList(ListNode head) {
        return sort(head);
    }

    public ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode l1 = sort(head);
        ListNode l2 = sort(slow);
        return merge(l1, l2);
    }

    // 合并两个有序
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    // 3 题
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            int len = len(s, i);
            max = Math.max(max, len);
        }
        return max;
    }

    public int len(String s, int i) {
        Set<Character> set = new HashSet<>();
        for (int j = i; j < s.length(); j++) {
            boolean flag = set.add(s.charAt(j));
            if (!flag) {
                return set.size();
            }
        }
        return set.size();
    }

    // 3题 滑动窗口解法
    public static int lengthOfLongestSubstring1(String s) {
        Set<Character> set = new HashSet<>();
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            }
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public static int nn(int n) {
        if (n == 1) {
            return 1;
        }
        return n * nn(n - 1);
    }

    public int climbStairs(int n) {
        int[] cache = new int[n + 1];
        return pal(n, cache);
    }

    public int pal(int n, int[] cache) {
        if (n == 1) {
            cache[n] = 1;
            return 1;
        }
        if (n == 2) {
            cache[n] = 2;
            return 2;
        }
        if (cache[n] != 0) {
            return cache[n];
        }
        cache[n] = pal(n - 1, cache) + pal(n - 2, cache);
        return pal(n - 1, cache) + pal(n - 2, cache);
    }


    int maxDep;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxDepth(root);
        return maxDep;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxDep = Math.max(maxDep, left + right);
        return Math.max(left, right) + 1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        if (nums[l] < nums[r - 1]) {
            return nums[l];
        }
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }


    public int[] searchRange(int[] nums, int target) {
        return new int[]{erfen(nums, target, true), erfen(nums, target, false)};
    }

    public static int erfen(int[] nums, int target, boolean f) {
        int l = 0;
        int r = nums.length;
        int rowIndex = -1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                rowIndex = mid;
                // f=true 继续往左
                if (f) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        return rowIndex;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l1 = 0;
        int r1 = m;
        int row = 0;
        while (l1 < r1) {
            int m1 = (r1 - l1) / 2 + l1;
            if (matrix[m1][0] > target) {
                r1 = m1;
            } else if (matrix[m1][0] < target) {
                l1 = m1 + 1;
            } else {
                row = m1;
            }
        }
        row = l1;
        int l2 = 0;
        int r2 = n;
        while (l2 < r2) {
            int m2 = (r2 - l2) / 2 + l2;
            if (matrix[row][m2] > target) {
                r2 = m2;
            } else if (matrix[row][m2] < target) {
                l2 = m2 + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] arr = new int[n1 + n2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                arr[k] = nums1[i];
                i++;
            } else if (nums1[i] > nums2[j]) {
                arr[k] = nums2[j];
                j++;
            }
            k++;
        }
        if (i == n1) {
            while (j < n2) {
                arr[k++] = nums2[j++];
            }
        } else {
            while (i < n1) {
                arr[k++] = nums1[i];
            }
        }
        if (arr.length % 2 == 0) {
            int m1 = arr[arr.length / 2];
            int m2 = arr[arr.length / 2 - 1];
            return (m1 + m2) / 2.0;
        } else {
            return arr[arr.length / 2];
        }
    }

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxSize = 0;
        while (i < j) {
            maxSize = Math.max(maxSize, Math.min(height[i], height[j]) * (j - i));
            if (height[j] > height[i]) {
                i++;
            } else {
                j++;
            }
        }
        return maxSize;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
            }
        }
        return res;
    }

    public int trap(int[] height) {
        // int l = 0;
        // int r = height.length - 1;
        // int maxL = 0;
        // int maxR = 0;
        // int sun = 0;
        // while (l < r) {
        //     maxL = Math.max(maxL, height[l]);
        //     maxR = Math.max(maxR, height[r]);
        //     if (height[l] < height[r]) {
        //         sun += maxL - height[l];
        //         l++;
        //     } else {
        //         sun += maxR - height[r];
        //         r--;
        //     }
        // }
        // return sun;
        // 动态规划
        int n = height.length;
        int[] maxLefts = new int[n];
        maxLefts[0] = height[0];
        for (int i = 1; i < n; i++) {
            maxLefts[i] = Math.max(maxLefts[i - 1], height[i]);
        }
        int[] maxRights = new int[n];
        maxRights[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRights[i] = Math.max(maxRights[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(maxLefts[i], maxRights[i]) - height[i];
        }
        return ans;
    }

    public int trap2(int[] height) {
        int n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        if (n <= 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                Integer left = stack.peek();
                int w = i - left - 1;
                ans += (Math.min(height[i], height[left]) - (height[top])) * w;
            }
            stack.push(i);
        }
        return ans;
    }

    public List<List<Integer>> generate(int numRows) {
        // 00
        // 10 11
        // 20 21 22
        // 30 31 32 33
        // 40 41 42 43 44
        // dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
        int[][] dp = new int[numRows][numRows];
        dp[0][0] = 1;
        // dp[1][0] = 1;
        // dp[2][1] = 1;
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> ls = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                ls.add(dp[i][j]);
            }
            res.add(ls);
        }
        return res;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        dp[0] = nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public static List<Integer> findAnagrams(String s, String p) {
        // s sort ->  hash
        // 左右指针 滑动 截取 —> sort -> hash
        int n = p.length();
        int i = 0;
        int r = n - 1;
        List<Integer> list = new ArrayList<>();
        while (r < s.length()) {
            if (hash(s.substring(i, r + 1), p)) {
                list.add(i);
            }
            i++;
            r++;
        }
        return list;
    }

    public static boolean hash(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] arr = new int[256];
        int n = s2.length();
        for (int i = 0; i < n; i++) {
            arr[s1.charAt(i)]++;
            arr[s2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) {
            return list;
        }
        int n = p.length();
        int[] countS = new int[26];
        int[] countP = new int[26];
        for (int i = 0; i < n; i++) {
            countP[p.charAt(i) - 'a']++;
            countS[s.charAt(i) - 'a']++;

        }
        if (Arrays.equals(countP, countS)) {
            list.add(0);
        }
        int m = s.length();
        for (int i = n; i < m; i++) {
            countS[s.charAt(i) - 'a']++;
            countS[s.charAt(i - n) - 'a']--;
            if (Arrays.equals(countP, countS)) {
                list.add(i - n + 1);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}
