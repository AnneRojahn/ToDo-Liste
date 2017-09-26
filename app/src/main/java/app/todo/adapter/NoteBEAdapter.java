package app.todo.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.todo.dao.NoteDAO;
import app.todo.model.NoteBE;
import todo.todo_liste.R;

/**
 * Created by 1234 on 25.09.2017.
 */

public class NoteBEAdapter extends BaseAdapter implements ListAdapter {
    private List<NoteBE> list = new ArrayList<NoteBE>();
    private Context context;

    public Context getContext() {
        return context;
    }

    public NoteBEAdapter(Context context,List<NoteBE> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_notes, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)row.findViewById(R.id.list_item_noteList_textview);
        listItemText.setText((CharSequence) list.get(position).getTitle());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)row.findViewById(R.id.delete_item);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                NoteBE noteToDelete = list.get(position);
                NoteDAO dao=new NoteDAO(getContext());
                dao.delete(noteToDelete);
                notifyDataSetChanged();
            }
        });

        return row;
    }
}