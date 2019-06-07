package controller;

import core.model.Student;
import core.service.StudentService;
import dto.StudentsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import converter.StudentConverter;
import dto.StudentDto;

import java.util.Collection;
import java.util.Set;


@RestController
public class StudentController {
    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentConverter studentConverter;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    StudentsDto getAllStudents() {
        log.trace("getAllStudents --- method entered");
        Collection<Student> studentList = studentService.getAllStudents();
        Set<StudentDto> dtos = studentConverter.convertModelsToDtos(studentList);
        StudentsDto result = new StudentsDto(dtos);

        log.trace("getAllStudents: result={}", result);

        return result;

    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    StudentDto saveStudent(@RequestBody StudentDto dto) {
        log.trace("saveStudent: dto={}", dto);

        Student saved = studentService.saveStudent(
                studentConverter.convertDtoToModel(dto)
        );
        StudentDto result = studentConverter.convertModelToDto(saved);

        log.trace("saveStudent: result={}", result);

        return result;
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    StudentDto updateStudent(@PathVariable Long id,
                             @RequestBody StudentDto dto) {
        log.trace("updateStudent: id={},dto={}", id, dto);

        Student update = studentService.updateStudent(
                studentConverter.convertDtoToModel(dto));
        StudentDto result = studentConverter.convertModelToDto(update);
        log.trace("updateMovie: result={}", dto);

        return result;
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        log.trace("deleteStudent: id={}", id);

        studentService.deleteStudent(id);

        log.trace("deleteStudent --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
