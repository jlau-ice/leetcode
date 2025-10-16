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
        return nums;
    }

    public static int erfen(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                return mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,333};
        int erfen = erfen(arr, 2);
        System.out.println(erfen);
    }
}
