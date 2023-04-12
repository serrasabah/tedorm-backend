package yte.app.application.permissions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.common.response.ResponseType;
import yte.app.application.permissions.entity.Permissions;
import yte.app.application.permissions.repository.PermissionsRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PermissionsService {

    //TO-DO  absence information ile olan bağlantı sağlanacak.
    private final PermissionsRepository permissionsRepository;

    public Permissions viewPermission(Long id) {
        return permissionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }

    public MessageResponse getPermissions(Permissions permissions) {
        permissionsRepository.save(permissions);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been added successfully");
        //uyarı olarak ne geçilmeli?
    }

    public MessageResponse deletePermissions(Permissions permissions) {
        permissionsRepository.delete(permissions);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been deleted successfully");
    }

    public MessageResponse updatePermissions(Permissions permissions) {

        permissions.update(permissions);

        permissionsRepository.save(permissions);

        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been updated successfully");
    }

}