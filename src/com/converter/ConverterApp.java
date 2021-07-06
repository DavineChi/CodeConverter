package com.converter;

import com.converter.node.ProgramNode;

public class ConverterApp {
	
	public static final String FILE = "COrderMaint";
	
	public static void main(String[] args) {
		
		Parser parser = new Parser(FILE);
		Inspector inspector = null;
		Generator generator = null;
		
		parser.start();
		parser.save();
		
		try {
			
			Thread.sleep(1000);
		}
		
		catch (InterruptedException ex) {
			
			ex.printStackTrace();
		}
		
		ProgramNode program = parser.load();
		
		inspector = new Inspector(program);
		generator = new Generator(program, "Daikin.Eng", "Legacy" + FILE);
		
		generator.start();
		
		//generator.print();
		
		generator.write("Legacy" + FILE + ".cs");
		
		int countMethods = inspector.getNumberOfMethods();
		
		System.out.println(" > Processed " + countMethods + " methods.");
	}
}
