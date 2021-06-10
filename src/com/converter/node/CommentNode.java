package com.converter.node;

public class CommentNode extends ASTNode {
	
	private String nodeComment;
	
	public CommentNode(String comment) {
		
		this.nodeType = NodeType.COMMENT;
		this.nodeComment = comment;
	}
	
	public String getComment() {
		
		return nodeComment;
	}
}
