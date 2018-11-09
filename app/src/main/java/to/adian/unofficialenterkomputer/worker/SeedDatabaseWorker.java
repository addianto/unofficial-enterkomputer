package to.adian.unofficialenterkomputer.worker;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import to.adian.unofficialenterkomputer.data.AppDatabase;
import to.adian.unofficialenterkomputer.model.Category;

public class SeedDatabaseWorker extends Worker {

    private static final String TAG = SeedDatabaseWorker.class.getName();

    public SeedDatabaseWorker(@NonNull Context context,
                              @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Seeding the database using worker");
        Result result;

        try {
            List<Category> categories = getCategories(getApplicationContext(),
                    AppDatabase.CATEGORIES_SEED_FILE);
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());

            db.categoryDao().insertAll(categories);
            result = Result.SUCCESS;
            Log.d(TAG, "Worker has successfully seeded the database");
        } catch (Exception ex) {
            Log.e(TAG, "Failure during seeding the database", ex);
            result = Result.FAILURE;
        }

        return result;
    }

    private static List<Category> getCategories(Context context,
                                                String fileName) {
        List<Category> categories = Collections.emptyList();
        Type listCategoryType = new TypeToken<List<Category>>() {
        }.getType();

        try {
            InputStream input = context.getAssets().open(fileName,
                    AssetManager.ACCESS_BUFFER);
            JsonReader reader = new JsonReader(new InputStreamReader(input));
            categories = new Gson().fromJson(reader, listCategoryType);
            Log.d(TAG, String.format(Locale.ENGLISH,
                    "Loaded %d categories from seed file", categories.size()));
            reader.close();
        } catch (IOException ex) {
            Log.e(TAG, "Cannot read the following JSON seed file: " + fileName,
                    ex);
        }

        return categories;
    }
}
