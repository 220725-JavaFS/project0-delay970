package com.revature.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class OutFile {

	public OutFile() {
	}

	public void toFile(String path, ArrayList<?> array) {
	if(array.size() == 0) {
	System.out.println("no objects to save to file");
	return;
	}

	Object object = array.get(0);

	final String prefix = "class ";
	String className = object.getClass().toString();
	className = className.substring(className.indexOf(prefix) + prefix.length());

	String methods = Arrays.toString(object.getClass().getDeclaredMethods());

	ArrayList <String> vNames = new ArrayList<String>();

	while(true) {
	int index = methods.indexOf(className+".get");
	if(index == -1) {
	break;
	}
	methods = methods.substring(methods.indexOf(className+".get") + (className+".get").length());
	String vName = methods.substring(0, methods.indexOf('('));
	vNames.add(vName);
	}

	try {
	FileWriter writer = new FileWriter(path);
	writer.write(vNames.toString());
	writer.write('\n');
	for(Object o: array) {
	writer.write(o.toString());
	writer.write('\n');
	}
	     writer.close();
	     System.out.println("Successfully wrote to the file.");
	}catch (IOException e) {
	     System.out.println("An error occurred.");
	     e.printStackTrace();
	   }
	}
}
