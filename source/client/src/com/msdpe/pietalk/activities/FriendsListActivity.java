package com.msdpe.pietalk.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Filter.FilterListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.msdpe.pietalk.R;
import com.msdpe.pietalk.base.BaseActivity;
import com.msdpe.pietalk.util.PieTalkLogger;

public class FriendsListActivity extends BaseActivity {
	
	private final String TAG = "FriendsListActivity";
	private ListView mLvFriends;
	private ArrayAdapter<String> mAdapter;
	private LinearLayout mLayoutAddFriend;
	
//	String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
//	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
//	        "Android", "iPhone", "WindowsMobile" };
	
	String[] values = new String[] { "Aaa", "Abc", "Abd", "Bcx", "Bdy", "Cdd" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, true);
		setContentView(R.layout.activity_friends_list);
		// Show the Up button in the action bar.
		setupActionBar();
		
		mLayoutAddFriend = (LinearLayout) findViewById(R.id.layoutAddFriend);
		mLayoutAddFriend.setVisibility(View.GONE);
		
		mLvFriends = (ListView) findViewById(R.id.lvFriends);
		mLvFriends.setOverScrollMode(View.OVER_SCROLL_NEVER);
		
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    
	    mAdapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, list);
	    mLvFriends.setAdapter(mAdapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends_list, menu);
		
		// Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.menuSearch).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName()));
	    //searchView.setQueryHint("test");
	    searchView.setOnQueryTextListener(new OnQueryTextListener() {			
	    		@Override
			public boolean onQueryTextSubmit(String query) {
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(final String newText) {
				PieTalkLogger.i(TAG, "Text: " + newText);
				
				mAdapter.getFilter().filter(newText, new FilterListener() {					
					@Override
					public void onFilterComplete(int count) {
						if (mAdapter.getCount() > 0) 
							mLvFriends.setVisibility(View.VISIBLE);
						else
							mLvFriends.setVisibility(View.GONE);
						
						if (!newText.equals("")) {
							mLayoutAddFriend.setVisibility(View.VISIBLE);
						} else {
							mLayoutAddFriend.setVisibility(View.GONE);
						}
					}
				});
				
				
				return true;
			}
		});		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			return true;
		case R.id.menuSearch:
			
			return true;
		case R.id.menuAddFriends:
			PieTalkLogger.i(TAG, "Need to implement adding friends");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);		
		
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            PieTalkLogger.i(TAG, "Search for: " + query);
            
        	    
        }
	}

}
