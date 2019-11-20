package com.example.tringuyen_sydneyaraujo_comp304_lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listitem, getResources().getStringArray(R.array.cuisine));

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch( i )
                {
                    case 0:  Intent newActivity = new Intent(MainActivity.this, ItalianActivity.class);
                        startActivity(newActivity);
                        break;
                    case 1:  Intent newActivity1 = new Intent(MainActivity.this, ChineseActivity.class);
                        startActivity(newActivity1);
                        break;
                    case 2:  Intent newActivity2 = new Intent(MainActivity.this, GreekActivity.class);
                        startActivity(newActivity2);
                        break;
                    case 3:  Intent newActivity3 = new Intent(MainActivity.this, IndianActivity.class);
                        startActivity(newActivity3);
                        break;
                    case 4:  Intent newActivity4 = new Intent(MainActivity.this, SpanishActivity.class);
                        startActivity(newActivity4);
                        break;
                }
            }
        });

    }

}
