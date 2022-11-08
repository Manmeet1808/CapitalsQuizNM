package edu.uga.cs.capitalsquiz;

public class QuizVariables {
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

    public QuizVariables(ReviewHistory reviewHistory) {
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getQ1() { return q1.toString(); }

    public void setQ1(Questions q) { this.q1 = q; }

    public String getQ2() { return q2.toString(); }

    public void setQ2(Questions q) { this.q2 = q; }

    public String getQ3() { return q3.toString(); }

    public void setQ3(Questions q) { this.q3 = q; }

    public String getQ4() { return q4.toString(); }

    public void setQ4(Questions q) { this.q4 = q; }

    public String getQ5() { return q5.toString(); }

    public void setQ5(Questions q) { this.q5 = q; }

    public String getQ6() { return q6.toString(); }

    public void setQ6(Questions q) { this.q6 = q; }

    public Integer getNumberAnswered() { return numberAnswered; }

    public void setNumberAnswered(Integer numberAnswered) { this.numberAnswered = numberAnswered; }

    public void incrementNumberAnswered() { this.numberAnswered += 1; }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score) { this.score = score; }

    public void incrementScore() { this.score += 1; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String toString()
    {
        return id + ": " + score + " " + date;
    }
}

