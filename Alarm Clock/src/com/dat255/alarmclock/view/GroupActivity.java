package com.dat255.alarmclock.view;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.alarm.IAlarm;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;

public class GroupActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * setContentView(R.layout.groupscreen);
		 * 
		 * Button homeButton = (Button) findViewById(R.id.homeButton);
		 * Tools.createOnClickLauncher(homeButton, GroupActivity.this,
		 * HomeActivity.class);
		 * 
		 * Button overviewButton = (Button) findViewById(R.id.overviewButton);
		 * Tools.createOnClickLauncher(overviewButton, GroupActivity.this,
		 * OverviewActivity.class);
		 * 
		 * Button alarmButton = (Button) findViewById(R.id.alarmButton);
		 * Tools.createOnClickLauncher(alarmButton, GroupActivity.this,
		 * AlarmActivity.class);
		 */
	}

	@Override
	public void onStart() {
		super.onStart();

		final Context context = this;

		// Get the desired group
		long groupId = getIntent().getLongExtra("groupid", 0);

		IGroup group = GroupManager.getInstance().findGroupById(groupId);

		// Set the activity title
		setTitle(group.getName());

		// List all alarms of this group
		final List<IAlarm> alarms = group.getAlarms();

		String[] names = new String[alarms.size()];

		for (int i = 0; i < alarms.size(); i++) {
			names[i] = "Alarm " + alarms.get(i).getId();
		}

		setListAdapter(new ArrayAdapter<String>(context, R.layout.groupscreen_list_item, names));

		// Set onItemClickListener()
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long index) {

				// Show which alarm the user clicked on
				Toast.makeText(context, "Clicked on alarm " + alarms.get((int) index).getId(), Toast.LENGTH_SHORT).show();

			}
		});
	}
}
