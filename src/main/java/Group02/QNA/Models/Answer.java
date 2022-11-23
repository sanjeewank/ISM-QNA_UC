package Group02.QNA.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Answer extends Message{
    @ManyToOne
    private Question question;

}
