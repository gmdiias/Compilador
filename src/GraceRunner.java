import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class GraceRunner {
	public static void main(String[] args) throws Exception {

		/*ANTLRInputStream input = new ANTLRInputStream(System.in);

		HelloLexer lexer = new HelloLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		HelloParser parser = new HelloParser(tokens);
		ParseTree tree = parser.if_token(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree*/
		    
	    ANTLRInputStream input = new ANTLRInputStream(System.in);
		
	    GraceLexer lexer = new GraceLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		GraceParser parser = new GraceParser(tokens);
		
		ParseTree tree = parser.grace();
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree
	}
}