package support;

public class TimeFormat {

    public static String formattedSeconds(int seconds) {
        int minutes = seconds / 60;
        int secondsRemaining = seconds % 60;
        return String.format("[%02d:%02d]", minutes, secondsRemaining);
    }

}
