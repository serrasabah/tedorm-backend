package tedorm.app.application.request.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.student.entity.Student;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Requests extends BaseEntity {

    private String message;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "ID")
    private Student student;


    public Requests(String message,
                    Long studentId) {

        this.message = message;
        this.student=new Student();
        this.student.setId(studentId);
    }

    public void update(Requests updatePermissionDates) {
        this.message = updatePermissionDates.message;

    }
}