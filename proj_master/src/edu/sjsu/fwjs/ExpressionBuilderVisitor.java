package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.sjsu.fwjs.parser.FeatherweightJavaScriptBaseVisitor;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser;

public class ExpressionBuilderVisitor extends FeatherweightJavaScriptBaseVisitor<Expression> {
    @Override
    public Expression visitProg(FeatherweightJavaScriptParser.ProgContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i = 0; i < ctx.stat().size(); i++) {
            Expression exp = visit(ctx.stat(i));
            if (exp != null)
                stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    @Override
    public Expression visitBareExpr(FeatherweightJavaScriptParser.BareExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitIfThenElse(FeatherweightJavaScriptParser.IfThenElseContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block(0));
        Expression els = visit(ctx.block(1));
        return new IfExpr(cond, thn, els);
    }

    @Override
    public Expression visitIfThen(FeatherweightJavaScriptParser.IfThenContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block());
        return new IfExpr(cond, thn, null);
    }

    @Override
    public Expression visitWhile(FeatherweightJavaScriptParser.WhileContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression keepDoing = visit(ctx.block());
        return new WhileExpr(cond, keepDoing);
    }

    @Override
    public Expression visitPrint(FeatherweightJavaScriptParser.PrintContext ctx) {
        Expression val = visit(ctx.expr());
        return new PrintExpr(val);
    }

    @Override
    public Expression visitInt(FeatherweightJavaScriptParser.IntContext ctx) {
        int val = Integer.valueOf(ctx.INT().getText());
        return new ValueExpr(new IntVal(val));
    }

    public Expression visitBoolean(FeatherweightJavaScriptParser.BooleanContext ctx) {
        Boolean bol = Boolean.valueOf(ctx.BOOL().getText());
        return new ValueExpr(new BoolVal(bol));
    }

    public Expression visitNull(FeatherweightJavaScriptParser.NullContext ctx) {
        return new ValueExpr(new NullVal());
    }

