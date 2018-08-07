package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(val);
        return new ValueExpr(val);
    }

    @Override
    public Expression visitInt(FeatherweightJavaScriptParser.IntContext ctx) {
        int val = Integer.valueOf(ctx.INT().getText());
        return new ValueExpr(new IntVal(val));
    }

    public Expression visitBoolean(FeatherweightJavaScriptParser.BooleanContext ctx) {
        Boolean bol = Boolean.valueOf(ctx.BOOLEAN().getText());
        return new ValueExpr(new BoolVal(bol));
    }

    public Expression visitNull(FeatherweightJavaScriptParser.NullContext ctx) {
        Null nul = Boolean.valueOf(ctx.NULL().getText());
        return new ValueExpr(new NullVal(nul));
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
        return new VarDeclExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitAssignmentStatement(FeatherweightJavaScriptParser.AssignmentStatementContext ctx) {
        return new AssignExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitFunctionCall(FeatherweightJavaScriptParser.FunctionCallContext ctx) {
        return new FunctionAppExpr(ctx.expr(0), visit(ctx.arglist()));
    }

    public List<Expression> visitArglist(FeatherweightJavaScriptParser.ArglistContext ctx) {
        return ctx.expr().stream().map(visit).collect(Collectors.toList());
    }
	@Override
	public Expresion visitMulDivMod(ExprParser.MulDivModContext ctx) {
		Expresion left = visit(ctx.expr(0));
		Expresion right = visit(ctx.expr(1));
		int op = ctx.op.getType();
		if (op == ExprParser.MULTIPLY)
			return new BinOpExpr(Op.MULTIPLY,left, right);
		else if (op == ExprParser.MOD)
			return new BinOpExpr(Op.MOD,left, right);
		else
			return new BinOpExpr(Op.DIVIDE,left, right);
	}
	@Override
	public Expresion visitComparisons(ExprParser.ComparisonsContext ctx) {
		Expresion left = visit(ctx.expr(0));
		Expresion right = visit(ctx.expr(1));
		int op = ctx.op.getType();
		if (op == ExprParser.GT)
			return new BinOpExpr(Op.GT,left, right);
		else if (ctx.op.getType() == ExprParser.GE)
			return new BinOpExpr(Op.GE,left, right);
		else if (op == ExprParser.LT)
			return new BinOpExpr(Op.LT,left, right);
		else if (op == ExprParser.LE)
			return new BinOpExpr(Op.LE,left, right);
		else 
			return new BinOpExpr(Op.EQ,left, right);
	}
	@Override
	public Expresion visitAddSub(ExprParser.AddSubContext ctx) {
		Expresion left = visit(ctx.expr(0));
		Expresion right = visit(ctx.expr(1));
		int op = ctx.op.getType();
		if (op == ExprParser.ADD)
			return new BinOpExpr(Op.ADD,left, right);
		else
			return new BinOpExpr(Op.SUBTRACT,left, right);
	}
	@Override
	public Expresion visitAnonFunctionDeclaration(ExprParser.FunctionDeclarationContext ctx) {
		List<Expression> stmts = ctx.idlists?;
		List<String> params = new ArrayList<String>();
		for(Expression e : stmts){
			params.add(e.getToken().getText());
		}
		Expresion body = visit(ctx.block);
		return new FunctionDeclExpr(params,body);
	}
	@Override
	public Expresion visitFunctionDeclaration(ExprParser.FunctionDeclarationContext ctx) {
		String name = ctx.getSymbol().getText();
		List<Expression> stmts = ctx.idlists?;
		List<String> params = new ArrayList<String>();
		for(Expression e : stmts){
			params.add(e.getSymbol().getText());
		}
		Expresion body = visit(ctx.block);
		return new FunctionDeclExpr(name,params,body);
	}

}
