package to.adian.unofficialenterkomputer.worker;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.data.Category;

public class SeedDatabaseWorker extends Worker {

    private static final String TAG = SeedDatabaseWorker.class.getName();

    public SeedDatabaseWorker(@NonNull Context context,
                              @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Result result;

        try {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.categoryDao().insertAll(getSeedCategories());
            result = Result.SUCCESS;
            Log.d(TAG, "Database was seeded successfully");
        } catch (Exception ex) {
            Log.e(TAG, "Error seeding database", ex);
            result = Result.FAILURE;
        }

        return result;
    }

    private static final List<Category> getSeedCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category(1, "Accessories"));
        categories.add(new Category(2, "All-in-One"));
        categories.add(new Category(3, "Casing"));
        categories.add(new Category(4, "Cooler Fan"));
        categories.add(new Category(5, "Drawing"));

        return categories;
    }
}
