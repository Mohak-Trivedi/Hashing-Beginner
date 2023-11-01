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

        int cnt = 1;
        while (idx < nums.length - 1) {
            if (nums[idx + 1] == (nums[idx] + 1)) {
                cnt++;
                idx++;
            } else if (nums[idx + 1] == nums[idx]) { // need not count, but need to include equal consecutive elements
                                                     // in the sequence
                idx++;
            } else {
                maxCnt = Math.max(maxCnt, cnt);
                cnt = 1;
                idx++;
            }
        }
        maxCnt = Math.max(maxCnt, cnt); // Important in case else never ran -> maxCnt never got updated -> entire array
                                        // might be a sequence. And, even if the else ran the best maxCnt will be
                                        // retained due to Math.max. Think: [1,2,0,1]

        // You might think, why not create a separate case if(maxCnt == 0) maxCnt =
        // nums.length; But we can't do that as we must not include count of equal
        // elements in the sequence.

        return maxCnt;
    }
}
