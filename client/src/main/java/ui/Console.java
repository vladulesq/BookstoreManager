package ui;

import dto.ProblemDto;
import dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ProblemClientServiceImpl;
import service.StudentClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

@Component
public class Console {

    @Autowired
    private StudentClientServiceImpl studentService;

    @Autowired
    private ProblemClientServiceImpl problemService;

    public Console() {
    }

    private static StudentDto readStudent() {
        System.out.println("Read Student {ID, Serial number, Name, Group}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String sn = bufferRead.readLine();
            String name = bufferRead.readLine();
            int group = Integer.parseInt(bufferRead.readLine());

            StudentDto student = StudentDto.builder()
                    .serialnumber(sn)
                    .name(name)
                    .gr(group)
                    .build();
            student.setId(id);

            return student;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static ProblemDto readProblem() {
        System.out.println("Read Problem {ID, Difficulty, Subject, Text}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String diff = bufferRead.readLine();
            String subject = bufferRead.readLine();
            String text = bufferRead.readLine();
            ProblemDto problemDto = ProblemDto
                    .builder()
                    .difficulty(diff)
                    .subject(subject)
                    .text(text)
                    .build();
            problemDto.setId(id);

            return problemDto;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void printMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Print students.\n");
        menu.append("2. Add student.\n");
        menu.append("3. Remove student.\n");
        menu.append("4. Update student.\n");
        menu.append("--------------------\n");
        menu.append("5. Print problems.\n");
        menu.append("6. Add problem.\n");
        menu.append("7. Remove problem\n");
        menu.append("8. Update problem\n");
        menu.append("0. Exit\n");
        menu.append("Enter command: ");
        System.out.println(menu);
    }

    public void run() {
        while (true) {
            try {
                printMenu();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(bufferedReader.readLine());
                if (option == 0) {
                    break;
                }
                switch (option) {
                    case 1:
                        printStudents();
                        break;
                    case 2:
                        addStudent();
                        break;
                    case 3:
                        removeStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        printProblems();
                        break;
                    case 6:
                        addProblem();
                        break;
                    case 7:
                        removeProblem();
                        break;
                    case 8:
                        updateProblem();
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                System.out.println("Error:\n\t" + e.getMessage());
            }
        }
    }

    private void printStudents() {
        Set<StudentDto> Movies = studentService.getAllStudents();
        Movies.forEach(System.out::println);
    }

    private void printProblems() {
        Set<ProblemDto> problems = problemService.getAllProblems();
        problems.forEach(System.out::println);
    }

    private void addStudent() {
        try {
            StudentDto student = readStudent();
            studentService.addStudent(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProblem() {
        try {
            ProblemDto problem = readProblem();
            problemService.addProblem(problem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeStudent() {
        System.out.println("Enter student ID");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            studentService.removeStudent(id);
            System.out.println("Student removed successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeProblem() {
        System.out.println("Enter problem ID");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            problemService.removeProblem(id);
            System.out.println("Problem removed successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStudent() {
        try {
            StudentDto student = readStudent();
            studentService.updateStudent(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProblem() {
        try {
            ProblemDto problem = readProblem();
            problemService.updateProblem(problem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
