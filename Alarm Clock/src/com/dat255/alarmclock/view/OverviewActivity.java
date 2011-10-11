package com.dat255.alarmclock.view;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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

	private List<IGroup> groups;

	/**
	 * Set up the options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.overviewmenu, menu);

		return true;
	}

	/**
	 * Set up a long-press context menu
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.deletemenu, menu);
	}

	@Override
	public void onStart() {
		super.onStart();

		fillListView();

		// Register the context menu
		registerForContextMenu(getListView());

		// Set onItemClickListener()
		final Context context = this;

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

	private void fillListView() {
		// Display all groups in the list view
		groups = GroupManager.getInstance().getGroups();

		String[] names = new String[groups.size()];

		for (int i = 0; i < groups.size(); i++) {
			names[i] = groups.get(i).getName();
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.overviewscreen_list_item, names));
	}

	/**
	 * Handle options menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;

		switch (item.getItemId()) {
		case R.id.createalarm:
			intent = new Intent(this, AlarmActivity.class);

			intent.putExtra("alarmedit", false);

			startActivity(intent);

			return true;
		case R.id.creategroup:

			// TODO Should ask the user for a name and then create the group

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Handle long-press context menu
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.delete:

			// Delete the selected group
			IGroup group = groups.get((int) info.id);

			GroupManager.getInstance().removeGroup(group);

			fillListView();

			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
