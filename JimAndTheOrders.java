// Problem: https://www.hackerrank.com/challenges/jim-and-the-orders/problem

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
     * Complete the 'jimOrders' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY orders as parameter.
     */

    public static List<Integer> jimOrders(List<List<Integer>> orders) {
        // Write your code here
        // Create mapping of delivery time to customer numbers, sorted map based on
        // delivery time
        // delivery time -> List of customer numbers having this delivery time, sorted
        // list based on customer numbers
        Map<Integer, Set<Integer>> tmap = new TreeMap<>();
        int currCustomerNum = 1;
        for (int i = 0; i < orders.size(); i++) {
            List<Integer> currOrder = orders.get(i);
            int orderNumber = currOrder.get(0);
            int prepTime = currOrder.get(1);
            int deliveryTime = orderNumber + prepTime;

            if (tmap.containsKey(deliveryTime)) {
                Set<Integer> customerNumbers = tmap.get(deliveryTime);
                customerNumbers.add(currCustomerNum);
                tmap.put(deliveryTime, customerNumbers);
            } else {
                Set<Integer> customerNumbers = new TreeSet<>();
                customerNumbers.add(currCustomerNum);
                tmap.put(deliveryTime, customerNumbers);
            }

            currCustomerNum++;
        }

        List<Integer> deliveryOrder = new ArrayList<Integer>();
        for (int deliveryTime : tmap.keySet()) {
            Set<Integer> customerNumbers = tmap.get(deliveryTime);
            for (int customerNum : customerNumbers) {
                deliveryOrder.add(customerNum);
            }
        }

        return deliveryOrder;
    }
}

// Use class name Solution in HackerRank
public class JimAndTheOrders {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> orders = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                orders.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.jimOrders(orders);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
