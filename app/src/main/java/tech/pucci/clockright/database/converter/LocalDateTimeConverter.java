package tech.pucci.clockright.database.converter;

import androidx.room.TypeConverter;

import org.joda.time.LocalDateTime;

public class LocalDateTimeConverter {

    @TypeConverter
    public String toString(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @TypeConverter
    public LocalDateTime toLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString);
    }
}
