package edu.uga.cs.capitalsquiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class defines the various parts of a quiz question.
 */

public class Questions implements Parcelable {
    private long   id;
    private String state;
    private String capital;
    private String city1;
    private String city2;

    public Questions()
    {
        this.id = -1;
        this.state = null;
        this.capital = null;
        this.city1 = null;
        this.city2 = null;

    }

    public Questions( String state, String capital, String city1, String city2) {
        this.id = -1;
        this.state = state;
        this.capital = capital;
        this.city1 = city1;
        this.city2 = city2;
    }

    protected Questions(Parcel in) {
        id = in.readLong();
        state = in.readString();
        capital = in.readString();
        city1 = in.readString();
        city2 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(state);
        dest.writeString(capital);
        dest.writeString(city1);
        dest.writeString(city2);
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    //getters for the quiz variables
    public long getId() {
        return id;
    }
    public String getState() {
        return state;
    }
    public String getCapital() {
        return capital;
    }
    public String getCity1()
    {
        return city1;
    }
    public String getCity2()
    {
        return city2;
    }

    //setters for the quiz variables
    public void setId(long id) {
        this.id = id;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public void setCity1(String city1)
    {
        this.city1 = city1;
    }
    public void setCity2(String city2)
    {
        this.city2 = city2;
    }

    //returns the quiz question information as a string
    public String toString()
    {
        return id + ": " + state + " " + capital + " " + city1 + " " + city2;
    }

    //checks to see if the answer chosen was correct or not
    public boolean gradeQuestion(String answer) {
        if(capital.equals(answer)) {
            return true;
        }
        else return false;
    }
}
