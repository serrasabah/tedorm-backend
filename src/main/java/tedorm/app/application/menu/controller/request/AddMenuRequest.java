package tedorm.app.application.menu.controller.request;

import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.days.Meal;

import java.time.LocalDate;

public record AddMenuRequest (
     LocalDate date,

     Meal meal,
     String food

){  public Menu toDomainEntity() {
        return new Menu(date, meal, food);
    }
}
