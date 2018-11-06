package to.adian.unofficialenterkomputer.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey
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

    public String getEndpoint() { return endpoint;  }

    @Override
    public String toString() {
        return name;
    }
}
