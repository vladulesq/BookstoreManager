package service;

import dto.ProblemDto;
import dto.ProblemsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class ProblemClientServiceImpl {
    @Autowired
    private RestTemplate restTemplate;

    public ProblemDto addProblem(ProblemDto problem) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/problems",
                problem,
                ProblemDto.class
        );
    }

    public Set<ProblemDto> getAllProblems() {
        return restTemplate.getForObject(
                "http://localhost:8080/api/problems",
                ProblemsDto.class
        ).getProblems();

    }

    public void removeProblem(Long id) {
        restTemplate.delete(
                "http://localhost:8080/api/problems/{id}",
                id
        );
    }

    public void updateProblem(ProblemDto problem) {
        restTemplate.put(
                "http://localhost:8080/api/problems/{id}",
                problem,
                problem.getId()
        );
    }
}
