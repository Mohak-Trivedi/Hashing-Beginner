// Problem: https://www.geeksforgeeks.org/count-number-subarrays-given-xor/

// Brute Force Approach: TC: O(N^2), SC: O(1)
// For each sub-array find XOR, and maintain count for sub-arrays with given XOR
// value.

// Optimized Approach using HashMap: TC: O(N), SC: O(N)
// For each current cumulative XOR, if you have previous cumulative XOR = m XOR
// current cumulative XOR, increase count the number of times it occurred. To
// find this in O(1), we can use a HashMap<cumXOR, freq>

// Logic:
// PrefixXOR[l-1] XOR PrefixXOR[r] = XOR[l, r] (Because 0 to l-1 indexed
// elements will cancel out to 0, as XOR of same values gives 0)
// -> PrefixXOR[l-1] = XOR[l, r] XOR PrefixXOR[r]
// As we traverse PrefixXOR[] start to end, we will be having our PrefixXOR[r]
// every time. And XOR[l, r] should be m (given input).
// So, for current cumulative XOR, if you have previous cumulative XOR = m XOR
// current cumulative XOR, increase count the number of times it occurred. To
// find this in O(1), we can use a HashMap<cumXOR, freq>

// Java program to implement the approach
import java.util.*;

class GFG {

    // Function to return the XOR of all subarrays
    static int subarrayXor(int[] arr, int n, int m) {
        Map<Integer, Integer> fmap = new HashMap<>();

        int cumXor = 0;
        int count = 0;
        fmap.put(0, 1); // for 0th element there exists a previous cumulative XOR 0

        for (int i = 0; i < arr.length; i++) {
            cumXor ^= arr[i];

            // if(cumXor == m) {
            // count++;
            // }
            // no need to include above if along with below as we have put cumulative XOR 0
            // at line 32.
            if (fmap.containsKey(m ^ cumXor)) {
                int freq = fmap.get(m ^ cumXor);
                count += freq;
            }

            fmap.put(cumXor, fmap.getOrDefault(cumXor, 0) + 1);
        }

        return count;
    }

    // Driver program to test above function
    public static void main(String[] args) {
        int[] arr = { 5, 6, 7, 8, 9 };
        int n = arr.length;
        int m = 5;

        // Function call
        System.out.println(
                "Number of subarrays having given XOR is "
                        + subarrayXor(arr, n, m));
    }
}

// This code is contributed by phasing17
