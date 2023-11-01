// Problem: https://leetcode.com/problems/longest-consecutive-sequence/submissions/

// Approach 1: Without Hashing. Using Sorting.
// TC: O(nlogn + n), SC: O(1)

// Logic:
// Sort the array so that sequence elements come side-by side.
// Start a new sequence's  count with the next element if the next element is not +1 or == with the current element.
// Keep updating maxCount as per new sequence's lengths found.

// import java.util.Arrays;

// class Solution {
//     public int longestConsecutive(int[] nums) {
//         if (nums.length == 0 || nums.length == 1) {
//             return nums.length;
//         }

//         Arrays.sort(nums);

//         int maxCnt = 0;

//         int idx = 0;

//         int cnt = 1;
//         while (idx < nums.length - 1) {
//             if (nums[idx + 1] == (nums[idx] + 1)) {
//                 cnt++;
//                 idx++;
//             } else if (nums[idx + 1] == nums[idx]) { // need not count, but need to include equal consecutive elements
//                                                      // in the sequence
//                 idx++;
//             } else {
//                 maxCnt = Math.max(maxCnt, cnt);
//                 cnt = 1;
//                 idx++;
//             }
//         }
//         maxCnt = Math.max(maxCnt, cnt); // Important in case else never ran -> maxCnt never got updated -> entire array
//                                         // might be a sequence. And, even if the else ran the best maxCnt will be
//                                         // retained due to Math.max. Think: [1,2,0,1]

//         // You might think, why not create a separate case if(maxCnt == 0) maxCnt =
//         // nums.length; But we can't do that as we must not include count of equal
//         // elements in the sequence.

//         return maxCnt;
//     }
// }

// Approach 2:
// TC: O(2N), SC: O(2N)

// Logic:
// If element-1 does not exist in nums[] and it was not a part of any sequence discovered before, then that element is a starting point of a new sequence.
// 2 HashMaps:
// inSeq: Elements already in a sequence discovered previously.
// presentNums: Elements of nums[]. To find in O(1) whether element-1 exists in nums[]
import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> presentNums = new HashMap<>();
        for (int num : nums) {
            presentNums.put(num, true);
        }

        int maxCount = 0;
        Map<Integer, Boolean> inSeq = new HashMap<>();
        for (int num : nums) {
            if (!inSeq.containsKey(num) && !presentNums.containsKey(num - 1)) {
                int count = 0;
                int start = num;
                while (presentNums.containsKey(start)) {
                    count++;
                    inSeq.put(start, true);
                    start++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }
}