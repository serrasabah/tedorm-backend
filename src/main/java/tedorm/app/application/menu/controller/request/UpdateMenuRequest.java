package tedorm.app.application.menu.controller.request;

import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.days.Meal;
import tedorm.app.application.student.entity.Student;

import java.time.LocalDate;

public record UpdateMenuRequest(
        Meal meal,
        String food
) {

    public Menu toDomainEntity() {
        return new Menu(meal, food);
    }
}
