package com.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.converter.Token.TokenType;

/************************************************************************************************
 * This class is used to read and manage streams of lines and tokens from a given input file.
 * <p>
 * 
 */
public class TokenInputStream {
	
	private int lineNumber = 1;
	private int tokenColumn = 0;
	
	private BufferedReader reader;
	private String delimiter;
	private String streamLine;
	private String[] tokens;
	private Token current;
	
	/********************************************************************************************
	 * Constructs a new TokenInputStream with a space character (" ") as the default delimiter.
	 * 
	 * @param file - The file from which to get the input stream.
	 * 
	 */
	public TokenInputStream(String file) {
		
		this(file, " ");
	}
	
	/********************************************************************************************
	 * Constructs a new TokenInputStream with the specified delimiter.
	 * 
	 * @param file - The file from which to get the input stream.
	 * @param delimiter - The delimiter used to parse the input stream.
	 * 
	 */
	public TokenInputStream(String file, String delimiter) {
		
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(file);
		
		this.delimiter = delimiter;
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
		//this.streamLine = this.peekLine();
		//this.tokens = streamLine.split(delimiter);
	}
	
	private void lineFeed() {
		
		tokenColumn = 0;
		lineNumber++;
	}
	
	public String readWhile(boolean condition) {
		
		String result = "";
		
		while (!this.eof()) {
			
			// TODO: fix
			result = this.peekToken();
		}
		
		return result;
	}
	
	public Token readNumber() {
		
		Token result;
		
		result = new Token(Token.TokenType.NUMBER, "");
		
		return result;
	}
	
	public void readIdentifier() {
		
		
	}
	
	public void readEscaped() {
		
		
	}
	
	public void readString() {
		
		
	}
	
	/********************************************************************************************
	 * Reads this TokenInputStream's current line and returns a Token containing the comment.
	 * <p>
	 * 
	 * NOTE: This method does not 'consume' the TokenInputStream's line.
	 * <p>
	 * 
	 * @param preserve
	 *   True if the comment should be preserved, false otherwise.
	 * 
	 * @return
	 *   A new Token containing the comment in this TokenInputStream's line if
	 *   <CODE>preserve</CODE> is true, <CODE>null</CODE> is returned otherwise.
	 */
	public Token readComment(boolean preserve) {
		
		Token result = null;
		
		int index = streamLine.indexOf("'");
		String value = streamLine.substring(index);
		
		if (preserve) {
			
			// TODO: Do not convert anything in this class.
			// Create a separate class to handle conversions and transforms.
			//value = streamLine.replace("'", "//");
			
			result = new Token(TokenType.COMMENT, value);
		}
		
		return result;
	}
	
	public Token readNext() {
		
		Token result = null;
		
		if (this.eof()) {
			
			return null;
		}
		
		return result;
	}
	
	public String peekToken() {
		
		String token = null;
		
		try {
			
			reader.mark(1);
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			reportPosition();
		}
		
		return token;
	}
	
	public String peekLine() {
		
		try {
			
			reader.mark(1);
			
			streamLine = reader.readLine();
			
			if (streamLine != null) {
				
				reader.reset();
			}
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			reportPosition();
		}
		
		return streamLine;
	}
	
	public String nextToken() {
		
		String token = "";
		
		if ((tokenColumn + 1) > tokens.length) {
			
			try {
				
				String query = reader.readLine();
				
				if (query.equals(streamLine)) {
					
					// Advance the reader to the next line.
					streamLine = reader.readLine();
				}
				
				tokens = streamLine.split(delimiter);
				
				this.lineFeed();
			}
			
			catch (IOException ex) {
				
				ex.printStackTrace();
			}
		}
		
		token = tokens[tokenColumn++];
		
		return token;
	}
	
	public String nextLine() {
		
		String stream = "";
		
		try {
			
			stream = reader.readLine();
			
			this.lineFeed();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
			reportPosition();
		}
		
		return stream;
	}
	
	public String[] split(String value) {
		
		String[] result = null;
		
		result = value.split(delimiter);
		
		return result;
	}
	
	public boolean eof() {
		
		String line = this.peekLine();
		
		return line == null;
	}
	
	public void reportPosition() {
		
		System.out.println("Line : " + lineNumber + ", token column : " + tokenColumn);
	}
}
