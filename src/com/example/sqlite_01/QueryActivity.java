package com.example.sqlite_01;

import java.util.ArrayList;
import java.util.List;

import com.example.sqlite_01.bean.Computer;
import com.example.sqlite_01.db.DBManager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QueryActivity extends ListActivity {

	public static final String KEY_COMPUTER = "com.example.sqlite_01.bean.Computer";

	private List<Computer> computers;
	private DBManager dbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		dbm = new DBManager(this);
		List<String> names = getComputers();
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		setListAdapter(adapter);
	}

	private List<String> getComputers() {
		// TODO 自动生成的方法存根

		computers = dbm.query();

		List<String> names = new ArrayList<String>();

		for (Computer computer : computers) {

			names.add(computer.getName());
		}

		return names;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO 自动生成的方法存根
		super.onListItemClick(l, v, position, id);

		String name = (String) ((TextView) v).getText();

		Computer computer = dbm.queryByName(name);

		Intent intent = new Intent(this, DetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(QueryActivity.KEY_COMPUTER, computer);
		intent.putExtras(bundle);

		startActivity(intent);

	}

}
