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
        Log.d(TAG, "Seeding database");
        Result result;

        try {
            List<Category> categories = getCategoriesFromJson(getApplicationContext(),
                    "categories.json");

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.categoryDao().insertAll(categories);
            result = Result.SUCCESS;
            Log.d(TAG, "Database was seeded successfully");
        } catch (Exception ex) {
            Log.e(TAG, "Error seeding database", ex);
            result = Result.FAILURE;
        }

        return result;
    }

    private static List<Category> getCategoriesFromJson(Context appContext,
                                                        String assetFileName) {
        List<Category> categories = Collections.emptyList();
        Type categoryType = new TypeToken<List<Category>>() {
        }.getType();

        // GSON

        try {
            InputStream inputStream = appContext.getAssets().open(assetFileName,
                   AssetManager.ACCESS_BUFFER);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
            categories = new Gson().fromJson(jsonReader, categoryType);
            jsonReader.close();
        } catch (IOException ex) {
            Log.e(TAG, String.format("Cannot read JSON file '{}' in the assets directory", assetFileName));
        }

        Log.d(TAG, String.format("Read {} categories from JSON file",
                categories.size()));

        return categories;
    }
}
