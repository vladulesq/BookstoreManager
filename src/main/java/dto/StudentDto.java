package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString(callSuper = true)
@Builder
@EqualsAndHashCode(callSuper = true)
public class StudentDto extends BaseDto {
    private String serialnumber;
    private String name;
    private int gr;
}
