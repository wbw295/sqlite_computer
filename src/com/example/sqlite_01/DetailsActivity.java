package com.example.sqlite_01;

import java.util.ArrayList;
import java.util.List;

import com.example.sqlite_01.bean.Computer;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class DetailsActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);

		List<String> attrs = getAttrs();
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, attrs);

		setListAdapter(adapter);

	}

	private List<String> getAttrs() {
		Bundle bundle = getIntent().getExtras();
		Computer computer = (Computer) bundle.get(QueryActivity.KEY_COMPUTER);
		List<String> attrs = new ArrayList<String>();
		attrs.add("name: " + computer.getName());
		attrs.add("brand: " + computer.getBrand());
		attrs.add("number: " + computer.getNumber());
		attrs.add("dimension: " + computer.getDimension());
		attrs.add("cpu: " + computer.getCpu());
		attrs.add("graphicsCard: " + computer.getGraphicsCard());
		attrs.add("price: " + computer.getPrice());
		return attrs;
	}

}
