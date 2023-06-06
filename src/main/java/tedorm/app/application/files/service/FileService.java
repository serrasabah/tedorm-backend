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
import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.islemGecmisi.repository.IslemGecmisiRepository;
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
    @Autowired
    private IslemGecmisiRepository islemGecmisiRepository;

    String[] variableFileTypes = {"application/pdf", "image/png",
                                "image/jpeg", "image/jpg"};

    public MessageResponse uploadImage(MultipartFile file, String name, Long id) throws IOException, SQLException {

        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        List<FileData> fileDataList = fileRepository.findByStudentId(id);


        FileData fileData = FileData.builder()
                .filename(file.getOriginalFilename())
                .type(file.getContentType())
                .name(name)
                .student(student)
                .imageData(FileUtils.compressImage(file.getBytes())).build();



        if (fileDataList.stream().anyMatch(x -> x.getName().equals(fileData.getName()))) {
            return new MessageResponse(ResponseType.WARNING, fileData.getName() + " önceden yüklenmiş!");
        }

        if (fileData == null || !(Arrays.stream(variableFileTypes).anyMatch(type -> fileData.getType().contains(type)))) {
            return new MessageResponse(ResponseType.WARNING, "File type not available !");
        }

        fileRepository.save(fileData);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname() + " uploaded the document. " + student.getCreatedDate() + " File name: " + fileData.getFilename());
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "File uploaded successfully");
    }


    @Transactional(readOnly = true)
    public byte[] downloadImage(String fileName, Long id) {
        List<FileData> fileDataList = fileRepository.findByStudentId(id);

        FileData dbImageData = fileDataList.stream()
                .filter(fileData -> fileData.getFilename().equals(fileName))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("File not found"));

        byte[] imageData = dbImageData.getImageData();
        byte[] images=FileUtils.decompressImage(dbImageData.getImageData());
        return images;
    }


    public List<FileData> getAllFilesByStudent() {
        return fileRepository.findAll();
    }

    public List<FileData> getById(Long id) {
        List<FileData> fileDataList = fileRepository.findByStudentId(id);
        return fileDataList;
    }

    public String getContentTypeForFileName(String fileName) {
        String contentType;
        if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (fileName.endsWith(".png")) {
            contentType = "image/png";
        } else if (fileName.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (fileName.endsWith(".jpg")) {
            contentType = "image/jpg";
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
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(dbImageData.getStudent().getName() + " " + dbImageData.getStudent().getSurname() + " deleted the document. " + dbImageData.getStudent().getCreatedDate() + " File Name: " + dbImageData.getName());
        islemGecmisiRepository.save(islemGecmisi);

        return new MessageResponse(ResponseType.SUCCESS, "File has been deleted successfully");
    }
}
