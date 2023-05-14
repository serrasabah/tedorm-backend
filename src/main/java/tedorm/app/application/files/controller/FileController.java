package tedorm.app.application.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.files.controller.responses.FileDataQueryModel;
import tedorm.app.application.files.service.FileService;
import tedorm.app.application.student.controller.responses.StudentQueryModel;

import java.io.IOException;
import java.util.List;

@RequestMapping("/image")
@RestController
public class FileController {

    @Autowired
    private FileService storageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse uploadImage(@RequestParam("image") MultipartFile file, String name) throws IOException {
        return storageService.uploadImage(file, name);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        byte[] fileContent = storageService.downloadImage(fileName);
        String contentType = getContentTypeForFileName(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileContent);
    }

    private String getContentTypeForFileName(String fileName) {
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

    @GetMapping
    public List<FileDataQueryModel> getAllFilesByStudent() {
        return storageService.getAllFilesByStudent()
                .stream()
                .map(file -> new FileDataQueryModel(file))
                .toList();
    }
}
