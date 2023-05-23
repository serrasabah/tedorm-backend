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
        String contentType = storageService.getContentTypeForFileName(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileContent);
    }

    @GetMapping
    public List<FileDataQueryModel> getAllFilesByStudent() {
        return storageService.getAllFilesByStudent()
                .stream()
                .map(file -> new FileDataQueryModel(file))
                .toList();
    }
}
