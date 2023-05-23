package tedorm.app.application.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tedorm.app.application.authentication.entity.Authority;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.student.controller.responses.StudentQueryModel;
import tedorm.app.application.student.entity.EmailDetails;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public MessageResponse addStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Username already exists");
        }
        if(!emailValidation(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Invalid Email Address");
        }
        User user = new User();
        user.getAuthorities().add(new Authority("STUDENT"));
        user.setUsername(student.getEmail());
        String password = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(password));
        EmailDetails details = new EmailDetails();
        details.setRecipient(student.getEmail());
        details.setSubject("TEDORM USER INFORMATION");
        details.setMsgBody("You have been successfully added to the system. " + student.getEmail() + " şifreniz: " + password);
        boolean result = emailService.sendSimpleMail(details);
        if(!result){
            return new MessageResponse(ResponseType.WARNING, "mail could not be sent");
        }
        student.setUser(user);
        user.setStudent(student);
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

    public String generateRandomPassword()
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();

        String password = IntStream.range(0, 8)
                .map(i -> random.nextInt(chars.length()))
                .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
                .collect(Collectors.joining());

        return userRepository.existsByPassword(password) ? generateRandomPassword() : password;
    }

    public static boolean emailValidation(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}