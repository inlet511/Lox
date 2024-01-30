package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;

public class AstPrinter implements Expr.Visitor<String> {
	String print(Expr expr) {
		return expr.accept(this);
	}

	@Override
	public String visitBinaryExpr(Binary expr) {
		// TODO Auto-generated method stub
		return parenthesize(expr.operator.lexeme,expr.left,expr.right);
	}

	@Override
	public String visitGroupingExpr(Grouping expr) {
		// TODO Auto-generated method stub
		return parenthesize("group",expr.expression);
	}

	@Override
	public String visitLiteralExpr(Literal expr) {
		// TODO Auto-generated method stub
		if(expr.Value==null) return "nil";
		return expr.Value.toString();
	}

	@Override
	public String visitUnaryExpr(Unary expr) {
		// TODO Auto-generated method stub
		return parenthesize(expr.operator.lexeme, expr.right);
	}
	
	private String parenthesize(String name, Expr... exprs)
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("(").append(name);
		for(Expr expr:exprs)
		{
			builder.append(" ");
			builder.append(expr.accept(this));
		}
		builder.append(")");
		
		return builder.toString();		
	}
	
}
