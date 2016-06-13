import java.util.*;

public class SymbolTable {

    /**
     *  Create a new global scope object.  Initialize the scope 
     *  stack so this new global scope entry is the current scope,
     *  i.e., this global scope entry must be the top entry of the scope 
     *  stack.  Also, note that from now on, this global scope will always be 
     *  at the bottom of the stack.  In this case there is only one entry 
     *  on the scope stack.  
     */
	GlobalEntry globalEntry = new GlobalEntry();
	ArrayList<ScopeEntry> stack = new ArrayList<ScopeEntry>();
	
    public SymbolTable() {
    	stack.add(globalEntry);
    }

    /**  
     *  Insert a binding for parameter 'symTabEntry' in the current scope, 
     *  i.e., in the top entry of the scope stack.  
     *  No ScopeEntry is pushed onto or popped off the scope stack 
     *  during this operation.  This method only adds a binding for 
     *  'symTabEntry'.  
     */
    public boolean insertBinding(Entry symTabEntry) {
    	if (getCurrentScope().addBinding(symTabEntry.name(), symTabEntry)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**  
     *  Return a String representing the current scope, 
     *  i.e., call toString() on the top entry.
     */
    public String currentScope() {
    	return getCurrentScope().toString();
    }
    public ScopeEntry getCurrentScope() {
    	return (ScopeEntry) stack.get(stackSize());
    }
    
    public int stackSize() {
    	return stack.size() - 1;
    }

    /**  
     *  Lookup 'name' in each ScopeEntry on the stack until found, 
     *  starting at the top (the current scope) and ending with the 
     *  global scope (the entry at the bottom of the stack). 
     *  Return null if not found.
     *
     *  This is done to handle nested scopes, i.e., so the proper binding 
     *  in the nearest enclosing scope is returned in cases when the same 
     *  name denotes two different entities.  
     */
    public Entry lookup(String name) {
    	for (int i = stackSize(); i >= 0; i--) {
    		if (stack.get(i).name().equals(name)) {
    			return stack.get(i);
    		}
    		if (stack.get(i).lookup(name) != null) {
    			return stack.get(i);
    		}
    	}
    	return null;
    }

    /**  
     *  Look up the qualified name 'name1.name2'
     *  
     *  To do this, first look up 'name1' as in the above 'lookup' method.  
     *  If found, make sure it is an instance of ClassEntry.
     *  If not a ClassEntry (or null), return null. 
     *  If it is, then look up name2 in this ClassEntry. 
     *  
     *  Return null if 'name1.name2' is not found.
     */
    public Entry lookup(String name1, String name2) {
    	if (lookup(name1) != null) {
    		if ( lookup(name1) instanceof ClassEntry ) {
    			if ( ((ClassEntry) lookup(name1)).lookup(name2) != null ) {
    				return ((ClassEntry) lookup(name1)).lookup(name2);
    			}
    		}
    	}
    	return null;
    }

    /**  
     *  Return the MethodEntry instance nearest the top of the scope stack. 
     *  Return null if no such object is found in the scope stack.
     */
    public MethodEntry enclosingMethod() {
    	for (int i = stackSize(); i >= 0; i--) {
    		if (stack.get(i) instanceof MethodEntry) {
    			return (MethodEntry) stack.get(i);
    		}
    	}
    	return null;
    }

    /**  
     *  Make the parameter 'scopeEntry' the current scope, i.e., push 
     *  'scopeEntry' onto the scope stack.
     *
     *  Return true if the operation is successful, otherwise return false.
     */
    public boolean enterScope(ScopeEntry scopeEntry) {
    	stack.add(globalEntry);
    	return true;
    }

    /**  
     *  Leave the current scope by popping the current scope entry 
     *  (the top entry) off the scope stack.
     *  Return the ScopeEntry that is being popped off the stack.
     *
     *  Return null if there is an attempt to leave the global scope, 
     *  that is, do not allow the bottom entry (the global scope) 
     *  to be popped off the stack.  Thus, in such cases, return 
     *  null and do not leave the global scope.  
     */
    public ScopeEntry leaveScope() {
    	if (getCurrentScope() instanceof GlobalEntry) {
    		return null;
    	}
    	ScopeEntry entry = stack.get(stackSize());
    	stack.remove(stackSize());
    	return entry;
    }

    /**  
     *  Create a new instance of 'BlockEntry' and make it the current scope, 
     *  i.e., push the newly created 'BlockEntry' onto the top of the 
     *  scope stack.
     *
     *  Return true if the operation is successful, otherwise return false.
     */
    public boolean enterNewBlock() {
    	BlockEntry new_block = new BlockEntry();
    	if ( stack.add(new_block) ) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**  
     *  Return a String representation of the global scope entry 
     *  (the entry at the bottom of scope stack)
     *  i.e., call toString() on the bottom entry of the stack.
     */
    public String toString() {
    	return stack.get(0).toString();    
    }
}              // End of class SymbolTable            
