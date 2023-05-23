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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    String[] variableFileTypes = {"application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                                "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};

    public MessageResponse uploadImage(MultipartFile file, String name) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName() ;
        User users = userRepository.findByUsername(username).orElseThrow();
        Long id = (users.getStudent().getId());
        Student student = studentRepository.findById(users.getStudent().getId()).orElseThrow(() -> new EntityNotFoundException("Student not found"));;
        FileData imageData = FileData.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(FileUtils.compressImage(file.getBytes())).build();
        imageData.setName(name);
        if (imageData == null || Arrays.stream(variableFileTypes).anyMatch(type -> imageData.getType().contains(type))) {
            return new MessageResponse(ResponseType.WARNING, "ERROR");
        }

        fileRepository.save(imageData);
        student.addFile(imageData);
        return new MessageResponse(ResponseType.SUCCESS, "file uploaded successfully : " + file.getOriginalFilename());
    }

    @Transactional(readOnly=true)
    public byte[] downloadImage(String fileName){
        FileData dbImageData = fileRepository.findByFilename(fileName);
        byte[] images = FileUtils.decompressImage(dbImageData.getImageData());
        return images;
    }

    public List<FileData> getAllFilesByStudent() {
        return fileRepository.findAll();
    }

    public String getContentTypeForFileName(String fileName) {
        String contentType;
        if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (fileName.endsWith(".doc")) {
            contentType = "application/msword";
        } else if (fileName.endsWith(".docx")) {
            contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (fileName.endsWith(".xls")) {
            contentType = "application/vnd.ms-excel";
        } else if (fileName.endsWith(".xlsx")) {
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else {
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}
