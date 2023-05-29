package tedorm.app.application.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.address.controller.requests.AddAddressRequest;
import tedorm.app.application.address.controller.responses.AddressResponse;
import tedorm.app.application.address.service.AddressService;
import tedorm.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Validated
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public MessageResponse addAddress(@Valid @RequestBody AddAddressRequest addAddressRequest) {
        return addressService.addAddress(addAddressRequest.toDomainEntity());
    }

    @GetMapping
    public List<AddressResponse> getAllAddress() {
        return addressService.getAllAdress()
                .stream()
                .map(AddressResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public AddressResponse getById(@NotNull @PathVariable Long id) {
        return new AddressResponse(addressService.getById(id));
    }

}