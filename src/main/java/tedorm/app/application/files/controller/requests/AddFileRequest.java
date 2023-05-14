package tedorm.app.application.files.controller.requests;

import lombok.Builder;
import tedorm.app.application.files.entity.FileData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
public record AddFileRequest (
    @NotBlank
    @Size(max = 25)
    String name,
    @NotBlank
    String type,

    String filename,
    byte[] imageData

) {

        public FileData toDomainEntity() {
            return new FileData(name, type, filename, imageData);
        }
}
