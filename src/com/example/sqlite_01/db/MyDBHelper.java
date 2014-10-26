package com.example.sqlite_01.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "computer.db";
	private static final int DB_VERSION = 1;

	public MyDBHelper(Context context) {
		// CursorFactory����Ϊnull��ʹ��Ĭ��ֵ
		super(context, DB_NAME, null, DB_VERSION);

	}

	// ���ݿ��һ�α�����ʱ�ᱻ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������

		String createComputer = "create table if not exists computer (_id integer primary key autoincrement, name varchar, brand varchar, number varchar, dimension varchar, cpu varchar, graphics_card varchar, price varchar)";
		db.execSQL(createComputer);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������
		String updateComputer = "alter table computer add column other string";
		db.execSQL(updateComputer);

	}

}
