package tedorm.app.application.menu.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.menu.entity.days.Meal;
import tedorm.app.application.student.entity.Student;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
public class Menu extends BaseEntity {

    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Meal meal;
    private String food;
    private String day;

    public Menu(LocalDate date, Meal meal, String food) {
        this.date = date;
        this.meal = meal;
        this.food = food;
    }

    public Menu(Meal meal, String food) {
        this.meal = meal;
        this.food = food;
    }

    public void update(Menu updatedMenu) {
        this.meal = updatedMenu.meal;
        this.food = updatedMenu.food;
    }
}
