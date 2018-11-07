package to.adian.unofficialenterkomputer.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);
}
