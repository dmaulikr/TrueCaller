package com.example.truecaller;

import java.util.ArrayList;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.turecaller.db.dbSource;

public class MainActivity extends ListActivity implements OnItemClickListener {

	dbSource source ;
	ArrayList<ContactInfo> contacts;
	private static final String LOGTAG = "TAG";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// To Deal with DataBase
		source =new dbSource(MainActivity.this);
		source.open();
		contacts = new ArrayList<ContactInfo>();
		contacts=getContacts();
		// insert contacts in database
		for (int i = 0; i < contacts.size(); i++) {
			ContactInfo c = null;
			c=source.create(contacts.get(i));
			Log.i(LOGTAG, "contact with id "+c.getId()+" was added to database");
		}
		MyAdapter adpt = new MyAdapter(contacts, this);
		setListAdapter(adpt);
	}

	
	private ArrayList<ContactInfo> getContacts() {
		ArrayList<ContactInfo> cont = new ArrayList<ContactInfo>();
		 Cursor people = getContentResolver().query(Contacts.CONTENT_URI, null, null, null, null);
		 String name ,id,number = "";		
		 if(people.getCount()>0){
		 while(people.moveToNext()){
			 name =people.getString(people.getColumnIndex(Contacts.DISPLAY_NAME));
			 id= people.getString(people.getColumnIndex(Contacts._ID));
			 Cursor cur = getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
			 while (cur.moveToNext()) {
				 number=cur.getString(cur.getColumnIndex(Phone.NUMBER));
			}
			cont.add(new ContactInfo(name, number)); 
		 }
		 }
		 return cont ;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Toast.makeText(getApplicationContext(),contacts.get(position).getName(),Toast.LENGTH_SHORT).show();
	}
	

	
	@Override
	protected void onResume() {
	
		super.onResume();
		source.open();
	}
	
	@Override
	protected void onPause() {
	
		super.onPause();
		source.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
