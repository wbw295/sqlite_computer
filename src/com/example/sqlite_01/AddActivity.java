package com.example.sqlite_01;

import java.util.ArrayList;
import java.util.List;

import com.example.sqlite_01.bean.Computer;
import com.example.sqlite_01.db.DBManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener {

	private EditText name, brand, number, dimension, cpu, graphicsCard, price;
	private Button add, query;

	private DBManager dbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		// setTitle(R.string.label_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		dbm = new DBManager(this);

		name = (EditText) findViewById(R.id.editText1);
		brand = (EditText) findViewById(R.id.editText2);
		number = (EditText) findViewById(R.id.editText3);
		dimension = (EditText) findViewById(R.id.editText4);
		cpu = (EditText) findViewById(R.id.editText5);
		graphicsCard = (EditText) findViewById(R.id.editText6);
		price = (EditText) findViewById(R.id.editText7);

		add = (Button) findViewById(R.id.button1);
		query = (Button) findViewById(R.id.button2);

		add.setOnClickListener(this);
		query.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO 自动生成的方法存根
		if (view == add) {
			Computer computer = new Computer();
			computer.setName(name.getText().toString());
			computer.setBrand(brand.getText().toString());
			computer.setNumber(number.getText().toString());
			computer.setDimension(dimension.getText().toString());
			computer.setCpu(cpu.getText().toString());
			computer.setGraphicsCard(graphicsCard.getText().toString());
			computer.setPrice(price.getText().toString());
			// List<Computer> list = new ArrayList<Computer>();
			// list.add(computer);
			boolean flag = dbm.add(computer);
			if (flag) {

				Toast.makeText(this, "add success!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "add fail! some field may be illegal~",
						Toast.LENGTH_LONG).show();
			}
		} else if (view == query) {

			startActivity(new Intent(this, QueryActivity.class));
		}

	}

}
