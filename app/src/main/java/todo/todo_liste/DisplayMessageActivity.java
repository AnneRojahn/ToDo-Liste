package todo.todo_liste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        SharedPreferences savedToDo = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES_KEY, 0);
        String key = MainActivity.TODO1;
        String EntryToDisplay = savedToDo.getString(MainActivity.TODO1, null);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(EntryToDisplay + " wurde gespeichert");
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //The activity has become visible (it is now "resumed")
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        //The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //The activity is about to be destroyed.
    }
}
