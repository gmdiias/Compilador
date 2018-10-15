import java.io.File;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class HelloRunner {
	public static void main(String[] args) throws Exception {

		/*ANTLRInputStream input = new ANTLRInputStream(System.in);

		HelloLexer lexer = new HelloLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		HelloParser parser = new HelloParser(tokens);
		ParseTree tree = parser.if_token(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree*/
		    
	    ANTLRInputStream input = new ANTLRInputStream(System.in);
		
	    FGMLexer lexer = new FGMLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		FGMParser parser = new FGMParser(tokens);
		
		ParseTree tree = parser.fgm();
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}