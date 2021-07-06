package com.converter;

public class StringManager {
	
	private static final boolean DEBUG = true;
	
	private static final String TAB_CHAR = "\t";
	private static final String LF_CHAR  = "\n";
	
	private static StringBuilder output = new StringBuilder();
	
	private static int lineNumber = 0;
	private static String currentLine = "";
	
	private static int tabIndents = 0;
	private static String tabs = "";
	
	// ******************************************************************************************
	// Helper method to advance the current line number for reporting purposes.
	// 
	private static void lineFeed() {
		
		lineNumber++;
	}
	
	private static void computeTabs() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < tabIndents; i++) {
			
			sb.append(TAB_CHAR);
		}
		
		tabs = sb.toString();
	}
	
	public static void append(String value) {
		
		currentLine = value;
		
		StringManager.computeTabs();
		
		//output.append(tabs).append(currentLine);
		
		currentLine = tabs + currentLine;
		
		output.append(currentLine).append(LF_CHAR);
		
		StringManager.lineFeed();
		
		if (DEBUG) {
			
			StringManager.reportPosition();
		}
	}
	
	// TODO: remove?
	public static void appendLine(String value) {
		
		currentLine = value;
		
		StringManager.computeTabs();
		
		//output.append(tabs).append(currentLine).append(LF_CHAR);
		
		currentLine = tabs + currentLine + LF_CHAR;
		
		output.append(currentLine);
		
		StringManager.lineFeed();
		
		if (DEBUG) {
			
			StringManager.reportPosition();
		}
	}
	
	public static String getOutput() {
		
		return output.toString();
	}
	
	public static void incrementIndents() {
		
		tabIndents++;
	}
	
	public static void decrementIndents() {
		
		tabIndents--;
	}
	
	/********************************************************************************************
	 * Prints the current line number and line value for this StringManager.
	 */
	public static void reportPosition() {
		
		String id = String.format("%1$6s", lineNumber);
		
		System.out.println("[" + id + "] : " + currentLine);
	}
}
