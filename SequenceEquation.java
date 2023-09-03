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

    public static List<Integer> permutationEquation(List<Integer> p) {
        // Write your code here
        int n = p.size();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int x = 1; x <= n; x++) {
            // Find p(x) i.e. position of x in p
            int xPos = 0;
            for (int i = 0; i <= p.size(); i++) {
                if (p.get(i) == x) {
                    xPos = i + 1; // p is 0-based but xPos is 1-based
                    break;
                }
            }

            // Find y = p(p(x)) = p(xPos) i.e. position of xPos in p
            int y = 0;
            for (int i = 0; i <= p.size(); i++) {
                if (p.get(i) == xPos) {
                    y = i + 1; // p is 0-based but y is 1-based
                    break;
                }
            }

            // Store the current y
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
