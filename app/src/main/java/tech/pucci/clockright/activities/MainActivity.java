package tech.pucci.clockright.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import tech.pucci.clockright.R;
import tech.pucci.clockright.database.ClockrightDatabase;
import tech.pucci.clockright.database.dao.ClockDao;
import tech.pucci.clockright.models.Clock;

public class MainActivity extends AppCompatActivity {

    ClockrightDatabase database;
    ClockDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = ClockrightDatabase.getInstance(this);
        dao = database.getClockDao();

        configureClockButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tvDayDifference = findViewById(R.id.main_day_difference);
        tvDayDifference.setText(getDayDifference());
    }

    private int getDayDifference() {
        return 5;
    }

    private void configureClockButton() {
        final Button btnClock = findViewById(R.id.main_clock_button);
        btnClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvLastClock = findViewById(R.id.main_last_clock);
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter formatter = ISODateTimeFormat.hourMinuteSecond();
                tvLastClock.setText(formatter.print(time));

                ClockDao clockDao = database.getClockDao();
                clockDao.save(new Clock(LocalDateTime.now()));

                Snackbar.make(btnClock, "Clock registered at " + formatter.print(time), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
