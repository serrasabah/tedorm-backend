package tedorm.app.application.files.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.files.entity.AvatarData;
import tedorm.app.application.files.entity.FileData;
import tedorm.app.application.files.respository.AvatarRepository;
import tedorm.app.application.files.util.FileUtils;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    String[] variableFileTypes = {"image/png",
            "image/jpeg"};

    public AvatarData uploadImage(MultipartFile file, Long id) throws IOException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (!student.getFiles().isEmpty()) {
            return null;
        }

        AvatarData imageData = AvatarData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(FileUtils.compressImage(file.getBytes()))
                .student(student)
                .build();
        if (imageData == null || !(Arrays.stream(variableFileTypes).anyMatch(type -> imageData.getType().contains(type)))) {
            return null;
        }

        return repository.save(imageData);
    }


    public byte[] downloadImage(String fileName) {
        Optional<AvatarData> dbImageData = repository.findByName(fileName);
        byte[] images = FileUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public String getById(Long id) throws JsonProcessingException {
        AvatarData fileDataList = repository.findByStudentId(id);
        String imageUrl = "/images/" + fileDataList.getName();

        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }
}
