package tedorm.app.application.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.repository.MenuRepository;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MessageResponse addMenu(Menu menu) {

        menuRepository.save(menu);
        return new MessageResponse(ResponseType.SUCCESS, "User has been added successfully");
    }
}
