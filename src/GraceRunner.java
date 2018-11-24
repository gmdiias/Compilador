import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class GraceRunner {
	public static void main(String[] args) throws Exception {
		 
	    ANTLRInputStream input = new ANTLRInputStream("var a: string;"
	    											+ "var b, c = b  + a: int; ");
	    
	    GraceLexer lexer = new GraceLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		GraceParser parser = new GraceParser(tokens);
		
		ParseTree tree = parser.grace();
		
		Listener listener = new Listener();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		
		walker.walk(listener, tree);
		
		//System.out.println("Arvore: "+ tree.toStringTree(parser));
		
	}
}