package tedorm.app.application.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.service.MenuService;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

@PostMapping
    public MessageResponse addMenu(@RequestBody Menu menu){
    return menuService.addMenu(menu);
}
}