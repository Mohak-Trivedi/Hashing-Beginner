// Problem:
// https://practice.geeksforgeeks.org/problems/max-distance-between-same-elements/1?utm_source=geeksforgeeks&utm_medium=article_practice_tab&utm_campaign=article_practice_tab

//{ Driver Code Starts
import java.util.Scanner;
import java.util.*;

class GFG {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            Solution ob = new Solution();
            System.out.println(ob.maxDistance(arr, n));

            t--;
        }
    }
}
// } Driver Code Ends

// your task is to complete this function
class Solution {
    int maxDistance(int arr[], int n) {
        // Your code here
        Map<Integer, Integer> map = new HashMap<>();

        int maxDist = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (map.containsKey(num)) {
                int firstOcc = map.get(num);
                maxDist = Math.max(maxDist, i - firstOcc);
            } else {
                map.put(num, i);
            }
        }

        return maxDist;
    }
}
