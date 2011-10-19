package com.dat255.alarmclock.test.storage;

import java.util.ArrayList;
import java.util.List;

import android.test.AndroidTestCase;

import com.dat255.alarmclock.storage.FileStorage;

public class FileStorageTest extends AndroidTestCase {
	private List<TestClassOne> classListOne;
	private List<TestClassTwo> classListTwo;
	private FileStorage<TestClassOne> storageOne;
	private FileStorage<TestClassTwo> storageTwo;

	@Override
	public void setUp() throws Exception {
		super.setUp();

		classListOne = null;
		classListTwo = null;

		storageOne = null;
		storageTwo = null;
	}

	public void testFileStorage() {
		// No error should be generated using the constructor
		for (int i = 0; i < 100; i++) {
			@SuppressWarnings("unused")
			FileStorage<TestClassOne> testStorageOne = new FileStorage<TestClassOne>(getContext());
			@SuppressWarnings("unused")
			FileStorage<TestClassTwo> testStorageTwo = new FileStorage<TestClassTwo>(getContext());
		}

	}

	public void testWriteObjectsToFile() {
		classListOne = new ArrayList<TestClassOne>();
		classListTwo = new ArrayList<TestClassTwo>();

		storageOne = new FileStorage<TestClassOne>(getContext());
		storageTwo = new FileStorage<TestClassTwo>(getContext());

		// Build list to write to files
		for (int i = 1; i < 100; i++) {
			TestClassOne testOne = new TestClassOne("StringOne" + Integer.toString(i), "StringTwo" + Integer.toString(i));

			classListOne.add(testOne);

			// Clone list to set it into the other class
			List<TestClassOne> cloneList = new ArrayList<TestClassOne>();
			for (TestClassOne c : classListOne) {
				cloneList.add(c);
			}
			classListTwo.add(new TestClassTwo(cloneList));

			// Write to file
			assert storageOne.writeObjects(TestClassOne.TAG, classListOne);
			assert storageTwo.writeObjects(TestClassTwo.TAG, classListTwo);
		}

	}

	public void testReadObjectsFromFile() {
		classListOne = new ArrayList<TestClassOne>();
		classListTwo = new ArrayList<TestClassTwo>();

		storageOne = new FileStorage<TestClassOne>(getContext());
		storageTwo = new FileStorage<TestClassTwo>(getContext());

		for (int i = 1; i < 15; i++) {
			TestClassOne testOne = new TestClassOne("StringOne" + Integer.toString(i), "StringTwo" + Integer.toString(i));

			classListOne.add(testOne);

			// Clone list to set it into the other class
			List<TestClassOne> cloneList = new ArrayList<TestClassOne>();
			for (TestClassOne c : classListOne) {
				cloneList.add(c);
			}
			classListTwo.add(new TestClassTwo(cloneList));

			// Write to file
			storageOne.writeObjects(TestClassOne.TAG, classListOne);
			storageTwo.writeObjects(TestClassTwo.TAG, classListTwo);

			// New FileStorage object should not make a different
			storageOne = new FileStorage<TestClassOne>(getContext());
			storageTwo = new FileStorage<TestClassTwo>(getContext());

			// Read from storageOne
			List<TestClassOne> readFromStorageOne = storageOne.readObjects(TestClassOne.TAG);

			// Test all values are intact
			for (int n = 0; n < i; n++) {
				assertEquals(readFromStorageOne.get(n).testStringOne, classListOne.get(n).testStringOne);
				assertEquals(readFromStorageOne.get(n).testStringTwo, classListOne.get(n).testStringTwo);
			}

			// Read from storageTwo
			List<TestClassTwo> readFromStorageTwo = storageTwo.readObjects(TestClassTwo.TAG);
			// Test that the last value values are intact

			assertEquals(readFromStorageTwo.get(i - 1).list.get(i - 1).testStringOne, classListTwo.get(i - 1).list.get(i - 1).testStringOne);
			assertEquals(readFromStorageTwo.get(i - 1).list.get(i - 1).testStringTwo, classListTwo.get(i - 1).list.get(i - 1).testStringTwo);

		}
	}

	public void testWriteObjectToFile() {
		classListOne = new ArrayList<TestClassOne>();
		classListTwo = new ArrayList<TestClassTwo>();

		storageOne = new FileStorage<TestClassOne>(getContext());
		storageTwo = new FileStorage<TestClassTwo>(getContext());

		// Build list to write to files
		for (int i = 1; i < 100; i++) {
			TestClassOne testOne = new TestClassOne("StringOne" + Integer.toString(i), "StringTwo" + Integer.toString(i));

			classListOne.add(testOne);

			// Clone list to set it into the other class
			List<TestClassOne> cloneList = new ArrayList<TestClassOne>();
			for (TestClassOne c : classListOne) {
				cloneList.add(c);
			}
			TestClassTwo testTwo = new TestClassTwo(cloneList);

			// Write to file
			assert storageOne.writeObject(TestClassOne.TAG, testOne);
			assert storageTwo.writeObject(TestClassTwo.TAG, testTwo);
		}

	}

}
