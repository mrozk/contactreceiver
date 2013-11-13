package com.example.progs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	final String LOG_TAG = "myLogs";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ArrayList<PhoneElement> phoneElementsList = getNumberObj();
		String str = "";
		for( PhoneElement pe:phoneElementsList)
		{
			str += pe.toString();
		}
		saveToFile(str);
		Toast toast = Toast.makeText(getApplicationContext(), 
				"готово",
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	//Try
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	public ArrayList<PhoneElement> getNumberObj(){
		
		ArrayList<PhoneElement> phoneElementsList = new ArrayList<PhoneElement>();
		Context ctx = getApplicationContext();
		
		Cursor cursor=ctx.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
		
		if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
            	final String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            	String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            	PhoneElement pe = new PhoneElement(id, name);
            	
            	if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
            	  // Достаем email
          		  Cursor emailCur = ctx.getContentResolver().query( 
          		  ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
                  null,
                  ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
                  new String[]{id}, null); 
          		  
          		  ArrayList<String> emailArray = new ArrayList<String>(); 
          		 
          		  while (emailCur.moveToNext()) 
                  { 
                       String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));                            
                       emailArray.add( email );
                  }
          		  pe.setEmail(emailArray);
          		  
                  // Достаем email
                  
               // Достаем контакты
         		 Cursor pCur=ctx.getContentResolver().query(
         				 ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
         				 null,
         				 ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
         				 new String[]{id},
         				 null);
         		 
         		ArrayList<String> numbersArray = new ArrayList<String>();
         		
         		while(pCur.moveToNext())
                {
                      String phone=pCur.getString(
                      pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                      numbersArray.add(phone);
                 }
         		pe.setNumbers(numbersArray);
         		// Достаем контакты
         		 
         		// Достаем организацию
         		String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                String[] orgWhereParams = new String[]{id,
                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
                
         		 Cursor orgCur=ctx.getContentResolver().query(
         				 ContactsContract.Data.CONTENT_URI,
         				 null,
         				 orgWhere, 
         				 orgWhereParams,
         				 null);
         		 
         		ArrayList<String> orgsArray = new ArrayList<String>();
         		
         		 while(orgCur.moveToNext())
                  {
         			String orgName = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
                    String title = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
                    orgsArray.add(title + "-" + orgName);
                  }
         		pe.setOrganization(orgsArray);
         		// Достаем организацию
                }
            	phoneElementsList.add(pe);
            }
        }
		return phoneElementsList;
	}
	
	public void saveToFile( String strToSave )
	{
		File myF = new File(Environment.getExternalStorageDirectory()+"/data/","file.txt");
		
		try
	    {
			if(!myF.exists())
			{
				myF.createNewFile();
			}
	        try 
	        {
	            FileWriter fWr = new FileWriter(myF);                   
	            fWr.write( strToSave );
	            fWr.flush();
	            fWr.close();      
	         }
	         catch (Throwable t) 
	         {
	            //Toast.makeText(getApplicationContext(), "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
	         }

	    }
	    catch (Exception e1) 
	    {
	        //Toast.makeText(getApplicationContext(), "Exception: " + e1.toString(), Toast.LENGTH_LONG).show();
	    }
	}

}
