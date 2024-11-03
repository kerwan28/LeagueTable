package Pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Score {

    public int score;
    public String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

}
