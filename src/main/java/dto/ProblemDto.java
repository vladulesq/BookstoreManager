package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString(callSuper = true)
@Builder
@EqualsAndHashCode()
public class ProblemDto extends BaseDto {
    private String difficulty;
    private String subject;
    private String text;
}
