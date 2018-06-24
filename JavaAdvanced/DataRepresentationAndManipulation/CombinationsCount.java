package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class CombinationsCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger n = new BigInteger(br.readLine());
        BigInteger k = new BigInteger(br.readLine());

        BigInteger nFact = calcFactorial(n);
        BigInteger nMinusKFact = calcFactorial(n.subtract(k));
        BigInteger kFact = calcFactorial(k);

        BigInteger result = nFact.divide(nMinusKFact.multiply(kFact));

        System.out.println(result);
    }

    private static BigInteger calcFactorial(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) == -1){
            return BigInteger.ONE;
        }

        return num.multiply(calcFactorial(num.subtract(BigInteger.ONE)));
    }
}
