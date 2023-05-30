package tedorm.app.application.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.repository.MenuRepository;
import tedorm.app.application.student.entity.Student;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MessageResponse addMenu(Menu menu) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(menu.getDate().toString(), inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE");
        String finalDay = date.format(outputFormatter);

        menu.setDay(finalDay);
        menuRepository.save(menu);
        return new MessageResponse(ResponseType.SUCCESS, "Menu has been added successfully");
    }

    public List<Menu> getAllMenus() {
        List<Menu> listMenus =  menuRepository.findAll();
        return  listMenus;
    }

    public List<Menu> getMenus() {
        LocalDate currentDate = LocalDate.now();
        List<Menu> listMenus =  menuRepository.findByDateGreaterThanEqual(currentDate);
        return  listMenus.stream()
                .sorted(Comparator.comparing(Menu::getDate)).limit(7)
                .collect(Collectors.toList());
    }

    public MessageResponse deleteMenuById(Long id){
        menuRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Menu has been deleted successfully");
    }

    public MessageResponse updateMenu(Long id, Menu updatedMenu) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        menu.update(updatedMenu);

        menuRepository.save(menu);

        return new MessageResponse(ResponseType.SUCCESS, "Menu has been updated successfully");
    }
}
