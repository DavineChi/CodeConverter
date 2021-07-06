package com.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import com.converter.utils.ParserUtil;

/************************************************************************************************
 * This class is used to read and manage streams of lines and tokens from a given input file.
 * <p>
 * 
 */
public class TokenInputStream implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static int lineNumber = 1;
	private static String streamLine;
	
	private BufferedReader reader;
	private boolean lineHasContinuation;
	
	/********************************************************************************************
	 * Constructs a new TokenInputStream.
	 * 
	 * @param file - The file from which to get the input stream.
	 */
	public TokenInputStream(String file) {
		
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(file);
		
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
		this.lineHasContinuation = false;
	}
	
	// ******************************************************************************************
	// Helper method to advance the current line number for reporting purposes.
	// 
	private void lineFeed() {
		
		lineNumber++;
	}
	
	// ******************************************************************************************
	// Helper method to concatenate multiple lines that have the continuation character.
	// 
	private String collectContinuationLines() {
		
		String result = "";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(streamLine);
		
		try {
			
			while (lineHasContinuation) {
				
				streamLine = reader.readLine();
				streamLine = streamLine.trim();
				
				this.lineFeed();
				
				sb.append(streamLine);
				
				lineHasContinuation = ParserUtil.hasLineContinuation(streamLine);
			}
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		result = sb.toString();
		result = result.replace(" _", " ");
		
		return result;
	}
	
	/********************************************************************************************
	 * Gets the current line number of this TokenInputStream.
	 * 
	 * @return
	 *   The current line number.
	 */
	public static int getLineNumber() {
		
		return lineNumber;
	}
	
	/********************************************************************************************
	 * Peeks the next line from this TokenInputStream, not advancing the underlying reader.
	 * 
	 * @return
	 *   The next line.
	 */
	public String peekLine() {
		
		String result = null;
		
		try {
			
			reader.mark(5000);
			
			result = reader.readLine();
			
			if (result != null) {
				
				reader.reset();
			}
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			reportPosition();
		}
		
		if (result != null) {
			
			return result.trim();
		}
		
		else {
			
			return result;
		}
	}
	
	/********************************************************************************************
	 * Reads the next line from this TokenInputStream, advancing the underlying reader.
	 * 
	 * @return
	 *   The next line.
	 */
	public String nextLine() {
		
		try {
			
			streamLine = reader.readLine();
			
			TokenInputStream.reportPosition();
			
			lineHasContinuation = ParserUtil.hasLineContinuation(streamLine);
			
			if (lineHasContinuation) {
				
				streamLine = collectContinuationLines();
			}
			
			this.lineFeed();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			reportPosition();
		}
		
		return streamLine.trim();
	}
	
	/********************************************************************************************
	 * Returns whether end of the underlying stream of this TokenInputStream is reached.
	 * 
	 * @return
	 *   True if the end of file is reached, false otherwise.
	 */
	public boolean eof() {
		
		String line = this.peekLine();
		
		return line == null;
	}
	
	/********************************************************************************************
	 * Prints the current line number and line value for this TokenInputStream.
	 */
	public static void reportPosition() {
		
		String id = String.format("%1$6s", lineNumber);
		
		System.out.println("[" + id + "] : " + streamLine);
	}
	
	/********************************************************************************************
	 * Closes this TokenInputStream's underlying reader object.
	 */
	public void close() {
		
		try {
			
			reader.close();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
		}
	}
}
