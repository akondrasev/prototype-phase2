package inc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(XConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }
}
