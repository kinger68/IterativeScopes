package org.example.iterativescope;

import java.util.*;

public class TryTheQuestions {

    public record TargetIndices(int start, int end) {}

    static List<Integer> numbers = new ArrayList<>(List.of(10, 6, 8, 5, 4, 7, 1));

    public static List<Integer> getLeaders(List<Integer> numbers) {
        Stack<Integer> leaders = new Stack<>();

        // Last element always is a leader
        leaders.push(numbers.get(numbers.size()-1));
        for(int i = numbers.size() - 2; i > -1; i--) {
            int nextLeaderCandidate = numbers.get(i);
            if(leaders.peek() < nextLeaderCandidate) {
                leaders.push(nextLeaderCandidate);
            }
            System.out.println(leaders);
        }

        List<Integer> returnList = new ArrayList<>(leaders);
        Collections.reverse(returnList);
        return returnList;
    }

    public static TargetIndices getTarget(List<Integer> numbers, Integer target) {
        int start = 0, end = 0;
        int runningSum = 0;

        for(Integer next : numbers) {
            runningSum += next;
            if(runningSum == target) {
                return new TargetIndices(start, end);
            }
            else if(runningSum < target) {
                end++;
            }
            else {
                runningSum -= numbers.get(start);
                start++;

                // Need to check if the new sum after removing the value at the start is now
                // equal to the target because we may have gone over the target and then come back to it
                // Must be done before incrementing the end index since the new sum already includes it
                if(runningSum == target) {
                    return new TargetIndices(start, end);
                }

                end++;
            }

        }

        // Did not find any sequential numbers that matched the target
        return null;
    }

    public static void main(String[] args) {
        // System.out.println(getLeaders(numbers));
        TargetIndices result = getTarget(numbers, 17);
        if(result != null) {
            System.out.println(numbers.subList(result.start, result.end+1));
        }
    }

}
