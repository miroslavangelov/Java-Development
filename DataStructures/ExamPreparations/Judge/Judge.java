package DataStructures.ExamPreparations.Judge;

import java.util.*;
import java.util.stream.Collectors;

public class Judge implements IJudge {
    private TreeSet<Integer> users;
    private TreeSet<Integer> contests;
    private TreeMap<Integer, Submission> submissions;
    private Map<SubmissionType, TreeSet<Integer>> contestsBySubmissionType;
    private Map<Integer, List<Submission>> submissionsByUserId;
    private Map<SubmissionType, List<Submission>> submissionsBySubmissionType;
    private Map<String, List<Submission>> submissionsByPointsContestIdAndUserId;

    public Judge() {
        this.users = new TreeSet<>();
        this.contests = new TreeSet<>();
        this.submissions = new TreeMap<>();
        this.contestsBySubmissionType = new HashMap<>();
        this.submissionsByUserId = new HashMap<>();
        this.submissionsBySubmissionType = new HashMap<>();
        this.submissionsByPointsContestIdAndUserId = new HashMap<>();
    }

    public void addContest(int contestId) {
        this.contests.add(contestId);
    }

    public void addSubmission(Submission submission) {
        if (!this.users.contains(submission.getUserId()) || !this.contests.contains(submission.getContestId())) {
            throw new IllegalArgumentException();
        }

        int submissionId = submission.getId();
        SubmissionType submissionType = submission.getType();
        int userId = submission.getUserId();
        String key = String.format("%s %s %s", submission.getPoints(), submission.getContestId(), userId);

        this.submissions.putIfAbsent(submissionId, submission);
        this.contestsBySubmissionType.putIfAbsent(submissionType, new TreeSet<>());
        this.contestsBySubmissionType.get(submissionType).add(submission.getContestId());
        this.submissionsByUserId.putIfAbsent(userId, new ArrayList<>());
        this.submissionsByUserId.get(userId).add(submission);
        this.submissionsBySubmissionType.putIfAbsent(submissionType, new ArrayList<>());
        this.submissionsBySubmissionType.get(submissionType).add(submission);
        this.submissionsByPointsContestIdAndUserId.putIfAbsent(key, new ArrayList<>());
        this.submissionsByPointsContestIdAndUserId.get(key).add(submission);
    }

    public void addUser(int userId) {
        this.users.add(userId);
    }

    public void deleteSubmission(int submissionId) {
        if (!this.submissions.containsKey(submissionId)) {
            throw new IllegalArgumentException();
        }

        Submission submissionToRemove = this.submissions.get(submissionId);
        String key = String.format("%s %s %s", submissionToRemove.getPoints(), submissionToRemove.getContestId(), submissionToRemove.getUserId());

        this.submissionsByUserId.get(submissionToRemove.getUserId()).remove(submissionToRemove);
        this.submissionsBySubmissionType.get(submissionToRemove.getType()).remove(submissionToRemove);
        this.submissionsByPointsContestIdAndUserId.remove(key);
        this.submissions.remove(submissionId);
    }

    public Iterable<Submission> getSubmissions() {
        return new ArrayList<>(this.submissions.values());
    }

    public Iterable<Integer> getUsers() {
        return new ArrayList<>(this.users);
    }

    public Iterable<Integer> getContests() {
        return new ArrayList<>(this.contests);
    }

    public Iterable<Submission> submissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType) {
        List<Submission> result = new ArrayList<>();

        if (!this.submissionsBySubmissionType.containsKey(submissionType)) {
            return result;
        }

        this.submissionsBySubmissionType.get(submissionType)
                .forEach(submission -> {
                    if (submission.getPoints() >= minPoints && submission.getPoints() <= maxPoints) {
                        result.add(submission);
                    }
                });

        return result;
    }

    public Iterable<Integer> contestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId) {
        return this.submissionsByUserId.get(userId).stream()
                .sorted(Comparator.comparing(Submission::getPoints, Comparator.reverseOrder())
                        .thenComparing(Submission::getId))
                .map(Submission::getContestId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Iterable<Submission> submissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId) {
        String key = String.format("%s %s %s", points, contestId, userId);

        if (!this.submissionsByPointsContestIdAndUserId.containsKey(key)) {
            throw new IllegalArgumentException();
        }

        return this.submissionsByPointsContestIdAndUserId.get(key);
    }

    public Iterable<Integer> contestsBySubmissionType(SubmissionType submissionType) {
        if (!this.contestsBySubmissionType.containsKey(submissionType)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(this.contestsBySubmissionType.get(submissionType));
    }
}
