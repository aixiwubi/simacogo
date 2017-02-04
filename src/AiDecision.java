
public class AiDecision {
	private int alphaBeta;
	private AiSearchNode node;
	public AiDecision(){


	}
	public AiSearchNode getNode(){
		return this.node;
	}
	public int getAlphaBeta(){
		return this.alphaBeta;
	}
	public void setAlphaBeta(int alphaBeta){
		this.alphaBeta = alphaBeta;
	}
	
	public void setNode(AiSearchNode node){
		this.node = node;
	}
	public static AiDecision max(AiDecision a, AiDecision b){
		if(a.getAlphaBeta()>b.getAlphaBeta()) return a;
		else return b;

	}
	public static AiDecision min(AiDecision a, AiDecision b){
		if(a.getAlphaBeta()<b.getAlphaBeta()) return a;
		else return b;

	}

}
