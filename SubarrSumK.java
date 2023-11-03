// Problem: https://practice.geeksforgeeks.org/problems/subarrays-with-sum-k/1?utm_source=geeksforgeeks&utm_medium=article_practice_tab&utm_campaign=article_practice_tab

// Brute Force Approach: TC: O(N^2), SC: O(1)
// For every sub-array check if sum is k, and maintain count.

// Optimized approach using HashMap<CumSum, Freq>:
// Keep updating cum sum. Check in HashMap if previously got CumSum - k, if yes, then how many times that many sub arrays of sum k are there for that CumSum.
// In this way, get total count of sub arrays of sum k.

// Logic:
// We know, prefixSum[l-1] + S[l, r] = prefixSum[r]
// We want to find sub-array of sum k. So consider S[l, r] = k
// prefixSum[l-1] + k = prefixSum[r]
// prefixSum[l-1] = prefixSum[r] - k
// Hence, for each CumSum check for CumSum - k frequency. Those many sub-arrays with sum k for that CumSum at that time. In this way, do for all CumSum to get the total count of all sub arrays of sum k.

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int N = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int Arr[] = new int[N];
            for (int i = 0; i < N; i++)
                Arr[i] = Integer.parseInt(input_line[i]);
            int k = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            System.out.println(ob.findSubArraySum(Arr, N, k));
        }
    }
}
// } Driver Code Ends

// User function Template for Java

class Solution {
    static int findSubArraySum(int Arr[], int N, int k) {
        // code here
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < Arr.length; i++) {
            sum += Arr[i];

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}