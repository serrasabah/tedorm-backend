package tedorm.app.application.files.controller.responses;

import tedorm.app.application.files.entity.FileData;

public record FileDataQueryModel (

        Long id,
        String name,
        String fileName
){
    public FileDataQueryModel(FileData fileData) {
        this(
                fileData.getId(),
                fileData.getName(),
                fileData.getFilename()
        );
    }
}



