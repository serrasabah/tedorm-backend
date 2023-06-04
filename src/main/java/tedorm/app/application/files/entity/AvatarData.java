package tedorm.app.application.files.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.student.entity.Student;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "AvatarData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvatarData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata")
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public AvatarData(String name, String type, byte[] imageData) {
        this.name=name;
        this.type=type;
        this.imageData=imageData;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
