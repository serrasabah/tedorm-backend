package tedorm.app.application.islemGecmisi.controller.request;

import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.days.Meal;

import java.time.LocalDate;

public record AddIslemGecmisiRequest (
        String message

){  public IslemGecmisi toDomainEntity() {
    return new IslemGecmisi(message);
}
}
