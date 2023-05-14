package tedorm.app.application.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.files.entity.FileData;
import tedorm.app.application.files.respository.FileRepository;
import tedorm.app.application.files.util.FileUtils;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository storageRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageResponse uploadImage(MultipartFile file, String name) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User users = userRepository.findByUsername(username).orElseThrow();
        Student student = studentRepository.findById(users.getStudent().getId()).orElseThrow(() -> new EntityNotFoundException("Student not found"));;
        FileData imageData = FileData.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(FileUtils.compressImage(file.getBytes())).build();
        imageData.setName(name);
        if(imageData == null){
                return new MessageResponse(ResponseType.WARNING, "ERROR");
        }

        storageRepository.save(imageData);
        student.addFile(imageData);
        studentRepository.save(student);
        return new MessageResponse(ResponseType.SUCCESS, "file uploaded successfully : " + file.getOriginalFilename());
    }

    @Transactional(readOnly=true)
    public byte[] downloadImage(String fileName){
        FileData dbImageData = storageRepository.findByFilename(fileName);
        byte[] images = FileUtils.decompressImage(dbImageData.getImageData());
        return images;
    }

    public List<FileData> getAllFilesByStudent() {
        return storageRepository.findAll();
    }
}
