package tedorm.app.application.islemGecmisi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "islemGecmisi")
@Getter
@Setter
@NoArgsConstructor
public class IslemGecmisi extends BaseEntity {

        private String message;

        public IslemGecmisi(String message) {
                this.message = message;
        }
}
