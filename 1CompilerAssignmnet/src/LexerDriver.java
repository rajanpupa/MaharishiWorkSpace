
import java.io.*;
import java.util.*;
import node.*;
import lexer.*;

public class LexerDriver          
{

    private static Token next_token(Lexer lex) {
	try {
	    return lex.next();
	}
	catch (LexerException e) {
	    System.out.println(e.getMessage());
	    return null;
	}
	catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	    return null;
	}
    }

    private static String getClassName(Class c) {
	StringTokenizer fullName = new StringTokenizer(c.getName(), ".");
	String className = "";
	while (fullName.hasMoreTokens()) {
	    className = fullName.nextToken();
	}
	return className;
    }

    public static void main(String[] args)
    {

    	System.out.println("File : " + args[0]);
	try {
	    System.out.println("Starting Lexer");
	    Lexer lex = new Lexer( 
		new PushbackReader( new InputStreamReader(
				        new FileInputStream(args[0])),
					  1024));

	    Token t = next_token(lex);
	    int tokenCount = 0;
	    while (!(t instanceof EOF)) {
		
		if (t == null) {
		    // Skip Unknown Token
		} else if (t instanceof TWhiteSpace) {
		    System.out.println("[" + t.getLine() + ", " + t.getPos() 
				       + "]  \tTWhiteSpace: \"" + t + "\"");
		} else {
		    System.out.println("[" + t.getLine() + ", " + t.getPos() 
				       + "]  \t" + getClassName(t.getClass())
				       + ": " + t);
		    ++tokenCount;
		}
		t = next_token(lex);
	    }
	    System.out.println("Token count="+tokenCount);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	System.out.println("End of Scanner test");

    }

}              // End of LexerDriver           
