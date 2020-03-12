package com.example.dynamiclist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MySQLLightOpenHelper helper;
    private EditText txtname,txtage;
    private MyViewModel data;
    ListView listeV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Manage Client");
        helper=new MySQLLightOpenHelper(this,"ClientDb",null,1);
        data=new MyViewModel(helper);
         listeV=(ListView)findViewById(R.id.myList);
        refrshe();
        registerForContextMenu(listeV);
    }

    public void Add_Client(View view) {
        txtname=(EditText) findViewById(R.id.Nametxt);
        txtage=(EditText)findViewById(R.id.Agetxt);
        Client c=new Client(txtname.getText().toString(),Integer.valueOf(txtage.getText().toString()));
        data.AddToDB(c);
        refrshe();
    }
    private void refrshe(){

        ClientAdapter ada=new ClientAdapter(this,R.layout.activity_main,data.ListClient());
        listeV.setAdapter(ada);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        TextView myid=(TextView)info.targetView.findViewById(R.id.IDcTxt);
        int id=Integer.valueOf(myid.getText().toString());
        switch (item.getItemId()){
            case R.id.DeleteItem:
                data.deletFList(id);
                refrshe();
                break;
            case R.id.UpdateItem:
                try {
                    Client cc=new Client(txtname.getText().toString(),Integer.valueOf(txtage.getText().toString()));
                    cc.setIDc(id);
                    data.UpdateListe(id,cc);
                    refrshe();
                }
                catch (Exception ex){

                    Log.d("myexp",ex.getMessage()+"this is the exception message");
                }

                break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
