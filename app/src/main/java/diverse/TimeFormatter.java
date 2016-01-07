package diverse;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Simon on 07/01/16.
 */
public class TimeFormatter {

    private static SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");

    //Metode der tager antal sekunder og retunere String med tidspunkt.
    public static String getCountdown(long seconds) {
        String retur="";
        //Timer
        retur += String.valueOf(seconds/3600)+":";
        seconds = seconds%3600;
        //Minutter
        retur += String.valueOf(seconds/60)+":";
        seconds = seconds%60;
        retur += seconds;
        return retur;
    }

    public static String getTidString(long sec) {
        long seconds = (sec - new Date().getTime())/1000;
        String retur="";
        retur += String.valueOf(seconds/3600)+":";
        seconds = seconds%3600;
        retur += String.valueOf(seconds/60);
        return retur;
    }
}
