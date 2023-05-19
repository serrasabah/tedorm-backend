package yte.app.application.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.app.application.address.controller.requests.AddAddressRequest;
import yte.app.application.address.controller.responses.AddressResponse;
import yte.app.application.address.service.AddressService;
import yte.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/addresss")
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