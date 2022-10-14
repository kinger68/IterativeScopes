package org.example.iterativescope;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TryTheQuestionTest {
    static List<Integer> numbers = new ArrayList<>(List.of(10, 6, 8, 5, 4, 7, 1));

    @ParameterizedTest
    @MethodSource("leaderSource")
    public void testForLeaders(ArrayList<Integer>result) {
        assertTrue(TryTheQuestions.getLeaders(numbers).equals(result));
    }
    private static Stream<Arguments> leaderSource() {
        return Stream.of(
                arguments(new ArrayList<>(List.of(10, 8, 7, 1)))
        );
    }

    @ParameterizedTest
    @MethodSource("numSource")
    public void testForTargets(int target, TryTheQuestions.TargetIndices result) {
        assertEquals(result, TryTheQuestions.getTarget(numbers, target));
    }
    private static Stream<Arguments> numSource() {
        return Stream.of(
                arguments(17, new TryTheQuestions.TargetIndices(2,4)),
                arguments(123, null),
                arguments(11, new TryTheQuestions.TargetIndices(4,5))
        );
    }
}
