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

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        Collections.sort(x);
        int numTransmitters = 0;
        int i = 0;
        int n = x.size();
        while (i < n) {
            numTransmitters++;
            int location = x.get(i) + k;
            while (i < n && x.get(i) <= location)
                i++;
            i--;
            location = x.get(i) + k;
            while (i < n && x.get(i) <= location)
                i++;
        }
        return numTransmitters;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long startTime = System.nanoTime();

        int result = Result.hackerlandRadioTransmitters(x, k);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time taken: " + duration + " nanoseconds");

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        System.out.println(String.valueOf(result));

        bufferedReader.close();
        bufferedWriter.close();
    }
}
