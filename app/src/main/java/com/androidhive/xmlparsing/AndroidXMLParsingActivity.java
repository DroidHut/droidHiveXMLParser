package com.androidhive.xmlparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidXMLParsingActivity extends ListActivity {

	// All static variables
	static final String URL = "http://www.indiatvnews.com/rssfeed/";
	// XML node keys
	static final String KEY_ITEM= "item"; // parent node
	static final String KEY_TITLE = "title";
	static final String KEY_DESCRIPTION = "description";
	static final String KEY_LINK = "link";
	static final String KEY_PUBDATE = "pubDate";
   private static TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
	
		Typeface typeface=Typeface.createFromAsset(getAssets(),"font/kruti.ttf");
		TextView text1=(TextView)findViewById(R.id.name);
		TextView text2=(TextView)findViewById(R.id.cost);
		TextView text3=(TextView)findViewById(R.id.desciption);
        text1.setTypeface(typeface);
		text2.setTypeface(typeface);
		text3.setTypeface(typeface);
		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
 		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_ITEM);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_DESCRIPTION, parser.getValue(e, KEY_DESCRIPTION));
			map.put(KEY_LINK,parser.getValue(e, KEY_LINK));
			map.put(KEY_PUBDATE, parser.getValue(e, KEY_PUBDATE));

			// adding HashList to ArrayList
			menuItems.add(map);
		}

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.list_item,
				new String[] { KEY_DESCRIPTION, KEY_PUBDATE, KEY_LINK }, new int[] {
						R.id.name, R.id.desciption, R.id.cost });
		
		
		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				/*String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(KEY_DESCRIPTION, name);
				in.putExtra(KEY_LINK, cost);
				in.putExtra(KEY_PUBDATE, description);
				startActivity(in);*/

			}
		});
	}
	
}