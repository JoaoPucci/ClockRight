package tech.pucci.clockright.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tech.pucci.clockright.models.Clock;

@Dao
public interface ClockDao {

    @Insert
    void save(Clock clock);

    @Query("SELECT * FROM clock")
    List<Clock> getAll();

}
