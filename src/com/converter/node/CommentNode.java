package com.converter.node;

import java.io.Serializable;

public class CommentNode extends ASTNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nodeComment;
	
	public CommentNode(String comment) {
		
		this.nodeType = NodeType.COMMENT;
		this.nodeComment = comment;
	}
	
	public String getComment() {
		
		return nodeComment;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeComment + "]";
	}
}
