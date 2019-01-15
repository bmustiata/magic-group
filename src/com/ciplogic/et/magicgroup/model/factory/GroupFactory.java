package com.ciplogic.et.magicgroup.model.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ciplogic.et.magicgroup.model.Group;

public class GroupFactory {

	private Group root;
	public static GroupFactory instance = new GroupFactory();
	
	public Group getRootNode() {
		if (root != null)
			return root;
		
		root = new Group(null, "root");
		
		return root;
	}

	public void load(File dataFile) {
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(
					new FileInputStream(dataFile));
			root = (Group) objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void save(File dataFile) {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(dataFile)
						);
			objectOutputStream.writeObject( this.root );			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null)
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
