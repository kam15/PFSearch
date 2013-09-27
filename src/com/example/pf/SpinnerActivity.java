package com.example.pf;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends Activity

{

	String[] day2;
	String selectedItem, search1;
	EditText et;
	Spinner spinner1, spinner2;
	Button b1;
	ArrayAdapter<String> adapter1;
	ListView l1;

	// private ArrayList<String> results = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState)

	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		final Context ctxt=getApplicationContext();
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		et = (EditText) findViewById(R.id.editText1);
		b1 = (Button) findViewById(R.id.button1);
		l1 = (ListView) findViewById(R.id.listView1);
		ArrayList<String> category = new ArrayList<String>();
		SQLiteDatabase database = new SQLiteOpener(ctxt)
				.getWritableDatabase();
		Cursor c = database.rawQuery("select category from createData", null);
		c.moveToFirst();

		while (!c.isAfterLast()) {
			System.out.println("Data : " + c.getString(0));
			category.add(c.getString(0));
			c.moveToNext();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_spinner_dropdown_item, category);
		c.close();
		database.close();
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener()

		{

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)

			{

				int index = arg0.getSelectedItemPosition();

				// storing string resources into Array
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// do nothing

			}

		});
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.day2, android.R.layout.simple_spinner_item);

		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);

		spinner2.setOnItemSelectedListener(new OnItemSelectedListener()

		{

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)

			{

				int index = arg0.getSelectedItemPosition();

				// storing string resources into Array
				day2 = getResources().getStringArray(R.array.day2);

				Toast.makeText(getBaseContext(),
						"You have selected : " + day2[index],
						Toast.LENGTH_SHORT).show();
				b1.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {

						selectedItem = spinner2.getSelectedItem().toString();
						search1 = et.getText().toString();
						Intent intent=new Intent(SpinnerActivity.this,ListData.class);
						   intent.putExtra("selecteditem",selectedItem);
						   intent.putExtra("search1", search1);
						final int result=1;
						   startActivityForResult(intent, result);
						// private ArrayList<String> results = new
						// ArrayList<String>();
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
				});

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// do nothing

			}

		});

	}

}
