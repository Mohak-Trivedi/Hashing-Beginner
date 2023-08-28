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
    // Approach: w/o HashMap T.C.: O(n) S.C.: O(1)
    public static int pageCount(int n, int p) {
        // Write your code here
        if (p == 1 || p == n) {
            return 0;
        }

        int currPage = 2; // already searched at 1 above
        int turnedFromOne = 1; // as we are starting from Page 2, 1 is already turned
        while ((p != currPage) && (p != currPage + 1)) {
            currPage += 2;
            turnedFromOne++;
        }

        int turnedFromLast;
        if (n % 2 == 0) {
            currPage = n - 1; // already searched at n above
            turnedFromLast = 1; // as we are starting from Page n-1, n is already turned
            while ((p != currPage) && (p != currPage - 1)) {
                currPage -= 2;
                turnedFromLast++;
            }
        } else {
            currPage = n - 1;
            if (currPage == p) {
                return 0;
            }
            currPage--;
            turnedFromLast = 1;
            while ((p != currPage) && (p != currPage - 1)) {
                currPage -= 2;
                turnedFromLast++;
            }
        }

        return Math.min(turnedFromOne, turnedFromLast);
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
