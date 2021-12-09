package Controller;

import java.util.Timer;
import java.util.TimerTask;


/**
 *  Use to record time taken
 */
public class Time {
    private static int seconds;
    private static int minutes;
    private int tempSec;
    private int tempMin;
    private boolean playing = false;

    /**
     * initialize a timer to record the time
     */
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

    /**
     * reset the timer
     */
    public void resetGame(){
        setSeconds(0);
        setMinutes(0);
        setPlaying(false);
    }

    /**
     * set temporary second
     * @param seconds seconds
     */
    public void setTempSec(int seconds){
        this.tempSec = seconds;
    }

    /**
     * get seconds
     * @return seconds
     */
    public static int getSeconds() {
        return seconds;
    }

    /**
     * set seconds
     * @param seconds seconds
     */
    public void setSeconds(int seconds) {
        Time.seconds = seconds;
    }

    /**
     * check if the timer is playing
     * @return boolean
     */
    private boolean getPlaying() {
        return playing;
    }

    /**
     * set the timer play or not
     * @param playing boolean
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * get minutes
     * @return minutes
     */
    public static int getMinutes() {
        return minutes;
    }

    /**
     * set minutes
     * @param minutes minutes
     */
    public void setMinutes(int minutes) {
        Time.minutes = minutes;
    }

    /**
     * set temporary minutes
     * @param tempMinutes tempMin
     */
    public void setTempMin(int tempMinutes) {
        this.tempMin = tempMinutes;
    }

    /**
     * get temporary seconds
     * @return tempSec
     */
    public int getTempSec() {
        return tempSec;
    }

    /**
     * get temporary minutes
     * @return tempMin
     */
    public int getTempMin() {
        return tempMin;
    }
}