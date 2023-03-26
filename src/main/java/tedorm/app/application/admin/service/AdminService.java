package tedorm.app.application.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.admin.repository.AdminRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminRepository adminRepository;

    public MessageResponse addAdmin(Admin admin) {
        adminRepository.save(admin);
        return new MessageResponse(ResponseType.SUCCESS, "SUCCESS");
    }

    public MessageResponse updateAdmin(Long id, Admin updateAdmin){
        Admin admin = adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No record"));

        adminRepository.save(updateAdmin);

        admin.update(updateAdmin);
        return new MessageResponse(ResponseType.SUCCESS,"Updated");
    }

    public MessageResponse deleteAdminById(Long id){
        adminRepository.deleteById(id);
        return new
                MessageResponse(ResponseType.SUCCESS,"Deleted");
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No records find"));
    }
    @GetMapping("{id}")
    public Admin getById(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No records find"));

    }

}