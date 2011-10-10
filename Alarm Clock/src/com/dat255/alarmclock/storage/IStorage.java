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
	public List<T> readObjectsFromFile(String classTag);

	/**
	 * Write Objects to a specific file
	 * 
	 * @param fileName
	 *            is the file which will contains the Object
	 * @param objList
	 *            is the Objects to be written
	 */
	public boolean writeObjectsToFile(String classTag, List<T> objList);

	/**
	 * Write Object to a specific file
	 * 
	 * @param fileName
	 *            is the file which will contains the Object
	 * @param obj
	 *            is the Object to be written
	 */
	public boolean writeObjectToFile(String classTag, T obj);
}
