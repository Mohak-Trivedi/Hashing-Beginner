// Problem: https://www.hackerrank.com/challenges/permutation-equation/problem

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
     * Complete the 'permutationEquation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY p as parameter.
     */

    // Approach: w/o HashMap: T.C.: O(n^2), S.C.: O(1)
    // public static List<Integer> permutationEquation(List<Integer> p) {
    // // Write your code here
    // int n = p.size();
    // ArrayList<Integer> ans = new ArrayList<>();
    // for (int x = 1; x <= n; x++) {
    // // Find p(x) i.e. position of x in p
    // int xPos = 0;
    // for (int i = 0; i <= p.size(); i++) {
    // if (p.get(i) == x) {
    // xPos = i + 1; // p is 0-based but xPos is 1-based
    // break;
    // }
    // }

    // // Find y = p(p(x)) = p(xPos) i.e. position of xPos in p
    // int y = 0;
    // for (int i = 0; i <= p.size(); i++) {
    // if (p.get(i) == xPos) {
    // y = i + 1; // p is 0-based but y is 1-based
    // break;
    // }
    // }

    // // Store the current y
    // ans.add(y);
    // }
    // return ans;
    // }

    // ALternate Approach: w/ HashMap: T.C.: O(n), S.C.: O(n^2)
    public static List<Integer> permutationEquation(List<Integer> p) {
        // Create a hashmap : given num, it should give position of num
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < p.size(); i++) {
            hmap.put(p.get(i), i + 1); // i+1 because p is 0-based but we need positions 1-based, so, 0->1, 1->2,
                                       // 2->3,...,p.size()-1->p.size()
        }

        ArrayList<Integer> ans = new ArrayList<>();

        int n = p.size();
        for (int x = 1; x <= n; x++) {
            // Find p(x) i.e. position of x in p
            int xPos = hmap.get(x);

            // Find y = pos(pos(x)) i.e. position of xPos in p
            int y = hmap.get(xPos);

            // Store current y
            ans.add(y);
        }

        return ans;
    }
}

// Use class name Solution in Hackerrank
public class SequenceEquation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.permutationEquation(p);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
