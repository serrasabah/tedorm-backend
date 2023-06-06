package tedorm.app.application.permissions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedorm.app.application.address.entity.Address;
import tedorm.app.application.address.repository.AddressRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.islemGecmisi.repository.IslemGecmisiRepository;
import tedorm.app.application.permissions.entity.Permissions;
import tedorm.app.application.permissions.repository.PermissionsRepository;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionsService {

    //TO-DO  absence information ile olan bağlantı sağlanacak.
    //TO-DO
    private final PermissionsRepository permissionsRepository;
    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;

    @Autowired
    private IslemGecmisiRepository islemGecmisiRepository;

    public Permissions getPermissions(Long id) {
        return permissionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
    }
    public List<Permissions> getAllPermissions() {
        return permissionsRepository.findAll();
    }

    public MessageResponse addPermissions(Permissions permissions) {
        Student student = studentRepository.findById(permissions.getStudent().getId()).orElseThrow();
        Address address = addressRepository.findById(permissions.getAddress().getId()).orElseThrow();
        permissions.setAddress(address);
        permissions.setStudent(student);
        permissionsRepository.save(permissions);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage(student.getName() + " " + student.getSurname()+ " added permission");
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been added successfully");
    }

    public MessageResponse deletePermissions(Long id) {
        permissionsRepository.deleteById(id);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage("Permission information has been deleted.");
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been deleted successfully");
    }

    public MessageResponse updatePermissions(Long id, Permissions updatePermission) {
        Permissions permission= permissionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        permission.update(updatePermission);

        permissionsRepository.save(permission);
        IslemGecmisi islemGecmisi = new IslemGecmisi();
        islemGecmisi.setMessage("permission güncellendi");
        islemGecmisiRepository.save(islemGecmisi);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been updated successfully");
    }

}