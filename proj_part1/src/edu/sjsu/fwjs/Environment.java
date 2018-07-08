package edu.sjsu.fwjs;

import java.util.Map;

import javax.management.RuntimeErrorException;

import java.util.HashMap;

public class Environment {
	private Map<String,Value> env = new HashMap<String,Value>();
	private Environment outerEnv;

	/**
	 * Constructor for global environment
	 */
	public Environment() {}

	/**
	 * Constructor for local environment of a function
	 */
	public Environment(Environment outerEnv) {
		this.outerEnv = outerEnv;
	}

	/**
	 * Handles the logic of resolving a variable.
	 * If the variable name is in the current scope, it is returned.
	 * Otherwise, search for the variable in the outer scope.
	 * If we are at the outermost scope (AKA the global scope)
	 * null is returned (similar to how JS returns undefined.
	 */
    public Value resolveVar(String varName) {
<<<<<<< HEAD
    	if (env.containsKey(varName)){
    		return env.get(varName);
    	}else if(outerEnv != null) {
    		return outerEnv.resolveVar(varName);
    	}else
    		return new NullVal();
=======
        Environment scope = search(varName, this);
        if(scope == null)
        	return new NullVal();
        return scope.env.get(varName);
>>>>>>> branch 'master' of https://github.com/holymushy/Featherweight-JavaScript.git
    }

	/**
	 * Used for updating existing variables.
	 * If a variable has not been defined previously in the current scope,
	 * or any of the function's outer scopes, the var is stored in the global scope.
	 */
    public void updateVar(String key, Value v) {
    	if (env.containsKey(key)){
    		env.put(key, v);
    	}else if (outerEnv != null) {
    		outerEnv.updateVar(key, v);
    	}else
    		env.put(key, v);
    }
<<<<<<< HEAD

	/**
	 * Creates a new variable in the local scope.
	 * If the variable has been defined in the current scope previously,
	 * a RuntimeException is thrown.
	 */
    public void createVar(String key, Value v) {
    	if(env.containsKey(key)) {
    		throw new RuntimeException("Previous Define Variable");
=======
 
    /**
     * Creates a new variable in the local scope.
     * If the variable has been defined in the current scope previously,
     * a RuntimeException is thrown.
     */
    public void createVar(String key, Value v) throws RuntimeException {
    	Environment scope = search(key, this);
        if(scope.env.containsKey(key)) {
            throw new RuntimeException("Previous Define Variable");
        }else {
            env.put(key, v);
        }
    }
    
    /**
     * Searches nested scopes for a given var name, moving upward
     * from local scope. If the var isn't found anywhere, returns
     * null.
     */
    private Environment search(String var, Environment scope) {
    	if(scope == null) {
    		return null;
    	}
    	if(scope.env==null || !(scope.env.containsKey(var))) {
    		return search(var, scope.outerEnv);
>>>>>>> branch 'master' of https://github.com/holymushy/Featherweight-JavaScript.git
    	}else {
        	env.put(key, v);
    	}
    }
}
