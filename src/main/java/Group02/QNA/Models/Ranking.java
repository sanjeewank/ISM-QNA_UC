package Group02.QNA.Models;

import javax.persistence.*;

@Entity
public class Ranking {

    @ManyToOne
    private Answer answer;
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;


    public Ranking(Answer answer,User user){
        this.answer = answer;
        this.user = user;
    }

    public Ranking() {

    }
}