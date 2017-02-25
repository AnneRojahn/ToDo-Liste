package todo.todo_liste;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        SharedPreferences savedToDo = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES_KEY, 0);
        String EntryToDisplay = "";
        for(int counter = 0; counter < 1; counter ++)
        {
            String key = MainActivity.TODO1;
            EntryToDisplay = EntryToDisplay + savedToDo.getString(MainActivity.TODO1, null);
        }
      //  Intent intent = getIntent();

        TextView textView = (TextView)findViewById(R.id.text_display_all);
//        textView.setTextSize(40);
        textView.setText(EntryToDisplay);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_all);
        //layout.addView(textView);
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
