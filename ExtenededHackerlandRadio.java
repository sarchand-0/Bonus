import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {

    static class Result {

        public static int hackerlandRadioTransmitters(List<Integer> x, List<Integer> cost, int k) {
            Collections.sort(x);

            int n = x.size();
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = cost.get(0);
            Map<Integer, Integer> coverageMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int coverageEnd = x.get(i) + 2 * k;
                for (int j = i; j < n && x.get(j) <= coverageEnd; j++) {
                    coverageMap.put(i, j);
                }
            }

            for (int i = 1; i < n; i++) {
                dp[i] = Math.min(dp[i], dp[i - 1] + cost.get(i));
                for (int j = 0; j < i; j++) {
                    int coveredUntil = coverageMap.getOrDefault(j, j);
                    if (coveredUntil >= i) {
                        dp[i] = Math.min(dp[i], dp[j] + cost.get(j));
                    }
                }
            }

            return dp[n - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long startTime = System.nanoTime();
        int result = Result.hackerlandRadioTransmitters(x, cost, k);
        long endTime = System.nanoTime(); // End timing
        long duration = (endTime - startTime);
        System.out.println("Time taken: " + duration + " nanoseconds");

        System.out.println(String.valueOf(result));

        bufferedReader.close();
    }
}
