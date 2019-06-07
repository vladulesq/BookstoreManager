package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import dto.StudentDto;
import dto.StudentsDto;

import java.util.Set;

@Component
public class StudentClientServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    public StudentDto addStudent(StudentDto student) {
        return restTemplate.postForObject(
                "http://localhost:8080/api/students",
                student,
                StudentDto.class
        );
    }

    public Set<StudentDto> getAllStudents() {
        return restTemplate.getForObject(
                "http://localhost:8080/api/students",
                StudentsDto.class
        ).getStudents();

    }

    public void removeStudent(Long id) {
        restTemplate.delete(
                "http://localhost:8080/api/students/{id}",
                id
        );
    }

    public void updateStudent(StudentDto student) {
        restTemplate.put(
                "http://localhost:8080/api/students/{id}",
                student,
                student.getId()
        );
    }
}
