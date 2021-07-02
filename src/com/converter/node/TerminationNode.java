package com.converter.node;

import com.converter.TokenInputStream;

public class TerminationNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeStatement;
	
	public TerminationNode(String statement) {
		
		this.nodeType = NodeType.TERMINATION;
		this.lineNumber = TokenInputStream.getLineNumber();
		this.nodeStatement = statement;
	}
	
	public String getStatement() {
		
		return nodeStatement;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeStatement + "] (line " + lineNumber + ")";
	}
}
