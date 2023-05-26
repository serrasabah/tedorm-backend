package tedorm.app.application.files.controller.responses;

import tedorm.app.application.files.entity.AvatarData;
import tedorm.app.application.files.entity.FileData;

public record AvatarDataQueryModel (

        Long id,
        String name
){
    public AvatarDataQueryModel(AvatarData fileData) {
        this(
                fileData.getId(),
                fileData.getName()
        );
    }
}
