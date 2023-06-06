package tedorm.app.application.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tedorm.app.application.admin.repository.AdminRepository;
import tedorm.app.application.applicant.entity.Applicant;
import tedorm.app.application.applicant.repository.ApplicantRepository;
import tedorm.app.application.authentication.entity.Authority;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.email.service.EmailService;
import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.islemGecmisi.repository.IslemGecmisiRepository;
import tedorm.app.application.rooms.entity.Room;
import tedorm.app.application.rooms.repository.RoomRepository;
import tedorm.app.application.student.controller.requests.ChangePasswordRequest;
import tedorm.app.application.email.entity.EmailDetails;
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

    private final IslemGecmisiRepository islemGecmisiRepository;

    private AdminRepository adminRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicantRepository applicantRepository;
    private final EmailService emailService;
    private final RoomRepository roomRepository;


    public MessageResponse addStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Email already exists");
        }


        if(!emailValidation(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Invalid Email Address");
        }

        boolean roomResult = checkRoomAvaliable(student);
        if(!roomResult){
            return new MessageResponse(ResponseType.WARNING, "No available room in the specified room type! Please check room page");
        }

        User user = new User();
        user.getAuthorities().add(new Authority("STUDENT"));
        user.setUsername(student.getEmail());
        String password = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(password));

        EmailDetails details = new EmailDetails();
        details.setRecipient(student.getEmail());
        details.setSubject("TEDORM USER INFORMATION");
        String emailBody =
                 "        Welcome to Our Platform!\n"
                + "            Dear " + student.getName() + ",\n"
                + "            We are pleased to inform you that you have been successfully registered on TEDORM. Welcome to our dormitory!\n"
                + "        \n"
                + "       \n"
                + "            To access your account, please find below your login credentials:\n"
                + "        \n"
                + "        \n"
                + "         Email: " + student.getEmail() + "\n"
                + "          Password: " + password + "\n"
                + "        \n"
                + "        \n"
                + "            Please note that the password is case-sensitive and should be kept confidential. We recommend changing your password after logging in for security purposes.\n"
                + "        \n"
                + "        \n"
                + "            If you have any questions or need assistance, our support team is here to help. Feel free to reach out to us at tedorm@tedu.edu.tr or +90 312 585 02 00.\n"
                + "        \n"
                + "        \n"
                + "            Thank you for choosing our dormitory. We look forward to providing you with a great experience.\n"
                + "        \n"
                + "    \n"
                + "    \n"
                + "        \n"
                + "            Best regards,\n"
                + "            TEDU Dormitories Directorate\n"
                + "        \n"
                + "    \n";
        details.setMsgBody(emailBody);
        boolean result = emailService.sendSimpleMail(details);
        if(!result){
            return new MessageResponse(ResponseType.WARNING, "mail could not be sent");
        }


    //    student.setUser(user);
        user.setStudent(student);
        studentRepository.save(student);
        userRepository.save(user);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + "student is added." + student.getCreatedDate());
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "User has been added successfully");
    }

    public MessageResponse addApplicantToStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Username already exists");
        }
        if(!emailValidation(student.getEmail())){
            return new MessageResponse(ResponseType.WARNING, "Invalid Email Address");
        }

        boolean roomResult = checkRoomAvaliable(student);
        if(!roomResult){
            return new MessageResponse(ResponseType.WARNING, "No available room in the specified room type! Please check room page");
        }

        User user = new User();
        user.getAuthorities().add(new Authority("STUDENT"));
        user.setUsername(student.getEmail());
        String password = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(password));

        EmailDetails details = new EmailDetails();
        details.setRecipient(student.getEmail());
        details.setSubject("TEDORM USER INFORMATION");
        String emailBody =
                "        Welcome to Our Platform!\n"
                        + "            Dear " + student.getName() + ",\n"
                        + "            We are pleased to inform you that you have been successfully registered on TEDORM. Welcome to our dormitory!\n"
                        + "        \n"
                        + "       \n"
                        + "            To access your account, please find below your login credentials:\n"
                        + "        \n"
                        + "        \n"
                        + "         Email: " + student.getEmail() + "\n"
                        + "          Password: " + password + "\n"
                        + "        \n"
                        + "        \n"
                        + "            Please note that the password is case-sensitive and should be kept confidential. We recommend changing your password after logging in for security purposes.\n"
                        + "        \n"
                        + "        \n"
                        + "            If you have any questions or need assistance, our support team is here to help. Feel free to reach out to us at tedorm@tedu.edu.tr or +90 312 585 02 00.\n"
                        + "        \n"
                        + "        \n"
                        + "            Thank you for choosing our dormitory. We look forward to providing you with a great experience.\n"
                        + "        \n"
                        + "    \n"
                        + "    \n"
                        + "        \n"
                        + "            Best regards,\n"
                        + "            TEDU Dormitories Directorate\n"
                        + "        \n"
                        + "    \n";
        details.setMsgBody(emailBody);
        boolean result = emailService.sendSimpleMail(details);
        if(!result){
            return new MessageResponse(ResponseType.WARNING, "mail could not be sent");
        }
        Applicant applicant = applicantRepository.findByEmail(student.getEmail());
        applicantRepository.delete(applicant);
     //   student.setUser(user);
        user.setStudent(student);
        studentRepository.save(student);
        userRepository.save(user);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + " has been added. " + student.getCreatedDate());
        islemGecmisiRepository.save(islemGecmisi);
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
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Room room = roomRepository.findByRoomNumber(student.getRoomNumber());
        if(!(room ==null)){
            room.setAvailableSlots(room.getAvailableSlots() + 1);
        }
        studentRepository.deleteById(id);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + "was deleted." + student.getCreatedDate());
        islemGecmisiRepository.save(islemGecmisi);
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
        Room room = roomRepository.findByRoomNumber(student.getRoomNumber());
        if(room==null){
            return new MessageResponse(ResponseType.WARNING, "Room Not found");
        }
        student.update(updatedStudent);

        studentRepository.save(student);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + " information has been updated. " + student.getCreatedDate());
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "Student has been updated successfully");
    }

    public MessageResponse updateStudentForAdmin(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.updateForAdmin(updatedStudent);

        studentRepository.save(student);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + " information has been updated. " + student.getCreatedDate());
        islemGecmisiRepository.save(islemGecmisi);
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


    public MessageResponse changePassword(Long id, ChangePasswordRequest passwordFields) {
        User user = userRepository.findByStudentId(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        String encodedPassword = user.getPassword();
        String plainTextPassword = passwordFields.getEskiSifre();

        if (passwordEncoder.matches(plainTextPassword, encodedPassword)) {
            if (passwordFields.getYeniSifre().equals(passwordFields.getYeniSifreTekrar())) {
                String newEncodedPassword = passwordEncoder.encode(passwordFields.getYeniSifre());
                user.setPassword(newEncodedPassword);
                userRepository.save(user);
                return new MessageResponse(ResponseType.SUCCESS, "Şifre değiştirildi");
            }
        }

        return new MessageResponse(ResponseType.ERROR, "Şifreler uyuşmuyor");
    }
    public Boolean checkRoomAvaliable(Student student) {
        List<Room> rooms = roomRepository.findByRoomTypeAndAvailableSlotsGreaterThan(student.getRoomType(), 0);

        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                if(room.getRoomNumber().equals(student.getRoomNumber())){
                    student.setRoomNumber(room.getRoomNumber());
                    room.setAvailableSlots(room.getAvailableSlots() - 1);
                    roomRepository.save(room);
                    studentRepository.save(student);
                    return true;
                }
            }
        }
        return false;
    }


}