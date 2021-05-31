package com.converter;

public class Token {
	
	public enum TokenType {
		
		PUNCTUATION,
		NUMBER,
		STRING,
		KEYWORD,
		IDENTIFIER,
		OPERATOR,
		COMMENT
	}
	
	private TokenType tokenType;
	private String tokenValue;
	
	public Token(TokenType tokenType, String tokenValue) {
		
		this.tokenType = tokenType;
		this.tokenValue = tokenValue;
	}
	
	public String getTokenType() {
		
		return String.valueOf(tokenType);
	}
	
	public String getTokenValue() {
		
		return tokenValue;
	}
}
