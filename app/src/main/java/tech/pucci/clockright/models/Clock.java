package tech.pucci.clockright.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDateTime;

import tech.pucci.clockright.enums.ClockType;

@Entity
public class Clock {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private LocalDateTime dateTime;

    @Ignore
    private ClockType type;

    public Clock(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Ignore
    @Deprecated
    public Clock(LocalDateTime dateTime, ClockType type) {
        this.dateTime = dateTime;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ClockType getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

}