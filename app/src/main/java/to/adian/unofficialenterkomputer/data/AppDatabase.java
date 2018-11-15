package to.adian.unofficialenterkomputer.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import to.adian.unofficialenterkomputer.model.Category;
import to.adian.unofficialenterkomputer.model.Product;
import to.adian.unofficialenterkomputer.worker.SeedDatabaseWorker;

@Database(entities = {Category.class, Product.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String CATEGORIES_SEED_FILE = "categories.json";

    private static final String TAG = AppDatabase.class.getName();
    private static final String WORK_TAG = "INIT";
    private static final String DB_NAME = "unofficial-ek.db";
    private static AppDatabase instance;

    public abstract CategoryDao categoryDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "Building the database for the first time");
            instance = buildDatabase(context);
        }

        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .addCallback(new CreateDBCallback())
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    private static class CreateDBCallback extends Callback {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            OneTimeWorkRequest request =
                    new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class)
                            .addTag(WORK_TAG)
                            .build();
            WorkManager.getInstance().enqueue(request);
        }
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `products` (`name` TEXT," +
                    "`id` INTEGER NOT NULL PRIMARY KEY," +
                    "`category` TEXT," +
                    "`price` INTEGER NOT NULL)");
        }
    };
}