    @Override
    public Expression visitParens(FeatherweightJavaScriptParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitFullBlock(FeatherweightJavaScriptParser.FullBlockContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i = 1; i < ctx.getChildCount() - 1; i++) {
            Expression exp = visit(ctx.getChild(i));
            stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    /**
     * Converts a list of expressions to one sequence expression, if the list
     * contained more than one expression.
     */
    private Expression listToSeqExp(List<Expression> stmts) {
        if (stmts.isEmpty())
            return null;
        Expression exp = stmts.get(0);
        for (int i = 1; i < stmts.size(); i++) {
            exp = new SeqExpr(exp, stmts.get(i));
        }
        return exp;
    }

    @Override
    public Expression visitSimpBlock(FeatherweightJavaScriptParser.SimpBlockContext ctx) {
        return visit(ctx.stat());
    }

    @Override
    public Expression visitVariableReference(FeatherweightJavaScriptParser.VariableReferenceContext ctx) {
        return new VarExpr(ctx.IDENTIFIER().getSymbol().getText());
    }

    public Expression visitVariableDeclaration(FeatherweightJavaScriptParser.VariableDeclarationContext ctx) {
<<<<<<< HEAD
        return new VarDeclExpr(ctx.IDENTIFIER().getSymbol().getText(), visit(ctx.expr()));
    }

    public Expression visitAssignmentStatement(FeatherweightJavaScriptParser.AssignmentStatementContext ctx) {
        return new AssignExpr(ctx.IDENTIFIER().getSymbol().getText(), visit(ctx.expr()));
    }

    public Expression visitFunctionCall(FeatherweightJavaScriptParser.FunctionCallContext ctx) {
        return new FunctionAppExpr(visit(ctx.expr()), 
                                   ctx.arglist().expr()
                                   .stream().map(x -> visit(x)).collect(Collectors.toList())
                                  );
=======
        return new VarDeclExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitAssignmentStatement(FeatherweightJavaScriptParser.AssignmentStatementContext ctx) {
        return new AssignExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitFunctionCall(FeatherweightJavaScriptParser.FunctionCallContext ctx) {
        return new FunctionAppExpr(ctx.expr(0), visit(ctx.arglist()));
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
    }

    public Expression visitArglist(FeatherweightJavaScriptParser.ArglistContext ctx) {
        throw new RuntimeException("Oops! This function should never have been called. Arglists are parsed as part of functions only!");
    }

	@Override
	public Expression visitMulDivMod(FeatherweightJavaScriptParser.MulDivModContext ctx) {
		Expression left = visit(ctx.expr(0));
		Expression right = visit(ctx.expr(1));
		int op = ctx.op.getType();
<<<<<<< HEAD
		if (op == FeatherweightJavaScriptParser.MULTIPLY)
			return new BinOpExpr(Op.MULTIPLY,left, right);
		else if (op == FeatherweightJavaScriptParser.MOD)
=======
		if (op == ExprParser.MULTIPLY)
			return new BinOpExpr(Op.MULTIPLY,left, right);
		else if (op == ExprParser.MOD)
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
			return new BinOpExpr(Op.MOD,left, right);
		else
			return new BinOpExpr(Op.DIVIDE,left, right);
	}
	@Override
	public Expression visitComparisons(FeatherweightJavaScriptParser.ComparisonsContext ctx) {
		Expression left = visit(ctx.expr(0));
		Expression right = visit(ctx.expr(1));
		int op = ctx.op.getType();
<<<<<<< HEAD
		if (op == FeatherweightJavaScriptParser.GT)
			return new BinOpExpr(Op.GT,left, right);
		else if (ctx.op.getType() == FeatherweightJavaScriptParser.GE)
			return new BinOpExpr(Op.GE,left, right);
		else if (op == FeatherweightJavaScriptParser.LT)
			return new BinOpExpr(Op.LT,left, right);
		else if (op == FeatherweightJavaScriptParser.LE)
=======
		if (op == ExprParser.GT)
			return new BinOpExpr(Op.GT,left, right);
		else if (ctx.op.getType() == ExprParser.GE)
			return new BinOpExpr(Op.GE,left, right);
		else if (op == ExprParser.LT)
			return new BinOpExpr(Op.LT,left, right);
		else if (op == ExprParser.LE)
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
			return new BinOpExpr(Op.LE,left, right);
		else 
			return new BinOpExpr(Op.EQ,left, right);
	}
	@Override
	public Expression visitAddSub(FeatherweightJavaScriptParser.AddSubContext ctx) {
		Expression left = visit(ctx.expr(0));
		Expression right = visit(ctx.expr(1));
		int op = ctx.op.getType();
<<<<<<< HEAD
		if (op == FeatherweightJavaScriptParser.ADD)
=======
		if (op == ExprParser.ADD)
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
			return new BinOpExpr(Op.ADD,left, right);
		else
			return new BinOpExpr(Op.SUBTRACT,left, right);
	}
	@Override
	public Expression visitAnonFunctionDeclaration(FeatherweightJavaScriptParser.FunctionDeclarationContext ctx) {
		List<Expression> stmts = ctx.idlists;
		List<String> params = new ArrayList<String>();
		for(Expression e : stmts){
			params.add(e.getToken().getText());
		}
<<<<<<< HEAD
		Expression body = visit(ctx.block);
		return new AnonFunctionDeclExpr(params,body);
=======
		Expresion body = visit(ctx.block);
		return new FunctionDeclExpr(params,body);
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
	}
	@Override
	public Expression visitFunctionDeclaration(FeatherweightJavaScriptParser.FunctionDeclarationContext ctx) {
		String name = ctx.getSymbol().getText();
		List<Expression> stmts = ctx.idlists;
		List<String> params = new ArrayList<String>();
		for(Expression e : stmts){
			params.add(e.getSymbol().getText());
		}
<<<<<<< HEAD
		Expression body = visit(ctx.block);
=======
		Expresion body = visit(ctx.block);
>>>>>>> 5d1bab785664ec6629fa9291202ea8bd79d6f5db
		return new FunctionDeclExpr(name,params,body);
	}

}
