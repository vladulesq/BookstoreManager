package core.service;

import core.model.Student;

import java.util.Set;

public interface StudentService {
    Set<Student> getAllStudents();

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    Set<Student> filterByGroup(int group);

    void setSize(int size);

    Set<Student> getNextStudents();
}
