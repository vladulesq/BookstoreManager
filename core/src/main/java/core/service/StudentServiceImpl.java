package core.service;

import core.model.Student;
import core.repository.StudentRepoJPA;
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
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(
            StudentServiceImpl.class);

    @Autowired
    private StudentRepoJPA studentRepository;

    private int size = 0;
    private int page = 0;

    @Override
    public Set<Student> getAllStudents() {
        log.trace("getAllStudents --- method entered");

        Iterable<Student> result = studentRepository.findAll();

        log.trace("getAllStudents: result={}", result);

        return StreamSupport.stream(result.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Student saveStudent(Student student) {
        log.trace("saveStudent: student={}", student);

        Student savedStudent = studentRepository.save(student);

        log.trace("saveStudent --- method finished");

        return savedStudent;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        log.trace("update: student={}", student);
        Optional<Student> updatedMovie = studentRepository.findById(student.getId());
        updatedMovie.ifPresent(student1 -> {
            student1.setSerialnumber(student.getSerialnumber());
            student1.setName(student.getName());
            student1.setGr(student.getGr());
            log.debug("update --- student updated? --- " +
                    "student={}", student1);
        });

        log.trace("update --- method finished");
        return updatedMovie.get();
    }

    @Override
    public void deleteStudent(Long id) {
        log.trace("delete: id={}", id);

        studentRepository.deleteById(id);

        log.trace("delete --- method finished");
    }

    @Override
    public Set<Student> filterByGroup(int group) {
        log.trace("filterByGroup --- method entered");

        Iterable<Student> students = studentRepository.findAll();
        Set<Student> filteredStudents = new HashSet<>();
        students.forEach(filteredStudents::add);
        filteredStudents.removeIf(Student -> { return Student.getGr() == group;});
        log.trace("filterByGroup: result={}", filteredStudents);
        return filteredStudents;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Set<Student> getNextStudents() {
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
