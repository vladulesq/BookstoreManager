package converter;

import core.model.Student;
import dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {

    @Override
    public Student convertDtoToModel(StudentDto dto) {
        Student student = Student.builder()
                .gr(dto.getGr())
                .name(dto.getName())
                .serialnumber(dto.getSerialnumber())
                .build();
        student.setId(dto.getId());
        return student;
    }

    @Override
    public StudentDto convertModelToDto(Student student) {
        StudentDto dto = StudentDto.builder()
                .serialnumber(student.getSerialnumber())
                .name(student.getName())
                .gr(student.getGr())
                .build();
        dto.setId(student.getId());
        return dto;
    }
}
