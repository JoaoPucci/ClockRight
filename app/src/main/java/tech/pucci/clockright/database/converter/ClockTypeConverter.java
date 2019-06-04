package tech.pucci.clockright.database.converter;

import androidx.room.TypeConverter;

import tech.pucci.clockright.enums.ClockType;

public class ClockTypeConverter {

    @TypeConverter
    public String toString(ClockType clockType){
        return clockType.name();
    }

    @TypeConverter
    public ClockType toClockType(String clockTypeString) {
        ClockType type = null;

        switch (clockTypeString){
            case "IN": type = ClockType.IN;
            break;
            case "OUT": type = ClockType.OUT;
            break;
        }

        return type;
    }
}
