package com.example.pf;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_data);
		ListView l1=(ListView)findViewById(R.id.listView1);
		final Context ctxt=getApplicationContext();
		Intent i=getIntent();
		String selectedItem= i.getStringExtra("selecteditem");
		String search1= i.getStringExtra("search1");
		
		System.out.println(selectedItem);
		System.out.println(search1);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ctxt,android.R.layout.simple_list_item_1);
		l1.setAdapter(adapter1);

		SQLiteDatabase database = new SQLiteOpener(
				getApplicationContext()).getWritableDatabase();

		Cursor c = database.rawQuery(
				"select * from createData where "
						+ selectedItem + "='" + search1 + "'",
				null);
		c.moveToFirst();

		if (c != null) {
			if (c.moveToFirst()) {
				do {
					String description = c.getString(c
							.getColumnIndex("description"));
					String amount = c.getString(c
							.getColumnIndex("amount"));
					String category = c.getString(c
							.getColumnIndex("category"));
					adapter1.add("Description: " + description
							+ ",amount: " + amount
							+ ",category:" + category);
				} while (c.moveToNext());
			}
		}
		c.close();
		database.close();
		l1.setTextFilterEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_data, menu);
		return true;
	}

}
