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
@TypeDefs({
        @TypeDef(name = "blob", typeClass = org.hibernate.type.BlobType.class)
})
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String type;
    private String name;

    @Lob
    @Type(type = "blob")
    @Column(name = "filedata", columnDefinition = "bytea")
    private Blob imageData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public FileData(String name, String type, String filename, Blob imageData) {
        this.name=name;
        this.type=type;
        this.filename=filename;
        this.imageData=imageData;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
