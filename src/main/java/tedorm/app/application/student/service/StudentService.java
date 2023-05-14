package tedorm.app.application.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tedorm.app.application.authentication.entity.Authority;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.student.controller.responses.StudentQueryModel;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    public MessageResponse addStudent(Student student) {
        if(studentRepository.existsByName(student.getName())){
            return new MessageResponse(ResponseType.WARNING, "Username already exists");
        }

        User user = new User();
        user.setStudent(student);
        user.getAuthorities().add(new Authority("STUDENT"));
        user.setUsername(student.getEmail());
        user.setPassword("deneme");
        studentRepository.save(student);
        userRepository.save(user);
        return new MessageResponse(ResponseType.SUCCESS, "User has been added successfully");
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    public MessageResponse deleteStudentById(Long id) {
        studentRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Student has been deleted successfully");
    }

    public List<Student> getStudentByUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User users = userRepository.findByUsername(name).orElseThrow();
        return studentRepository.findByUserId(users.getId());

    }


    public MessageResponse updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.update(updatedStudent);

        studentRepository.save(student);

        return new MessageResponse(ResponseType.SUCCESS, "Student has been updated successfully");
    }
}