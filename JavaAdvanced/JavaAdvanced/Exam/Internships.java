package JavaAdvanced.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Internships {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int problemsCount = Integer.parseInt(reader.readLine());
        int candidatesCount = Integer.parseInt(reader.readLine());
        String candidateRegex = "^([A-Z][a-z]+ [A-Z][a-z]+)$";
        ArrayDeque<String> problemsStack = new ArrayDeque<>();
        ArrayDeque<String> candidatesQueue = new ArrayDeque<>();

        for (int i = 0; i < problemsCount; i++) {
            String currentLine = reader.readLine();
            problemsStack.push(currentLine);
        }
        for (int i = 0; i < candidatesCount; i++) {
            String currentLine = reader.readLine();
            Matcher candidateMatcher = Pattern.compile(candidateRegex).matcher(currentLine);

            while (candidateMatcher.find()) {
                String candidateName = candidateMatcher.group(1);
                candidatesQueue.add(candidateName);
            }
        }

        while (problemsStack.size() > 0) {
            String currentProblem = problemsStack.pop();
            String currentCandidate = candidatesQueue.remove();
            int currentProblemSum = Arrays.stream(currentProblem.split("")).mapToInt(s -> s.charAt(0)).sum();
            int currentCandidateSum = Arrays.stream(currentCandidate.split("")).mapToInt(s -> s.charAt(0)).sum();

            if (currentCandidateSum > currentProblemSum) {
                candidatesQueue.addLast(currentCandidate);
                System.out.println(String.format("%s solved %s.", currentCandidate, currentProblem));
            } else {
                problemsStack.addLast(currentProblem);
                System.out.println(String.format("%s failed %s.", currentCandidate, currentProblem));
            }

            if (candidatesQueue.size() == 1) {
                System.out.println(String.format("%s gets the job!", candidatesQueue.remove()));
                return;
            }
        }
        System.out.println(Arrays.toString(candidatesQueue.toArray()).replaceAll("[\\[\\]]", ""));
    }
}
