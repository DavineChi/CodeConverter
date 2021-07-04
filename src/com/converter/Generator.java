package com.converter;

import com.converter.node.ASTNode;
import com.converter.node.MethodNode;
import com.converter.node.ParameterNode;

public class Generator {
	
	public static void convert(MethodNode node) {
		
		String nodeAccessModifier = node.getAccessModifier().toLowerCase();
		//String nodeCallType = node.getCallType();
		String nodeReturnType = node.getReturnType();
		String nodeName = node.getName();
		ParameterNode[] nodeParameters = node.getParameters();
		ASTNode nodeBody = node.getBody();
		
		if (nodeReturnType == null) {
			
			nodeReturnType = "void";
		}
		
		String methodHeader = nodeAccessModifier + " " + nodeReturnType + " " + nodeName;
		
		String tab = "\t";
		
		System.out.println(methodHeader);
		System.out.println("{");
		System.out.println(tab + "cout >> \"Hello World!\"");
		System.out.println("}");
	}
}
