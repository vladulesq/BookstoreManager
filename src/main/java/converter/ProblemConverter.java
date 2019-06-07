package converter;

import core.model.Problem;
import dto.ProblemDto;
import org.springframework.stereotype.Component;

@Component
public class ProblemConverter extends BaseConverter<Problem, ProblemDto> {
    @Override
    public Problem convertDtoToModel(ProblemDto dto) {
        Problem problem = new Problem(dto.getDifficulty(), dto.getSubject(), dto.getText());
        problem.setId(dto.getId());
        return problem;

    }

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
        ProblemDto dto = ProblemDto.builder()
                .difficulty(problem.getDifficulty())
                .subject(problem.getSubject())
                .text(problem.getText())
                .build();
        dto.setId(problem.getId());
        return dto;
    }
}
