package tech.pucci.clockright.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.joda.time.Minutes;
import org.joda.time.Period;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import tech.pucci.clockright.R;

public class SettingsActivity extends AppCompatActivity {

    private EditText edtWhHours;
    private EditText edtWhMinutes;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        resources = getResources();

        configureWorkingHours();
    }

    private void configureWorkingHours() {
        edtWhHours = findViewById(R.id.settings_wk_hours);
        edtWhMinutes = findViewById(R.id.settings_wk_minutes);

        SharedPreferences prefs = getSharedPreferences(resources.getString(R.string.prefs_settings), MODE_PRIVATE);
        int minutesConfigured = prefs.getInt(resources.getString(R.string.prefs_settings_working_hours), resources.getInteger(R.integer.prefs_default_working_hours));
        Minutes minutes = Minutes.minutes(minutesConfigured);

        double whHours = minutes.getMinutes() / 60.0;
        int hourPart = Double.valueOf(whHours).intValue();
        int minutePart = Double.valueOf((whHours - hourPart) * 60).intValue();

        edtWhHours.setText(String.format(Locale.getDefault(), "%02d", hourPart));
        edtWhMinutes.setText(String.format(Locale.getDefault(), "%02d", minutePart));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings_done:
                Period p = new Period().withHours(Integer.valueOf(edtWhHours.getText().toString())).withMinutes(Integer.valueOf(edtWhMinutes.getText().toString()));

                SharedPreferences sharedPrefs = getSharedPreferences(resources.getString(R.string.prefs_settings), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putInt(resources.getString(R.string.prefs_settings_working_hours), p.toStandardMinutes().getMinutes());
                editor.apply();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
