package tedorm.app.application.permissions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.permissions.entity.Permissions;
import tedorm.app.application.permissions.repository.PermissionsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionsService {

    //TO-DO  absence information ile olan bağlantı sağlanacak.
    //TO-DO
    private final PermissionsRepository permissionsRepository;

    public Permissions getPermissions(Long id) {
        return permissionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }
    public List<Permissions> getAllPermissions() {
        return permissionsRepository.findAll();
    }

    public MessageResponse addPermissions(Permissions permissions) {
        permissionsRepository.save(permissions);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been added successfully");
        //uyarı olarak ne geçilmeli?
    }

    public MessageResponse deletePermissions(Long id) {
        permissionsRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been deleted successfully");
    }

    public MessageResponse updatePermissions(Long id, Permissions updatePermission) {
        Permissions permission= permissionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        permission.update(updatePermission);

        permissionsRepository.save(permission);

        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been updated successfully");
    }

}