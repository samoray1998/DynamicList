package com.example.dynamiclist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {
    List<Client> MyData;
    Context ctx;
    LayoutInflater inflater;

    public ClientAdapter(@NonNull Context context, int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);
        this.MyData=objects;
        this.ctx=context;
    }


    @Override
    public int getCount() {
        return MyData.size();
    }

    @Nullable
    @Override
    public Client getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView id,name,age;
        Client c=getItem(position);
        if (convertView==null){
            inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.my_element,parent,false);

        }
        id=(TextView)convertView.findViewById(R.id.IDcTxt);
        name=(TextView)convertView.findViewById(R.id.NomTxt);
        age=(TextView)convertView.findViewById(R.id.AGETxt);
        id.setText(String.valueOf(c.getIDc()));
        name.setText(c.getName());
        age.setText(String.valueOf(c.getAge()));


        return convertView;
    }
}
