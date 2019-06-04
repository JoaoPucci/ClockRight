package tech.pucci.clockright.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import tech.pucci.clockright.R;
import tech.pucci.clockright.database.converter.ClockTypeConverter;
import tech.pucci.clockright.database.converter.LocalDateTimeConverter;
import tech.pucci.clockright.database.dao.ClockDao;
import tech.pucci.clockright.models.Clock;

@Database(entities = {Clock.class}, version = 1, exportSchema = false)
@TypeConverters({LocalDateTimeConverter.class, ClockTypeConverter.class})
public abstract class ClockrightDatabase extends RoomDatabase {

    private static ClockrightDatabase instance = null;

    public abstract ClockDao getClockDao();

    public static ClockrightDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, ClockrightDatabase.class, context.getResources().getString(R.string.database_name))
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
