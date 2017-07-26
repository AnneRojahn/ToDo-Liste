package app.todo.businesslogic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import app.todo.model.NoteBE;

import static todo.todo_liste.R.id.delete;

/**
 * Created by 1234 on 25.07.2017.
 */

public class NoteBEListAdapter extends ArrayAdapter<NoteBE> {

    private List<NoteBE> items;
    private int layoutResourceId;
    private Context context;

    public NoteBEListAdapter(Context context, int layoutResourceId, List<NoteBE> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NoteBEHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new NoteBEHolder();
        holder.noteBE = items.get(position);
        holder.removePaymentButton = (Button)row.findViewById(delete);
        holder.removePaymentButton.setTag(holder.noteBE);

        holder.name = (TextView)row.findViewById(list_item_noteList_textview);
        //holder.value = (TextView)row.findViewById(R.id.atomPay_value);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(NoteBEHolder holder) {
        holder.name.setText(holder.noteBE.getTitle());
    //    holder.value.setText(String.valueOf(holder.noteBE.getValue()));
    }

    public static class NoteBEHolder {
        NoteBE noteBE;
        TextView name;
       // TextView value;
        Button removePaymentButton;
    }
}