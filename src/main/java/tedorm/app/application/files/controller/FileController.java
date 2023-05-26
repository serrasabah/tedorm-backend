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
import tedorm.app.application.files.entity.FileData;
import tedorm.app.application.files.service.FileService;
import tedorm.app.application.student.controller.responses.StudentQueryModel;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/image")
@RestController
public class FileController {

    @Autowired
    private FileService storageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessageResponse uploadImage(@RequestParam("image") MultipartFile file, String name, Long id) throws IOException, SQLException {
        return storageService.uploadImage(file, name, id);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, @RequestParam Long id) {
        byte[] fileContent = storageService.downloadImage(fileName, id);
        String contentType = storageService.getContentTypeForFileName(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileContent);
    }


    @GetMapping("/listFiles")
    public List<FileDataQueryModel> getAllFilesByStudent() {
        return storageService.getAllFilesByStudent()
                .stream()
                .map(file -> new FileDataQueryModel(file))
                .toList();
    }

    @GetMapping("/list/{id}")
    public List<FileDataQueryModel> getById(@NotNull @PathVariable Long id) {
        return storageService.getById(id)
                .stream()
                .map(file -> new FileDataQueryModel(file))
                .toList();
    }

    @DeleteMapping("/sil/{documentName}")
    public MessageResponse deleteDocument(@PathVariable String documentName, @RequestParam Long id) {

        return storageService.deleteFile(documentName, id);
    }
}
