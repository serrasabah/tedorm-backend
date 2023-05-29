package tedorm.app.application.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.address.entity.Address;
import tedorm.app.application.address.repository.AddressRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public MessageResponse addAddress(Address address) {
        addressRepository.save(address);
        return new MessageResponse(ResponseType.SUCCESS, "Address has been added successfully");
    }

    public List<Address> getAllAdress() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

}