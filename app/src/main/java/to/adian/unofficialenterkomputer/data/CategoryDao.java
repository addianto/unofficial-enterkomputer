package to.adian.unofficialenterkomputer.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import to.adian.unofficialenterkomputer.model.Category;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY name")
    LiveData<List<Category>> getCategories();

    @Query("SELECT * FROM categories WHERE id = :id")
    LiveData<Category> getCategory(int id);

    @Query("SELECT * FROM CATEGORIES WHERE name = :name")
    LiveData<Category> getCategoryByName(String name);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Category> categories);
}
