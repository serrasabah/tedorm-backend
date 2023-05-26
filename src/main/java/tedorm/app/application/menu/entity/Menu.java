package tedorm.app.application.menu.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.menu.entity.days.Days;
import tedorm.app.application.menu.entity.days.Meal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "menus")
@Getter
@NoArgsConstructor
public class Menu extends BaseEntity {

    //güne gerek var mı detayını client yapınca karar verilsin

    private LocalDateTime days;
    @Enumerated(EnumType.STRING)
    private Days weekdays;

    @Enumerated(EnumType.STRING)
    private Meal meal;
    private String food;


    public Menu(LocalDateTime days, Days weekdays, Meal meal, String food) {
        this.days = days;
        this.weekdays = weekdays;
        this.meal = meal;
        this.food = food;
    }
}
