package Pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClubData {

    public int score;
    public String name;

    public ClubData(int score, String name){
        this.score = score;
        this.name = name;
    }

}
