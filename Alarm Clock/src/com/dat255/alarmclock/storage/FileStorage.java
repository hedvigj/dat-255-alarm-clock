package com.dat255.alarmclock.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class FileStorage<T> implements IStorage<T> {

	private final Context context;

	public FileStorage(Context context) {
		this.context = context;
	}

	// SuppressWarning is for the unchecked conversion from Object to generic
	// type T
	@SuppressWarnings("unchecked")
	@Override
	public List<T> readObjectsFromFile(String classTag) {
		List<T> objList = new ArrayList<T>();
		Object obj = null;

		try {
			// Open streams for writing
			FileInputStream fis = context.openFileInput(classTag + "Object");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// ois.readObject() can generate ClassNotFoundException
			// Loop while there are more more objects in file and add them into
			// the list
			while ((obj = ois.readObject()) != null) {
				objList.add((T) obj);
			}

			// Close the streams
			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			Log.e("FileStorage", "FileNotFoundException in FileStorage readObjectsToFile");
		} catch (StreamCorruptedException e) {
			Log.e("FileStorage", "StreamCorruptedException in FileStorage readObjectsToFile");
		} catch (IOException e) {
			Log.e("FileStorage", "IOException in FileStorage readObjectsToFile");
		} catch (ClassNotFoundException e) {
			Log.e("FileStorage", "ClassNotFoundException in FileStorage readObjectsToFile");
		}

		return objList;
	}

	@Override
	public void writeObjectsToFile(String classTag, List<T> objList) {
		try {
			// Open streams for writing
			FileOutputStream fos = context.openFileOutput(classTag + "Object", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Write all objects in the list to the file
			for (T o : objList) {
				oos.writeObject(o);
			}

			// Close the streams
			oos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			Log.e("FileStorage", "FileNotFoundException in FileStorage writeObjectsToFile");
		} catch (IOException e) {
			Log.e("FileStorage", "IOException in FileStorage writeObjectsToFile");
		}
	}

	@Override
	public void writeObjectToFile(String classTag, Object obj) {
		try {
			// Open streams for writing
			FileOutputStream fos = context.openFileOutput(classTag + "Object", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Write
			oos.writeObject(obj);

			// Close the streams
			oos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			Log.e("FileStorage", "FileNotFoundException in FileStorage writeObjectToFile");
		} catch (IOException e) {
			Log.e("FileStorage", "IOException in FileStorage writeObjectToFile");
		}
	}

}
