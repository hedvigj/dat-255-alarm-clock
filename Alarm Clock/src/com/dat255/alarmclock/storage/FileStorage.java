package com.dat255.alarmclock.storage;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class FileStorage<T extends Serializable> implements IStorage<T> {

	private final Context context;

	public FileStorage(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> readObjectsFromFile(String classTag) {
		List<T> objList = new ArrayList<T>();

		try {
			// Open streams for writing
			ObjectInputStream ois = null;

			ois = new ObjectInputStream(context.openFileInput(classTag
					+ "Object"));

			// Test
			objList = (List<T>) ois.readObject();

			// Close the streams
			ois.close();

		} catch (EOFException e) {
			Log.w("FileStorage",
					"FileNotFoundException in FileStorage readObjectsToFile");
		} catch (FileNotFoundException e) {
			Log.e("FileStorage",
					"FileNotFoundException in FileStorage readObjectsToFile");
		} catch (StreamCorruptedException e) {
			Log.e("FileStorage",
					"StreamCorruptedException in FileStorage readObjectsToFile");
		} catch (ClassNotFoundException e) {
			Log.e("FileStorage",
					"ClassNotFoundException in FileStorage readObjectsToFile");
		} catch (IOException e) {
			Log.e("FileStorage",
					"IOException in FileStorage readObjectsToFile "
							+ e.getLocalizedMessage());
		}

		return objList;
	}

	@Override
	public boolean writeObjectsToFile(String classTag, List<T> objList) {
		try {
			// Open streams for writing
			ObjectOutputStream oos = new ObjectOutputStream(
					context.openFileOutput(classTag + "Object",
							Context.MODE_PRIVATE));

			oos.writeObject(objList);

			oos.flush();

			// Close the ObjectInputStream
			oos.close();

		} catch (FileNotFoundException e) {
			Log.e("FileStorage",
					"FileNotFoundException in FileStorage writeObjectsToFile");
			return false;
		} catch (IOException e) {
			Log.e("FileStorage",
					"IOException in FileStorage writeObjectsToFile "
							+ e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean writeObjectToFile(String classTag, T obj) {
		List<T> list = new ArrayList<T>();
		list.add(obj);
		return writeObjectsToFile(classTag, list);
	}
}
