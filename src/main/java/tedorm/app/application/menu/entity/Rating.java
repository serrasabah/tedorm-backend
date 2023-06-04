package tedorm.app.application.menu.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;


import javax.persistence.*;


@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
public class Rating extends BaseEntity {

    @Column
    private Long yemekId;
    @Column
    private Long studentId;

    @Column
    private int count;

    @Column
    private double puan = 0;
}
