package com.converter;

import com.converter.node.ProgramNode;

public class ConverterApp {
	
	public static void main(String[] args) {
		
		Parser parser = new Parser("main/resources/CEto.cls");
		Inspector inspector = null;
		Generator generator = null;
		
		//parser.start();
		//parser.save();
		
		ProgramNode program = parser.load();
		
		inspector = new Inspector(program);
		generator = new Generator(program, "Daikin.Eng", "LegacyETO");
		
		generator.start();
		
		//generator.print();
		
		generator.write("LegacyETO.cs");
		
		int countMethods = inspector.getNumberOfMethods();
		
		System.out.println(" > Processed " + countMethods + " methods.");
	}
}
