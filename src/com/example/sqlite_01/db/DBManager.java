package com.example.sqlite_01.db;

import java.util.ArrayList;
import java.util.List;

import com.example.sqlite_01.bean.Computer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBManager {

	private SQLiteOpenHelper helper;

	private SQLiteDatabase db;

	private Context mContext;

	public DBManager(Context context) {
		mContext = context;

		helper = new MyDBHelper(mContext);

		db = helper.getWritableDatabase();
	}

	public boolean add(List<Computer> computers) {

		for (Computer computer : computers) {
			Computer c = filter(computer);
			if (c == null) {
				return false;
			}
		}

		db.beginTransaction();

		try {

			for (Computer computer : computers) {
				insert(computer);
			}
			db.setTransactionSuccessful();

		} finally {
			db.endTransaction();
		}

		return true;

	}

	public boolean add(Computer computer) {

		Computer c = filter(computer);
		if (c == null) {
			return false;
		}

		db.beginTransaction();
		try {
			insert(c);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();

		}
		return true;
	}

	private Computer filter(Computer computer) {
		List<String> tags = new ArrayList<String>();
		Computer filtedComputer = new Computer();
		tags.add(computer.getName().trim());
		tags.add(computer.getBrand().trim());
		tags.add(computer.getNumber().trim());
		tags.add(computer.getDimension().trim());
		tags.add(computer.getCpu().trim());
		tags.add(computer.getGraphicsCard().trim());
		tags.add(computer.getPrice().trim());
		if (tags.contains("")) {
			return null;
		} else if (queryByName(tags.get(0)) != null) {
			return null;
		} else {
			filtedComputer.setName(tags.get(0));
			filtedComputer.setBrand(tags.get(1));
			filtedComputer.setNumber(tags.get(2));
			filtedComputer.setDimension(tags.get(3));
			filtedComputer.setCpu(tags.get(4));
			filtedComputer.setGraphicsCard(tags.get(5));
			filtedComputer.setPrice(tags.get(6));
		}
		return filtedComputer;
	}

	private void insert(Computer computer) {

		String insert = "insert into computer values(null, ?, ?, ?, ?, ?, ?, ?)";
		Object[] values = new Object[] { computer.getName(),
				computer.getBrand(), computer.getNumber(),
				computer.getDimension(), computer.getCpu(),
				computer.getGraphicsCard(), computer.getPrice() };
		db.execSQL(insert, values);
	}

	private Computer buildComputer(Cursor cursor) {
		Computer computer = new Computer();
		String name = cursor.getString(cursor.getColumnIndex("name"));
		String brand = cursor.getString(cursor.getColumnIndex("brand"));
		String number = cursor.getString(cursor.getColumnIndex("number"));
		String dimension = cursor.getString(cursor.getColumnIndex("dimension"));
		String cpu = cursor.getString(cursor.getColumnIndex("cpu"));
		String graphicsCard = cursor.getString(cursor
				.getColumnIndex("graphics_card"));
		String price = cursor.getString(cursor.getColumnIndex("price"));

		computer.setName(name);
		computer.setBrand(brand);
		computer.setNumber(number);
		computer.setDimension(dimension);
		computer.setCpu(cpu);
		computer.setGraphicsCard(graphicsCard);
		computer.setPrice(price);
		return computer;

	}

	public List<Computer> query() {
		List<Computer> computers = new ArrayList<Computer>();

		Cursor cursor = db.rawQuery("select * from computer", null);
		Toast.makeText(mContext, "cursor result : " + cursor.getCount(),
				Toast.LENGTH_LONG).show();
		while (cursor.moveToNext()) {
			Computer computer = buildComputer(cursor);
			computers.add(computer);
		}
		cursor.close();
		return computers;
	}

	public Computer queryByName(String name) {
		if (name == null) {
			return null;
		}
		Computer computer = null;

		Cursor cursor = db.rawQuery("select * from computer where name = ?",
				new String[] { name });
		if (cursor.moveToNext()) {
			computer = buildComputer(cursor);
		}
		return computer;
	}

	public void update(Computer computer) {
	}

	public void delete(Computer computer) {

	}

}
