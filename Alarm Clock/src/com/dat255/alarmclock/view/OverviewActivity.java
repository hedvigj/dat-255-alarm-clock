package com.dat255.alarmclock.view;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;

public class OverviewActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * setContentView(R.layout.overviewscreen);
		 * 
		 * button = (Button) findViewById(R.id.homeButton);
		 * Tools.createOnClickLauncher(button, OverviewActivity.this,
		 * HomeActivity.class);
		 * 
		 * button = (Button) findViewById(R.id.alarmButton);
		 * Tools.createOnClickLauncher(button, OverviewActivity.this,
		 * AlarmActivity.class);
		 * 
		 * button = (Button) findViewById(R.id.groupButton);
		 * Tools.createOnClickLauncher(button, OverviewActivity.this,
		 * GroupActivity.class);
		 */
	}

	// Shows a menu when menubutton is pushed
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.overviewmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.createalarm:
			i = new Intent(this, AlarmActivity.class);
			startActivity(i);
			return true;
		case R.id.creategroup:
			i = new Intent(this, GroupActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Shows a menu when an group/alarm in the list is pushed for a longer time
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.deletemenu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.delete:
			// TODO Implement delete
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		final Context context = this;

		// List all groups
		final List<IGroup> groups = GroupManager.getInstance().getGroups();

		String[] names = new String[groups.size()];

		for (int i = 0; i < groups.size(); i++) {
			names[i] = groups.get(i).getName();
		}

		setListAdapter(new ArrayAdapter<String>(context, R.layout.overviewscreen_list_item, names));

		// Register the context menu
		registerForContextMenu(getListView());

		// Set onItemClickListener()
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long index) {

				// Open the group activity
				Intent intent = new Intent(context, GroupActivity.class);

				intent.putExtra("groupid", groups.get((int) index).getId());

				context.startActivity(intent);
			}
		});
	}
}
