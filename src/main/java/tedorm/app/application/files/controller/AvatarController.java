package tedorm.app.application.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.files.controller.responses.AvatarDataQueryModel;
import tedorm.app.application.files.service.AvatarService;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    private AvatarService service;

    @PostMapping
    public MessageResponse uploadImage(@RequestParam("avatar")MultipartFile file, Long id) throws IOException {
        return service.uploadImage(file, id);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @GetMapping("/list/{id}")
    public List<AvatarDataQueryModel> getById(@NotNull @PathVariable Long id) {
        return service.getById(id)
                .stream()
                .map(file -> new AvatarDataQueryModel(file))
                .toList();
    }
}
