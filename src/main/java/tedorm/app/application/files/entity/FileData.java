package tedorm.app.application.files.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.student.entity.Student;

import javax.persistence.*;

@Entity
@Table(name = "FileData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData extends BaseEntity {

    private String filename;
    private String type;
    private String name;

    @Lob
    @Column(name = "filedata", length = 20000)
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
