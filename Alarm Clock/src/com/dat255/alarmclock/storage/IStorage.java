package com.dat255.alarmclock.storage;

import java.util.List;

public interface IStorage<T> {
	/**
	 * Read Objects from a specific file
	 * 
	 * @param fileName
	 *            is the file which contains the Object
	 * 
	 * @return Objects in specific file
	 */
	public List<T> readObjects(String classTag);

	/**
	 * Write Objects to storage
	 * 
	 * @param fileName
	 *            is the file which will contains the Object
	 * @param objList
	 *            is the Objects to be written
	 */
	public boolean writeObjects(String classTag, List<T> objList);

	/**
	 * Write Object to storage
	 * 
	 * @param fileName
	 *            is the file which will contains the Object
	 * @param obj
	 *            is the Object to be written
	 */
	public boolean writeObject(String classTag, T obj);
}
