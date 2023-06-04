package tedorm.app.application.islemGecmisi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.islemGecmisi.controller.request.AddIslemGecmisiRequest;
import tedorm.app.application.islemGecmisi.controller.response.ListIslemGecmisiResponse;
import tedorm.app.application.islemGecmisi.service.IslemGecmisiService;
import tedorm.app.application.menu.controller.request.AddMenuRequest;
import tedorm.app.application.menu.controller.response.ListMenuResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/islemGecmisi")
public class IslemGecmisiController {

    private final IslemGecmisiService islemGecmisiService;

    @PostMapping
    public MessageResponse addIslemGecmisi(@RequestBody AddIslemGecmisiRequest menu){
        return islemGecmisiService.addIslemGecmisi(menu.toDomainEntity());
    }

    @GetMapping
    public List<ListIslemGecmisiResponse> getAllIslemGecmisi() {
        return islemGecmisiService.getAllIslemGecmisi()
                .stream()
                .map(islemGecmisi -> new ListIslemGecmisiResponse(islemGecmisi))
                .toList();
    }

}
