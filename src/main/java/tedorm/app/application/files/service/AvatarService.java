package tedorm.app.application.files.service;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    String[] variableFileTypes = {"image/png",
            "image/jpeg"};

    public MessageResponse uploadImage(MultipartFile file, Long id) throws IOException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AvatarData imageData = AvatarData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(FileUtils.compressImage(file.getBytes()))
                .student(student)
                .build();
        if (imageData == null || !(Arrays.stream(variableFileTypes).anyMatch(type -> imageData.getType().contains(type)))) {
            return new MessageResponse(ResponseType.WARNING, "ERROR");

        }
        repository.save(imageData);
        return new MessageResponse(ResponseType.SUCCESS, "File uploaded successfully: " + file.getOriginalFilename());

    }

    public byte[] downloadImage(String fileName){
        Optional<AvatarData> dbImageData = repository.findByName(fileName);
        byte[] images=FileUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public List<AvatarData> getById(Long id) {
        List<AvatarData> fileDataList = repository.findByStudentId(id);
        return fileDataList;    }
}
