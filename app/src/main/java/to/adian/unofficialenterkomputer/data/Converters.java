package to.adian.unofficialenterkomputer.data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.room.TypeConverter;

/**
 * Provides converter functions to allow Room to reference complex
 * data types.
 */
public class Converters {

    private static final String REGEX = "^(\\w+)://([^/]+)/(.+)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @TypeConverter
    public URL stringToURL(String string) {
        URL result = null;
        Matcher matcher = PATTERN.matcher(string);

        if (matcher.matches()) {
            String protocol = matcher.group(1);
            String host = matcher.group(2);
            String file = "/" + matcher.group(3);

            try {
                result = new URL(protocol, host, file);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @TypeConverter
    public String urlToString(URL url) {
        return url.toString();
    }
}
