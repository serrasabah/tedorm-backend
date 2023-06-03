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
@Table(name = "FileData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String filename;
    private String type;
    @Lob
    @Column(name = "imagedata")
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public FileData(String name, String type, String filename, byte[] imageData) {
        this.name=name;
        this.type=type;
        this.filename=filename;
        this.imageData=imageData;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
