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
     * Complete the 'circularArrayRotation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY a
     * 2. INTEGER k
     * 3. INTEGER_ARRAY queries
     */

    // SMART APPROACH: We are not asked to print the rotated array, but to give the
    // position of a particular element after array rotation. Hence, NO NEED TO
    // ACTUALLY ROTATE THE ARRAY, JUST CALCULATE ROTATED POSITION.
    // T.C.: O(n + q) where q is number of queries, S.C.: O(n)
    // If you actually rotate: More lines of code + more TC:
    // T.C.: O(3n + q): O(2n) : rotation + O(n) : storing in hmap + O(q) : answering
    // queries, S.C.: O(n)
    public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
        // Write your code here
        int n = a.size();
        int effectiveRotation = k % n;

        Map<Integer, Integer> hmap = new HashMap<>(); // to answer each query in O(1)
        for (int i = 0; i < n; i++) {
            int ele = a.get(i);
            int rotatedPos = (i + effectiveRotation) % n;

            hmap.put(rotatedPos, ele); // given position by query, return element at that position post rotation
        }

        List<Integer> ans = new ArrayList<>();
        for (int query : queries) {
            ans.add(hmap.get(query));
        }
        return ans;
    }

}

// Use class name Solution in HackerRank
public class CircularArrayRotation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        int q = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> queries = IntStream.range(0, q).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.circularArrayRotation(a, k, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
