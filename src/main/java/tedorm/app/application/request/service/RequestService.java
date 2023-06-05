package tedorm.app.application.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.request.entity.Requests;
import tedorm.app.application.request.repository.RequestRepository;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final StudentRepository studentRepository;


    public Requests getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requests not found."));
    }
    public List<Requests> getAllRequest() {
        return requestRepository.findAll();
    }

    public MessageResponse addRequest(Requests request) {
        Student student = studentRepository.findById(request.getStudent().getId()).orElseThrow();
        request.setStudent(student);
        requestRepository.save(request);
        return new MessageResponse(ResponseType.SUCCESS, "Requests information has been added successfully.");
    }

    public MessageResponse deleteRequest(Long id) {
        requestRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Requests information has been deleted successfully");
    }

    public MessageResponse updateRequest(Long id, Requests updateRequest) {
        Requests request= requestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requests not found."));

        request.update(updateRequest);
        requestRepository.save(request);

        return new MessageResponse(ResponseType.SUCCESS, "Requests information has been updated successfully.");
    }
}