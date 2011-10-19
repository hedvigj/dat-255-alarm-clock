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
import android.widget.ListView;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.AlarmManager;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;
import com.dat255.alarmclock.utilities.Tools;

public class GroupActivity extends ListActivity {

	private IGroup group;

	private List<IAlarm> alarms;

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

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));

		updateCheckBoxes();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Makes selecting multiple list items possible
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

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

				// Set the checks' status according to the alarms
				updateCheckBoxes();

				// Open the alarm activity to view this alarm
				Intent intent = new Intent(context, AlarmActivity.class);

				intent.putExtra("alarmedit", true);
				intent.putExtra("alarmid", alarms.get((int) id).getId());
				intent.putExtra("groupid", group.getId());

				context.startActivity(intent);
			}
		});
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
			// Open the alarm activity to create a new alarm
			intent = new Intent(this, AlarmActivity.class);

			intent.putExtra("alarmedit", false);
			intent.putExtra("groupid", group.getId());

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

		inflater.inflate(R.menu.group_longpress_menu, menu);

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		// See if the alarm corresponding to the selected item is enabled
		boolean enabled = alarms.get(info.position).isEnabled();

		// Set the check status according to the alarm's status
		getListView().setItemChecked(info.position, enabled);

		// Show only the option to enable the alarm if it is disabled, and vice
		// versa
		menu.findItem(R.id.enable).setVisible(!enabled);
		menu.findItem(R.id.disable).setVisible(enabled);

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
		case R.id.enable:
			alarm = alarms.get((int) info.id);

			alarm.enable();

			Tools.showAlarmCountdownToast(this, alarm);

			updateCheckBoxes();

			return true;

		case R.id.disable:
			alarm = alarms.get((int) info.id);

			alarm.disable();

			updateCheckBoxes();

			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	public void updateCheckBoxes() {

		ListView view = getListView();

		// Set all checkboxes according to the status of the alarms
		for (int i = 0; i < view.getCount(); i++) {
			view.setItemChecked(i, group.getAlarms().get(i).isEnabled());
		}

	}
}
