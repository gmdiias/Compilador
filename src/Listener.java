
public class Listener extends GraceBaseListener {
	
	@Override 
	public void enterDecVar(GraceParser.DecVarContext ctx) {
		System.out.println("Entrei");
	}
	
	@Override 
	public void exitDecVar(GraceParser.DecVarContext ctx) {
		System.out.println(ctx.getText());
		
		System.out.println(ctx.getRuleContext());
		System.out.println(ctx.getRuleIndex());
		
		System.out.println(ctx.toStringTree());
		
		System.out.println(ctx.getChild(1).getChild(0).getChild(0).getChild(0));
	}
}
