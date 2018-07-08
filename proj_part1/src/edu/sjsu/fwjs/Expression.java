package edu.sjsu.fwjs;

ipackage edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

/**
 * FWJS expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression in the context of the specified environment.
     */
    public Value evaluate(Environment env);
}

// NOTE: Using package access so that all implementations of Expression
// can be included in the same file.

/**
 * FWJS constants.
 */
class ValueExpr implements Expression {
    private Value val;
    public ValueExpr(Value v) {
        this.val = v;
    }
    public Value evaluate(Environment env) {
        return this.val;
    }
}

/**
 * Expressions that are a FWJS variable.
 */
class VarExpr implements Expression {
    private String varName;
    public VarExpr(String varName) {
        this.varName = varName;
    }
    public Value evaluate(Environment env) {
        return env.resolveVar(varName);
    }
}

/**
 * A print expression.
 */
class PrintExpr implements Expression {
    private Expression exp;
    public PrintExpr(Expression exp) {
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        Value v = exp.evaluate(env);
        System.out.println(v.toString());
        return v;
    }
}
/**
 * Binary operators (+, -, *, etc).
 * Currently only numbers are supported.
 */
class BinOpExpr implements Expression {
    private Op op;
    private Expression e1;
    private Expression e2;
    public BinOpExpr(Op op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @SuppressWarnings("incomplete-switch")
    public Value evaluate(Environment env) {
        // YOUR CODE HERE
    	Value v1 = e1.evaluate(env), v2 = e2.evaluate(env);
    	int x, y;
    	if((new IntVal(0)).getClass().equals(v1.getClass()) && (new IntVal(0)).getClass().equals(v2.getClass())) {
    		x = ((IntVal)v1).toInt();
    		y = ((IntVal)v2).toInt();
    	}else {
    		return null;
    	}
        if(op.equals(Op.ADD)) {
        	return new IntVal(x + y);
        }else if(op.equals(Op.DIVIDE)) {
        	return new IntVal(x / y);
        }else if(op.equals(Op.EQ)) {
        	return new BoolVal(x == y);
        }else if(op.equals(Op.GE)) {
        	return new BoolVal(x >= y);
        }else if(op.equals(Op.GT)) {
        	return new BoolVal(x > y);
        }else if(op.equals(Op.LE)) {
        	return new BoolVal(x <= y);
        }else if(op.equals(Op.LT)) {
        	return new BoolVal(x < y);
        }else if(op.equals(Op.MOD)) {
        	return new IntVal(x % y);
        }else if(op.equals(Op.MULTIPLY)) {
        	return new IntVal(x * y);
        }else if(op.equals(Op.SUBTRACT)) {
        	return new IntVal(x - y);
        }else {return null;}
    }
}

/**
 * If-then-else expressions.
 * Unlike JS, if expressions return a value.
 */
class IfExpr implements Expression {
    private Expression cond;
    private Expression thn;
    private Expression els;
    public IfExpr(Expression cond, Expression thn, Expression els) {
        this.cond = cond;
        this.thn = thn;
        this.els = els;
    }
    public Value evaluate(Environment env) {
        if(cond == null) {
        	return null;
        }
        Value v = cond.evaluate(env);
        if((new BoolVal(false)).getClass().equals(v.getClass())) {
        	if(((BoolVal)v).toBoolean()) {
        		return thn.evaluate(env);
        	}else {
        		return els.evaluate(env);
        	}
        }
        return null;
    }
}

/**
 * While statements (treated as expressions in FWJS, unlike JS).
 */
class WhileExpr implements Expression {
    private Expression cond;
    private Expression body;
    public WhileExpr(Expression cond, Expression body) {
        this.cond = cond;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        while(true) {
            Value v;
            v = cond.evaluate(env);
            if(!(v instanceof BoolVal))
                throw new RuntimeException("While condition did not evaluate to a boolean");
            else if (((BoolVal)v).toBoolean()){
                body.evaluate(env);
            } else {
                return new NullVal();
            }
        }
    }
}

/**
 * Sequence expressions (i.e. 2 back-to-back expressions).
 */
class SeqExpr implements Expression {
    private Expression e1;
    private Expression e2;
    public SeqExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public Value evaluate(Environment env) {
        if(e1 == null) {
        	return null;
        }
        e1.evaluate(env);
        if(e2 == null) {
        	return null;
        }
        return e2.evaluate(env);
    }
}

/**
 * Declaring a variable in the local scope.
 */
class VarDeclExpr implements Expression {
    private String varName;
    private Expression exp;
    public VarDeclExpr(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
    	if(exp == null) {
    		return null;
    	}
    	Value v;
    	try {
	    	v = exp.evaluate(env);
    	}catch(RuntimeException e) {
    		System.out.print(e.toString());
    	}
        env.createVar(varName, v);
        return v;
    }
}

/**
 * Updating an existing variable.
 * If the variable is not set already, it is added
 * to the global scope.
 */
class AssignExpr implements Expression {
    private String varName;
    private Expression e;
    public AssignExpr(String varName, Expression e) {
        this.varName = varName;
        this.e = e;
    }
    public Value evaluate(Environment env) {
    	if(e == null) {
    		return null;
    	}
    	Value v = e.evaluate(env);
        env.updateVar(varName, v);
        return v;
    }
}

/**
 * A function declaration, which evaluates to a closure.
 */
class FunctionDeclExpr implements Expression {
    private List<String> params;
    private Expression body;
    public FunctionDeclExpr(List<String> params, Expression body) {
        this.params = params;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        return new ClosureVal(params, body, env);
    }
}

/**
 * Function application.
 */
class FunctionAppExpr implements Expression {
    private Expression f;
    private List<Expression> args;
    public FunctionAppExpr(Expression f, List<Expression> args) {
        this.f = f;
        this.args = args;
    }
    public Value evaluate(Environment env) {
        List<Value> argvals = args.stream().map(arg -> arg.evaluate(env));
        Value maybeFunc = f.evaluate(env);
        ClosureVal func;
        try {
            func = (ClosureVal) maybeFunc;
        } catch (ClassCastException e) {
            throw new RuntimeErrorException(e, "The expression, \"" + this.f.toString() + "\", did not evaluate to a closure.")
        }
        
        return func.apply(argvals);
    }
}
