package com.dat255.alarmclock.test.storage;

import java.io.Serializable;
import java.util.List;

/**
 * Test class used to the the storage functionality in FileStorageTest
 * 
 * @author Sickan
 * 
 */
public class TestClassTwo implements Serializable {
	private static final long serialVersionUID = -7759027063243614713L;
	public static final String TAG = "TestClassTwo";
	public List<TestClassOne> list;

	public TestClassTwo(List<TestClassOne> list) {
		this.list = list;
	}
}