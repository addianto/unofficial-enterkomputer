package to.adian.unofficialenterkomputer.model;

import java.net.URL;
import java.util.Locale;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey
    private final int id;
    private final String name;
    private final URL endpoint;

    public Category(int id, String name, URL endpoint) {
        this.id = id;
        this.name = name;
        this.endpoint = endpoint;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public URL getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Category(id=%d, name=%s, endpoint=%s)", id, name,
                endpoint.toString());
    }
}
