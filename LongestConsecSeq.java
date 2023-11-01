// Problem: https://leetcode.com/problems/longest-consecutive-sequence/submissions/

// Approach 1: Without Hashing. Using Sorting
// TC: O(nlogn + n), SC: O(1)
import java.util.Arrays;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }

        Arrays.sort(nums);

        int maxCnt = 0;

        int idx = 0;
        while (idx < nums.length) {
            int cnt = 1;
            while (idx < nums.length - 1) {
                if (nums[idx + 1] == (nums[idx] + 1)) {
                    cnt++;
                    idx++;
                } else if (nums[idx + 1] == nums[idx]) {
                    idx++;
                } else {
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 1;
                    idx++;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
            idx++;
        }

        return maxCnt;
    }
}
