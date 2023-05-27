package tedorm.app.application.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.files.entity.FileData;
import tedorm.app.application.files.respository.FileRepository;
import tedorm.app.application.files.util.FileUtils;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    String[] variableFileTypes = {"application/pdf", "image/png",
                                "image/jpeg"};

    public MessageResponse uploadImage(MultipartFile file, String name, Long id) throws IOException, SQLException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        List<FileData> fileDataList = fileRepository.findByStudentId(id);

        Blob imageData = new SerialBlob(FileUtils.compressImage(file.getBytes()));

        FileData fileData = FileData.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(imageData)
                .name(name)
                .student(student)
                .build();

        if (fileDataList.stream().anyMatch(x -> x.getName().equals(fileData.getName()))) {
            return new MessageResponse(ResponseType.WARNING, fileData.getName() + " önceden yüklenmiş!");
        }

        if (fileData == null || !(Arrays.stream(variableFileTypes).anyMatch(type -> fileData.getType().contains(type)))) {
            return new MessageResponse(ResponseType.WARNING, "Dosya tipi uygun değil !");
        }

        fileRepository.save(fileData);

        return new MessageResponse(ResponseType.SUCCESS, "File uploaded successfully: " + file.getOriginalFilename());
    }


    @Transactional(readOnly = true)
    public byte[] downloadImage(String fileName, Long id) {
        List<FileData> fileDataList = fileRepository.findByStudentId(id);

        // Find the FileData with the matching file name
        FileData dbImageData = fileDataList.stream()
                .filter(fileData -> fileData.getFilename().equals(fileName))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("File not found"));

        Blob imageData = dbImageData.getImageData();
        try {
            byte[] fileContent = imageData.getBytes(1, (int) imageData.length());
            return FileUtils.decompressImage(fileContent);
        } catch (SQLException ex) {
            // Handle any exceptions that may occur during blob retrieval or decompression
            throw new RuntimeException("Failed to download image", ex);
        }
    }


    public List<FileData> getAllFilesByStudent() {
        return fileRepository.findAll();
    }

    public List<FileData> getById(Long id) {
        List<FileData> fileDataList = fileRepository.findByStudentId(id);
        return fileDataList;    }

    public String getContentTypeForFileName(String fileName) {
        String contentType;
        if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    public MessageResponse deleteFile(String documentName, Long id) {
        List<FileData> fileDataList = fileRepository.findByStudentId(id);

        FileData dbImageData = fileDataList.stream()
                .filter(fileData -> fileData.getFilename().equals(documentName))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("File not found"));

        fileRepository.deleteById(dbImageData.getId());

        return new MessageResponse(ResponseType.SUCCESS, "File has been deleted successfully");
    }
}
