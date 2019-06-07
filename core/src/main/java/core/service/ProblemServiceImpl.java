package core.service;

import core.model.Problem;
import core.repository.ProblemRepoJPA;
import core.repository.paging.Pageable;
import core.repository.paging.impl.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProblemServiceImpl implements ProblemService {
    private static final Logger log = LoggerFactory.getLogger(
            ProblemServiceImpl.class);

    @Autowired
    private ProblemRepoJPA problemRepository;
    private int size = 0;
    private int page = 0;

    @Override
    public Set<Problem> getAllProblems() {
        log.trace("getAllProblems --- method entered");

        Iterable<Problem> result = problemRepository.findAll();

        log.trace("getAllProblems: result={}", result);

        return StreamSupport.stream(result.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Problem saveProblem(Problem problem) {
        log.trace("saveProblem: problem={}", problem);

        Problem problem1 = problemRepository.save(problem);

        log.trace("saveProblem --- method finished");
        return problem1;
    }

    @Override
    @Transactional
    public Problem updateProblem(Problem problem) {
        log.trace("update: problem={}", problem);

        Optional<Problem> updatedProblem = problemRepository.findById(problem.getId());
        updatedProblem.ifPresent(problem1 -> {
            problem1.setDifficulty(problem.getDifficulty());
            problem1.setSubject(problem.getSubject());
            problem1.setText(problem.getText());
        });
        log.trace("update --- method finished");

        return updatedProblem.get();
    }

    @Override
    public void deleteProblem(Long id) {
        log.trace("delete: id={}", id);

        problemRepository.deleteById(id);

        log.trace("delete --- method finished");

    }

    @Override
    public Set<Problem> filterByDifficulty(String difficulty) {
        log.trace("filterByDifficulty --- method entered");

        Iterable<Problem> problems = problemRepository.findAll();
        Set<Problem> filteredProblems = new HashSet<>();
        problems.forEach(filteredProblems::add);
        filteredProblems.removeIf(Problem -> {
            return difficulty.equals(Problem.getDifficulty());
        });
        log.trace("filterByDifficulty: result={}", filteredProblems);
        return filteredProblems;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Set<Problem> getNextProblems() {
        Pageable pageable = PageRequest.of(page, size);
        try {
//            Page<Movie> moviesPage = core.repository.findAll(pageable);
//            page = moviesPage.nextPageable().getPageNumber();
            return new HashSet<>();

        } catch (IndexOutOfBoundsException e) {
            page = 0;
            return new HashSet<>();
        }
    }

}
