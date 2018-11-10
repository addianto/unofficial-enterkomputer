package to.adian.unofficialenterkomputer.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import to.adian.unofficialenterkomputer.model.Product;

import static androidx.room.OnConflictStrategy.REPLACE;

public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getProducts();

    @Query("SELECT * FROM products WHERE category = :category")
    LiveData<List<Product>> getProductsByCategory(String category);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Product> products);
}
