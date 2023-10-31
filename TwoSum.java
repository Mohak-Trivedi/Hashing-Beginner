// Problem: https://leetcode.com/problems/two-sum/

import java.util.HashMap;
import java.util.Map;

// Use class Solution in Leetcode
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] ans = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (map.containsKey(target - num)) {
                int idx2 = map.get(target - num);
                ans[0] = i;
                ans[1] = idx2;
                return ans;
            }

            map.put(num, i);

        }
        return ans;
    }
}
