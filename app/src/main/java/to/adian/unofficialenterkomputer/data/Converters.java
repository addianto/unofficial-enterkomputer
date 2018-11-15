package to.adian.unofficialenterkomputer.data;

import java.net.URL;

import androidx.room.TypeConverter;

/**
 * Provides converter functions to allow Room to reference complex
 * data types.
 */
public class Converters {

    @TypeConverter
    public URL stringToURL(String string) {
        return null;
    }

    @TypeConverter
    public String urlToString(URL url) {
        return null;
    }
}
