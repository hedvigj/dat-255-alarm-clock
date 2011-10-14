package com.dat255.alarmclock.view;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.dat255.alarmclock.R;
import com.dat255.alarmclock.logic.group.GroupManager;
import com.dat255.alarmclock.logic.group.IGroup;

public class OverviewActivity extends ListActivity {

	private List<IGroup> groups;

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

				// Check or uncheck the checkbox according to the status of the
				// group
				getListView().setItemChecked(position, groups.get(position).isEnabled());

				// Open the group activity
				Intent intent = new Intent(context, GroupActivity.class);

				intent.putExtra("groupid", groups.get((int) id).getId());

				context.startActivity(intent);
			}
		});
	}

	private void fillListActivity() {
		// Display all groups in the list view
		groups = GroupManager.getInstance().getGroups();

		String[] names = new String[groups.size()];

		for (int i = 0; i < groups.size(); i++) {
			names[i] = groups.get(i).getName();
		}

		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));

		// See if groups are enabled, and set the checkbox accordingly
		for (int i = 0; i < groups.size(); i++) {
			getListView().setItemChecked(i, groups.get(i).isEnabled());
		}

	}

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
	 * Handle options menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.creategroup:

			// Creates an dialog to insert name of group with ok and cancel
			// option
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			final EditText input = new EditText(this);
			alert.setMessage(R.string.namedialog);
			alert.setView(input);
			alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int button) {
					String s = input.getText().toString().trim();

					if (s.equals("")) {
						dialog.cancel();
					} else {
						IGroup group = GroupManager.getInstance().createGroup();

						group.setName(s);

						fillListActivity();
					}
				}

			});

			alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int button) {
					dialog.cancel();
				}
			});
			alert.show();

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

		inflater.inflate(R.menu.longpress_menu, menu);
	}

	/**
	 * Handle long-press context menu
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.delete:

			// Delete the selected group and all of it's alarms
			IGroup group = groups.get((int) info.id);

			GroupManager.getInstance().removeGroupAndContent(group);

			fillListActivity();

			return true;
		case R.id.enable:
			CheckedTextView chk = (CheckedTextView) info.targetView;
			group = groups.get((int) info.id);

			// Enable all alarms in the group
			group.enable();

			// Check the group's checkbox to confirm enabling
			chk.setChecked(true);

			return true;

		case R.id.disable:
			chk = (CheckedTextView) info.targetView;
			group = groups.get((int) info.id);

			// Disable all alarms in the group
			group.disable();

			// Uncheck the group's checkbox to confirm disabling
			chk.setChecked(false);

			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}
