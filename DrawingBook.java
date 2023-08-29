// Problem: https://www.hackerrank.com/challenges/drawing-book/problem

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
     * Complete the 'pageCount' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER n
     * 2. INTEGER p
     */
    // Approach: w/o HashMap T.C.: O(2n) S.C.: O(1)
    // public static int pageCount(int n, int p) {
    // // Write your code
    // if (n % 2 == 0) {
    // if (p == 1 || p == n) {
    // return 0;
    // }
    // } else {
    // if (p == 1 || p == n || p == n - 1) {
    // return 0;
    // }
    // }

    // int currPage = 2; // already searched at 1 above
    // int turnedFromOne = 1; // as we are starting from Page 2, 1 is already turned
    // while ((p != currPage) && (p != currPage + 1)) {
    // currPage += 2;
    // turnedFromOne++;
    // }

    // int turnedFromLast;
    // if (n % 2 == 0) {
    // currPage = n - 1;
    // } else {
    // currPage = n - 2;
    // }
    // turnedFromLast = 1;
    // while ((p != currPage) && (p != currPage - 1)) {
    // currPage -= 2;
    // turnedFromLast++;
    // }

    // return Math.min(turnedFromOne, turnedFromLast);
    // }

    // Approach: w/ HashMap T.C.: O(n) S.C.:O(n)
    public static int pageCount(int n, int p) {
        // Write your code
        int pagesTurned = 0;
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int currPage = 0; currPage <= n; currPage++) {
            hmap.put(currPage, pagesTurned);
            currPage++;
            if (currPage <= n) {
                hmap.put(currPage, pagesTurned);
            }
            pagesTurned++;
        }
        int pagesTurnedFromFirst = hmap.get(p);
        int pagesTurnedFromLast = hmap.get(n) - hmap.get(p);
        return Math.min(pagesTurnedFromFirst, pagesTurnedFromLast);
    }
}

// Use class name Solution in HackerRank
public class DrawingBook {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
