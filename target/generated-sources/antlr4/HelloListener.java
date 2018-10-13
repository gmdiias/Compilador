// Generated from Hello.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(HelloParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(HelloParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#if_token}.
	 * @param ctx the parse tree
	 */
	void enterIf_token(HelloParser.If_tokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#if_token}.
	 * @param ctx the parse tree
	 */
	void exitIf_token(HelloParser.If_tokenContext ctx);
}