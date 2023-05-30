package tedorm.app.application.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.menu.controller.request.AddMenuRequest;
import tedorm.app.application.menu.controller.request.UpdateMenuRequest;
import tedorm.app.application.menu.controller.response.ListMenuResponse;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.service.MenuService;
import tedorm.app.application.student.controller.requests.UpdateStudentRequest;
import tedorm.app.application.student.controller.responses.NameSurnameRoomNumStudentQueryModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

@PostMapping
    public MessageResponse addMenu(@RequestBody AddMenuRequest menu){
    return menuService.addMenu(menu.toDomainEntity());
}

    @GetMapping
    public List<ListMenuResponse> getAllMenus() {
        return menuService.getAllMenus()
                .stream()
                .map(menu -> new ListMenuResponse(menu))
                .toList();
    }

    @GetMapping("/maxseven")
    public List<ListMenuResponse> getMenus() {
        return menuService.getMenus()
                .stream()
                .map(menu -> new ListMenuResponse(menu))
                .toList();
    }

    @PutMapping("/{id}")
    public MessageResponse updateStudent(@Valid @RequestBody UpdateMenuRequest request, @PathVariable Long id) {
        return menuService.updateMenu(id, request.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteMenuById(@PathVariable @NotNull Long id) {
        return menuService.deleteMenuById(id);
    }
}