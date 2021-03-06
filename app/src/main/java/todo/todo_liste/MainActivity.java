package todo.todo_liste;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import app.todo.adapter.NoteBEAdapter;
import app.todo.dao.NoteDAO;
import app.todo.model.NoteBE;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String SHARED_PREFERENCES_KEY = "anne.toDoApp.toDoMessage";
    public final static String TODO1 = "key";
    protected static final int DIALOG_REMOVE_All = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     * Tobias was here
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SharedPreferences settings = getPreferences(0);
        //String savedData = settings.getString("testtest", "...");

        //The activity is about to become visible
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        NoteDAO dao=new NoteDAO( getApplicationContext());

        //generate list
        List<NoteBE> noteList = dao.loadAll();
        List<String> todoTitleList = new ArrayList<>(noteList.size());
        for(NoteBE note : noteList) {
            if(note.getTitle() != null) {
                todoTitleList.add(note.getTitle());
            } else{
                todoTitleList.add("No Title");
            }
        }

        //instantiate custom adapter
        NoteBEAdapter adapter = new NoteBEAdapter(this, noteList);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listview_Notes);
        lView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
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
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());

        //We need an Editor object to make preference changes.
        //All objects are from android.context.Context
        //  SharedPreferences settings =getPreferences(0);
        //SharedPreferences.Editor editor = settings.edit();
        //editor.putString("testtest", "...");
        //The activity is no longer visible (it is now "stopped")
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //The activity is about to be destroyed.
    }

    /**
     * Called when user clicks the Send button
     */
    public void saveToDo(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        NoteBE newNote = new NoteBE(message);
        NoteDAO dao = new NoteDAO(getApplicationContext());
        dao.save(newNote);
        startActivity(intent);
    }

    public void deleteAll(final View view)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Warnung");
        alertDialog.setMessage("Sollen alle Notizen gelöscht werden?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NoteDAO dao = new NoteDAO(getApplicationContext());
                        List<NoteBE> list = dao.loadAll();
                        for(NoteBE note : list)
                        {
                            dao.delete(note);
                        }
                        // TODO: 28.09.2017 Anzeige muss nach löschen direkt aktualisiert werden 
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Abbruch",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

        /*
        Intent intent = new Intent(this, MainActivity.class);
        NoteDAO dao = new NoteDAO(getApplicationContext());
        List<NoteBE> list = dao.loadAll();
        for(NoteBE note : list)
        {
            dao.delete(note);
        }
        startActivity(intent);
        */
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}