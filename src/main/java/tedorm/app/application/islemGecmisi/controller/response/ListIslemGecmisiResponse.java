package tedorm.app.application.islemGecmisi.controller.response;

import tedorm.app.application.islemGecmisi.entity.IslemGecmisi;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.days.Meal;

import java.time.LocalDate;

public record ListIslemGecmisiResponse(
        Long id,
        String message
) {

    public ListIslemGecmisiResponse(IslemGecmisi islemGecmisi) {
        this(
                islemGecmisi.getId(),
                islemGecmisi.getMessage()

        );
    }

}
