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
        return VarExpr(ctx.IDENTIFIER().getSymbol().getText());
    }

    public Expression visitVariableDeclaration(FeatherweightJavaScriptParser.VariableDeclarationContext ctx) {
        return VarDeclExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitAssignmentStatement(FeatherweightJavaScriptParser.AssignmentStatementContext ctx) {
        return AssignExpr(ctx.IDENTIFIER().getSymbol().getText(), ctx.expr());
    }

    public Expression visitFunctionCall(FeatherweightJavaScriptParser.FunctionCallContext ctx) {
        return FunctionAppExpr(ctx.expr(0), visit(ctx.arglist()));
    }

    public List<Expression> visitArglist(FeatherweightJavaScriptParser.ArglistContext ctx) {
        return ctx.expr().stream().map(visit).collect(Collectors.toList());
    }
}
