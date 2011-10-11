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
import android.widget.Toast;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;

public class GroupActivity extends ListActivity {

	private IGroup group;

	private List<IAlarm> alarms;

	@Override
	public void onStart() {
		super.onStart();

		fillListActivity();

		// Register the context menu
		registerForContextMenu(getListView());

		// Set onItemClickListener()
		final Context context = this;

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				// Show which alarm the user clicked on
				Toast.makeText(context, "Clicked on alarm " + alarms.get((int) id).getId(), Toast.LENGTH_SHORT).show();

			}
		});
	}

	private void fillListActivity() {
		// Get the desired group
		long groupId = getIntent().getLongExtra("groupid", 0);

		group = GroupManager.getInstance().findGroupById(groupId);

		// Set the activity title
		setTitle(group.getName());

		// Display all alarms in the list view
		alarms = group.getAlarms();

		String[] names = new String[alarms.size()];

		for (int i = 0; i < alarms.size(); i++) {
			names[i] = "Alarm " + alarms.get(i).getId();
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.groupscreen_list_item, names));
	}

	/**
	 * Set up the options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.groupmenu, menu);

		return true;
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
		default:
			return super.onOptionsItemSelected(item);
		}
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

	/**
	 * Handle long-press context menu
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.delete:

			// Delete the selected alarm
			IAlarm alarm = alarms.get((int) info.id);

			group.removeAlarmFromGroup(alarm);

			AlarmManager.getInstance().removeAlarm(alarm);

			fillListActivity();

			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
