package test;

import java.util.Timer;
import java.util.TimerTask;


public class Time {
    private static int seconds;
    private static int minutes;
    private int tempSec;
    private int tempMin;
    private boolean playing = false;

    public Time() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getPlaying()) {
                    setSeconds(getSeconds() + 1);
                    if(getSeconds() >= 60) {
                        setSeconds(0);
                        setMinutes(getMinutes() + 1);
                    }
                }
            }
        }, 0, 1000);
    }

    public void resetGame(){
        setSeconds(0);
        setMinutes(0);
        setPlaying(false);
    }

    public void setTempSec(int seconds){
        this.tempSec = seconds;
    }

    public static int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        Time.seconds = seconds;
    }

    private boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public static int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        Time.minutes = minutes;
    }

    public void setTempMin(int tempMinutes) {
        this.tempMin = tempMinutes;
    }

    public int getTempSec() {
        return tempSec;
    }

    public int getTempMin() {
        return tempMin;
    }
}