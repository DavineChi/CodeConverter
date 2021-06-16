package com.converter.node;

import java.io.Serializable;

public abstract class ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected NodeType nodeType;
	
	public NodeType getType() {
		
		return nodeType;
	}
}
