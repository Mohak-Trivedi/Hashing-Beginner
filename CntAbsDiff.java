// Problem:
// https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/submissions/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// For every possible pair check if abs diff is k, and maintain count.

// Optimized Approach using HashMap: TC: O(N), SC: O(N)
// For every num in nums[]: get frequency of num + k and num - k

// Logic:
// |nums[i] - nums[j]| == k
// given if x>=0 then |x|=x -> nums[i] - nums[j] == k -> nums[i] == nums[j] + k
// given if x<0 then |x|=-x -> -(nums[i] - nums[j]) == k -> nums[j] - nums[i] == k -> nums[i] == nums[j] - k
// Therefore, for every num in nums[]: get frequency of num + k and num - k.

// CAUTION:
// Do not make HashMap before, instead make it on the go because we need to find pairs nums[i] and nums[j] where i<j.
// If we make HashMap before, then for nums[i] we will also get nums[j] which are j>i.

import java.util.*;

class Solution {
    public int countKDifference(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> fmap = new HashMap<>();

        for (int num : nums) {
            count += fmap.getOrDefault(num + k, 0) + fmap.getOrDefault(num - k, 0);
            fmap.put(num, fmap.getOrDefault(num, 0) + 1);
        }

        return count;
    }
}