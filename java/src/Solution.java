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
        if (nums.length ==0 || nums.length == 1)  {
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

    public static void main(String[] args) {

    }
}
