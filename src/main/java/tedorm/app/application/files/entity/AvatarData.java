package tedorm.app.application.files.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tedorm.app.application.student.entity.Student;

import javax.persistence.*;

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
    @Column(name = "imagedata",length = 500000)
    private byte[] imageData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public AvatarData(Long id, String name, String type, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
