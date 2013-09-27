package com.example.pf;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pf.R;
import com.example.pf.SQLiteOpener;


public class DataInsert extends Activity
{
    
            EditText editTextDescription,editTextAmount,editTextCategory;
            Button btnCreateData;
            @Override
            protected void onCreate(Bundle savedInstanceState) 
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.datainsert);
                    
                    editTextDescription=(EditText)findViewById(R.id.editText1);
                    editTextAmount=(EditText)findViewById(R.id.editText2);
                    editTextCategory=(EditText)findViewById(R.id.editText3);
                    
                    btnCreateData=(Button)findViewById(R.id.button1);
                     
                    btnCreateData.setOnClickListener(new View.OnClickListener() {
                        
                        public void onClick(View v) {
                                                     
                            String description=editTextDescription.getText().toString();
                            String amount=editTextAmount.getText().toString();
                            String category=editTextCategory.getText().toString();
                            
             
                				ContentValues cv = new ContentValues();
                				cv.put("description",description);
                				cv.put("amount",amount);
                				cv.put("category",category);
                				SQLiteDatabase database = new SQLiteOpener(getApplicationContext()).getWritableDatabase();
                				
                				
                
                				long a = database.insert("createData",null, cv);
                				System.out.println("insert : "+a);
                				
                				
                				
                				Cursor c = database.rawQuery("select * from createData", null);
                				//c.moveToFirst();
                				
                				//while(!c.isAfterLast())
                				//{
                				//	System.out.println("Data : "+c.getString(0));
                				//	adapter.add(c.getString(0));
                					//c.moveToNext();
                				//}
                				
                			//	lv.setAdapter(adapter);
                				
                				//c.close();
                				database.close();
                				
                				 Intent intent=new Intent(getApplicationContext(),SpinnerActivity.class);
                                 startActivity(intent); 
                                 finish();
               				
                				 //Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                			}
                        
                        
                        });
                   
 				
                     
            }
              
            
//           @Override
//                public boolean onCreateOptionsMenu(Menu menu)
//                {
//            	 getMenuInflater().inflate(R.menu.main,menu);
//                   return true;
//              }
                    
            }    
                


