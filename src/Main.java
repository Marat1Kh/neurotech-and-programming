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
     * Complete the 'interpolate_by_lagrange' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. DOUBLE_ARRAY x_axis
     *  2. DOUBLE_ARRAY y_axis
     *  3. DOUBLE x
     */

    public static double interpolate_by_lagrange(List<Double> x_axis, List<Double> y_axis, double x) {
        int n=x_axis.size();
        double result=0.0;
        double numeration; double denominator;
        for (int counter_i=0; counter_i<n; counter_i++) {
            numeration=1.0;
            denominator=1.0;
            for (int counter_j=0; counter_j<n; counter_j++) {
                if (counter_j!=counter_i) {
                    numeration=numeration*(x-x_axis.get(counter_j));
                    denominator=denominator*(x_axis.get(counter_i)-x_axis.get(counter_j));
                }
            }
            result +=(numeration/denominator)*y_axis.get(counter_i);
        }
        return result;
    }


}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int axisCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> x_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> y_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        double x = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.interpolate_by_lagrange(x_axis, y_axis, x);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}

