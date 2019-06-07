package controller;

import converter.ProblemConverter;
import core.model.Problem;
import core.service.ProblemService;
import dto.ProblemDto;
import dto.ProblemsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
public class ProblemController {
    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);


    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemConverter problemConverter;

    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    ProblemsDto getAllProblems() {
        log.trace("getAllProblems --- method entered");
        Collection<Problem> problemList = problemService.getAllProblems();
        Set<ProblemDto> dtos = problemConverter.convertModelsToDtos(problemList);
        ProblemsDto result = new ProblemsDto(dtos);
        log.trace("getAllStudents: result={}", result);
        return result;
    }

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    ProblemDto saveProblem(@RequestBody ProblemDto dto) {
        log.trace("saveStudent: dto={}", dto);

        Problem saved = problemService.saveProblem(
                problemConverter.convertDtoToModel(dto)
        );
        ProblemDto result = problemConverter.convertModelToDto(saved);

        log.trace("problemStudent: result={}", result);

        return result;
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.PUT)
    ProblemDto updateProblem(@PathVariable Long id,
                             @RequestBody ProblemDto dto) {
        log.trace("updateProblem: id={},dto={}", id, dto);
        Problem update = problemService.updateProblem(
                problemConverter.convertDtoToModel(dto));
        ProblemDto result = problemConverter.convertModelToDto(update);
        log.trace("updateProblem result={}", dto);
        return result;
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProblem(@PathVariable Long id) {
        log.trace("deleteProblem: id={}", id);

        problemService.deleteProblem(id);

        log.trace("deleteProblem --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

