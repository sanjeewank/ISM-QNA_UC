package Group02.QNA.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private User user;

    private Boolean isRanked;
}
