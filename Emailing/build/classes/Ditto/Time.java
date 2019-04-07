/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ditto;

import static Ditto.Convert.toInt;
import static Ditto.Ditto.Emailer;
import static Ditto.Ditto.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Owner
 */
public class Time {

    public static int getHour() {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static int getMin() {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static int getSec() {
        DateFormat dateFormat = new SimpleDateFormat("ss");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static int getDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static int getMonth() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static int getYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return toInt(dateFormat.format(date));
    }

    public static String getDate() {
        return getYear() + ":" + getMonth() + ":" + getDay();
    }

    public static String getTime() {
        return getHour() + ":" + getMin() + ":" + getSec();
    }

    public static String getInstant() {
        return getDate() + ":" + getTime();
    }

    public static String toFifty(double hour, double minute, double second) {
        double answer, sectot;
        int hour2, minute2, second2;
        sectot = (hour * 3600 + minute * 60 + second) * 1.44677;
        hour2 = (int)Math.floor(sectot / 2500);
        sectot -= hour2 * 2500;
        minute2 = (int)Math.floor(sectot / 50);
        sectot -= minute2 * 50;
        second2 = (int)Math.floor(sectot);
        return "hour: " + hour2 + ", minute: " + minute2 + ", second: " + second2;

    }
    public static void Nathanzclaim(){
        if (getYear() == 2024){
            MailBox Nathan = new MailBox("Nathan O'Day","10yearshavepassed","NathanODay@gmail.com");
            Emailer(User, Nathan, "Morning Brother!  So it's been ten years now since that fateful night when you declared that you would have Morticity in production in 2024.  How's that project going? You didn't forget the Nathan Bars did you?  Remember LeTourneau?  Non-stop puns and word battles.  Confusing people.  You and your giant magnet....-_-.....XD XD XD.  Hope things are going well for you.  If we're not still in contact send me a message now, eh?  For old times sake.  Adios Roomie.  -Micah");
        }
    }
}

