package core.service;

import core.model.Problem;

import java.util.Set;

public interface ProblemService {
    Set<Problem> getAllProblems();

    Problem saveProblem(Problem problem);

    Problem updateProblem(Problem problem);

    void deleteProblem(Long id);

    Set<Problem> filterByDifficulty(String difficulty);

    void setSize(int size);

    Set<Problem> getNextProblems();
}
