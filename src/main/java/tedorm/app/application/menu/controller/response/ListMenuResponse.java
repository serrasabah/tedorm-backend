package tedorm.app.application.menu.controller.response;

import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.menu.entity.days.Meal;

import java.time.LocalDate;

public record ListMenuResponse(
        Long id,
         LocalDate date,
         Meal meal,
         String food,
         String day
) {

    public ListMenuResponse(Menu menu) {
        this(
                menu.getId(),
                menu.getDate(),
                menu.getMeal(),
                menu.getFood(),
                menu.getDay()
        );
    }

    }
