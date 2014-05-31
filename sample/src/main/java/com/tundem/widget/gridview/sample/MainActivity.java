package com.tundem.widget.gridview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tundem.widget.gridview.AnimatedGridView;

import java.util.LinkedList;
import java.util.TreeSet;


public class MainActivity extends Activity {

    AnimatedGridView agv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agv = (AnimatedGridView) findViewById(R.id.gridview);
        agv.setAdapter(new SampleAdapter());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_remove) {
            if (agv.getCount() >= 6) {
                TreeSet<Integer> row = new TreeSet<Integer>();
                row.add(0);
                agv.animateDeleteRow(row, 200);
            }

            return true;
        } else if (id == R.id.action_add) {
            LinkedList<Object> items = new LinkedList<Object>();
            items.add(1);
            items.add(2);
            items.add(3);
            items.add(4);
            items.add(5);
            items.add(6);
            agv.animateAddCells(items, 200);
        }
        return super.onOptionsItemSelected(item);
    }
}
