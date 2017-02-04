
public class AiSearchNode {
	private String[][] state;
	private int alphaBeta;
	private int xScore;
	private int oScore;
	/**
	 * 
	 * @param state
	 * @param xScore
	 * @param oScore
	 */
	public AiSearchNode(String[][] state, int xScore, int oScore){
		this.state = new String[9][9];
		this.state= state;
		this.xScore = xScore;
		this.oScore = oScore;	
		this.alphaBeta = 0;
	}
	public void setAlphaBeta(int alphaBeta){
		this.alphaBeta = alphaBeta;
	}
	public int getAlphaBeta(){
		return this.alphaBeta;
	}
	public String[][] getState(){
		return this.state;
	}
	public int getxScore(){
		return this.xScore;
	}
	public int getoScore(){
		return this.oScore;
	}
	public boolean isTerminal(){
		for(int i = 0; i< this.state.length; i++){
			if(this.state[8][i].equals("*")){
				return false;
			}
		}
		return true;
	}
	public void showBoard(){
		System.out.println("Current Board State");
		System.out.println("'x' score is: " + this.xScore + " || 'o' score is: " + this.oScore);
		for(int i = 8; i>=0; i--){
			System.out.println();
			for(int j = 0; j < this.state.length; j++){
				System.out.print(this.state[i][j] + " ");
			}
		}
		System.out.println();
	}

}
