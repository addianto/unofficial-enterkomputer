package to.adian.unofficialenterkomputer.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY name")
    LiveData<List<Category>> getCategories();

    @Query("SELECT * FROM categories WHERE id = :id")
    LiveData<Category> getCategory(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categories);
}
