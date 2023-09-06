// Problem: https://www.hackerrank.com/challenges/closest-numbers/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'closestNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    // T.C.: O(3n + 2nlogn), S.C.: O(2n)
    // public static List<Integer> closestNumbers(List<Integer> arr) {
    // // Write your code here
    // Collections.sort(arr);

    // Map<int[], Integer> hmap = new HashMap<>(); // to store only unique pairs

    // // Find the minimum difference
    // int minDiff = Integer.MAX_VALUE;
    // for (int i = 0; i < arr.size() - 1; i++) {
    // int a = arr.get(i);
    // int b = arr.get(i + 1);

    // int diff = Math.abs(b - a);
    // if (diff > 0) {
    // minDiff = Math.min(minDiff, diff);
    // }
    // }

    // // Store unique pairs having minimum difference
    // for (int i = 0; i < arr.size() - 1; i++) {
    // int a = arr.get(i);
    // int b = arr.get(i + 1);

    // int diff = Math.abs(b - a);
    // if (diff == minDiff) {
    // int[] pair = new int[2];
    // pair[0] = a;
    // pair[1] = b;
    // hmap.put(pair, 0);
    // }
    // }

    // ArrayList<Integer> ans = new ArrayList<>();
    // for (int[] key : hmap.keySet()) {
    // ans.add(key[0]);
    // ans.add(key[1]);
    // }
    // Collections.sort(ans);
    // return ans;
    // }

    // Alternate Approach: T.C.: O(nlogn + 2n), S.C.: O(n)
    // O(n) decreases from S.C. Instead of storing min diff pairs (int[2]), store
    // just
    // the start index (int)
    // of each pair as key in hashmap along with its abs diff from next index
    // element as value.
    // O(n) decreases from T.C. as we don't need separate loop for filling hashmap,
    // we fill it in same loop used for finding minDiff as we will store all pairs
    // not just min pairs.
    // O(nlogn) decreases from T.C. as we are not requiring sorting of final
    // arraylist.
    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);

        Map<Integer, Integer> hmap = new HashMap<>(); // to store only unique pairs (start index of pair)

        // Find the minimum difference and fill hashmap with pair-start-index :
        // diff-with-next-index-element pair
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size() - 1; i++) {
            int a = arr.get(i);
            int b = arr.get(i + 1);

            int diff = Math.abs(b - a);
            if (diff > 0) {
                hmap.put(i, diff);
                minDiff = Math.min(minDiff, diff);
            }
        }

        // Find pair with min diff from hashmap, and store its elements in arraylist
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer key : hmap.keySet()) {
            int diff = hmap.get(key);
            if (diff == minDiff) {
                ans.add(arr.get(key));
                ans.add(arr.get(key + 1));
            }
        }

        return ans;
    }
}

// Use class name Solution in HackerRank
public class ClosestNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.closestNumbers(arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
