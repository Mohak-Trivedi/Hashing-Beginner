
// Problem: https://www.hackerrank.com/challenges/missing-numbers/problem
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
     * Complete the 'missingNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY arr
     * 2. INTEGER_ARRAY brr
     */

    // Approach: Using 2 hashmaps: T.C.: O(2m + n + nlogn) ,S.C.: O(m + n)
    // public static List<Integer> missingNumbers(List<Integer> arr, List<Integer>
    // brr) {
    // Map<Integer, Integer> aMap = new HashMap<>();
    // for (int i = 0; i < arr.size(); i++) {
    // aMap.put(arr.get(i), aMap.getOrDefault(arr.get(i), 0) + 1);
    // }

    // Map<Integer, Integer> bMap = new HashMap<>();
    // for (int i = 0; i < brr.size(); i++) {
    // bMap.put(brr.get(i), bMap.getOrDefault(brr.get(i), 0) + 1);
    // }

    // List<Integer> missingNumbersList = new ArrayList<>();
    // for (Integer key : bMap.keySet()) {
    // if (aMap.containsKey(key)) {
    // if (aMap.get(key) < bMap.get(key)) {
    // missingNumbersList.add(key);
    // }
    // } else {
    // missingNumbersList.add(key);
    // }
    // }

    // Collections.sort(missingNumbersList);
    // return missingNumbersList;
    // }

    // Alternate approach: Using single hashmap: T.C.: O(2m + n + nlogn), S.C.: O(m)
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        List<Integer> missingValueList = new ArrayList<>();
        Map<Integer, Integer> hmap = new HashMap<>();

        for (int i = 0; i < brr.size(); i++) {
            hmap.put(brr.get(i), hmap.getOrDefault(brr.get(i), 0) + 1);
        }

        for (int i = 0; i < arr.size(); i++) {
            if (hmap.containsKey(arr.get(i)) && hmap.get(arr.get(i)) > 0) {
                hmap.put(arr.get(i), hmap.get(arr.get(i)) - 1);
            }
        }

        for (Integer key : hmap.keySet()) {
            if (hmap.get(key) > 0) {
                missingValueList.add(key);
            }
        }

        Collections.sort(missingValueList);

        return missingValueList;
    }

}

// Use class name Solution in HackerRank
public class MissingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.missingNumbers(arr, brr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
