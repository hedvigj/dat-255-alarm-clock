package com.dat255.alarmclock.test.storage;

import java.io.Serializable;

/**
 * Test class used to the the storage functionality in FileStorageTest
 * 
 * @author Sickan
 * 
 */
public class TestClassOne implements Serializable {
	private static final long serialVersionUID = -3227313594217121556L;
	public static final String TAG = "TestClassOne";
	public String testStringOne;
	public String testStringTwo;

	public TestClassOne(String testStringOne, String testStringTwo) {
		this.testStringOne = testStringOne;
		this.testStringTwo = testStringTwo;
	}
}
