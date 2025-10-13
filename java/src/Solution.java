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


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);
        listNode.next.next.next.next.next.next.next = new ListNode(8);
        ListNode a = reverseKGroup(listNode, 2);
    }
}
