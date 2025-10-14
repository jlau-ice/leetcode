import model.ListNode;

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
        for(ListNode a : list1) {
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

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        listNode2.next = new ListNode(5);
        listNode2.next.next = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        listNode3.next = new ListNode(8);
        listNode3.next.next = new ListNode(9);
        ListNode[] lists = new ListNode[]{listNode1, listNode2, listNode3};
        ListNode a = mergeKLists(lists);
    }
}
