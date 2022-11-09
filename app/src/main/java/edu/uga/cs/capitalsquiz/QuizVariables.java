package edu.uga.cs.capitalsquiz;

/**
 * This class defines all the questions displayed in the quiz.
 */

public class QuizVariables {

    //quiz variables and associated information
    private long   id;
    private Questions q1;
    private Questions q2;
    private Questions q3;
    private Questions q4;
    private Questions q5;
    private Questions q6;
    private Integer numberAnswered;
    private String date;
    private Integer score;

    public QuizVariables(Questions q1, Questions q2, Questions q3, Questions q4,
                         Questions q5, Questions q6){
        this.id = -1;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.numberAnswered = 0;
        this.score = 0;
    }

    //initializes each variables
    public QuizVariables() {
        this.id = -1;
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
        this.numberAnswered = 0;
        this.date = null;
        this.score = 0;
    }



    public QuizVariables(Integer score, String date){
        this.id = -1;
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
        this.numberAnswered = 0;
        this.date = date;
        this.score = score;
    }

    //getters for the quiz variables
    public long getId() {
        return id;
    }
    public String getQ1() {
        return q1.toString();
    }
    public String getQ2() {
        return q2.toString();
    }
    public String getQ3() {
        return q3.toString();
    }
    public String getQ4() {
        return q4.toString();
    }
    public String getQ5() {
        return q5.toString();
    }
    public String getQ6() {
        return q6.toString();
    }
    public Integer getNumberAnswered() {
        return numberAnswered;
    }
    public Integer getScore() {
        return score;
    }
    public String getDate() {
        return date;
    }

    //setters for the quiz variables
    public void setId(long id) {
        this.id = id;
    }
    public void setQ1(Questions q) {
        this.q1 = q;
    }
    public void setQ2(Questions q) {
        this.q2 = q;
    }
    public void setQ3(Questions q) {
        this.q3 = q;
    }
    public void setQ4(Questions q) {
        this.q4 = q;
    }
    public void setQ5(Questions q) {
        this.q5 = q;
    }
    public void setQ6(Questions q) {
        this.q6 = q;
    }
    public void setNumberAnswered(Integer numberAnswered) {
        this.numberAnswered = numberAnswered;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //keeps track of how many questions have been answered
    public void incrementNumberAnswered() {
        this.numberAnswered += 1;
    }

    //increases the score by 1
    public void incrementScore() {
        this.score += 1;
    }

    //returns the id, score, and date as a string
    public String toString()
    {
        return id + ": " + score + " " + date;
    }
}

