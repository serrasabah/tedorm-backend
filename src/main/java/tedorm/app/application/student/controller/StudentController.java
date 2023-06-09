package tedorm.app.application.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.student.controller.requests.AddStudentRequest;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.student.controller.requests.UpdateStudentRequest;
import tedorm.app.application.student.controller.responses.StudentQueryModel;
import tedorm.app.application.student.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public MessageResponse addStudent(@Valid @RequestBody AddStudentRequest addStudentRequest) {
        return studentService.addStudent(addStudentRequest.toDomainEntity());
    }

    @GetMapping
    public List<StudentQueryModel> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(student -> new StudentQueryModel(student))
                .toList();
    }

    @GetMapping("/{id}")
    public StudentQueryModel getById(@NotNull @PathVariable Long id) {
        return new StudentQueryModel(studentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteStudentById(@PathVariable @NotNull Long id) {
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateStudent(@Valid @RequestBody UpdateStudentRequest request, @PathVariable Long id) {
        return studentService.updateStudent(id, request.toDomainEntity());
    }
}