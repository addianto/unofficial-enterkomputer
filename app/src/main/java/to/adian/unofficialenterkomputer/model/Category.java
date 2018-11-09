package to.adian.unofficialenterkomputer.model;

import java.util.Locale;

public class Category {

    private final int id;
    private final String name;
    private final String endpoint;

    public Category(int id, String name, String endpoint) {
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

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Category(id=%d, name=%s, endpoint=%s)", id, name, endpoint);
    }
}
