package org.leaguetable;

public class Score {

    public int score;
    public String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
