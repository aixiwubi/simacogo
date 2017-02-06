/*
 * A Sigmacogo game board.
 * stores the board state, x (black) Score and o(white) Score, * means empty
 *  
 */
public class SimacogoBoard {
	private String[][] board;
	private int xScore;
	private int oScore;
	/**
	 * constructor
	 */
	public SimacogoBoard(){
		this.board = new String[9][9];
		this.xScore = 0;
		this.oScore = 0;
	}
	/**
	 * update current board state, used by SearchNodeSuccessor
	 * @param board 
	 */
	public void updateBoard(String[][] board){
		for(int i = 0; i< board.length; i++){
			for(int j = 0; j< board.length; j++){

				this.board[i][j] = board[i][j];
			}
		}
	}
	/**
	 * update xscore used by SearchNodeSuccessor
	 * @param xScore
	 */
	public void updateXScore(int xScore){
		this.xScore = xScore;
	}
	/**
	 * update oscore used by SearchNodeSuccessor
	 * @param oScore
	 */
	public void updateOScore(int oScore){
		this.oScore = oScore;
	}
	/**
	 * check if the game is over
	 * @return true if over
	 */
	public boolean isGameOver(){
		for(int i = 0; i< this.board.length; i++){
			if(this.board[8][i].equals("*")){
				return false;
			}
		}
		return true;
	}
	/**
	 * set up game board with '*' in board array
	 */
	public void setUpboard(){
		for(int i = 0; i < this.board.length; i++){
			for(int j = 0; j < this.board.length; j++){
				this.board[i][j] = "*";
			}
		}
	}
	/**
	 * wrap the current state and score into an node object 
	 * used by AI to do search
	 * @return AiSearchNode
	 */
	public AiSearchNode getCurrentBoardState(){
		AiSearchNode sn = new AiSearchNode(this.board, this.xScore, this.oScore);
		return sn;
	}
	/**
	 * print out current board state
	 */
	public void showBoard(){
		System.out.println("Current Board State");
		System.out.println("'x' score is: " + this.xScore + " || 'o' score is: " + this.oScore);
		for(int i = 8; i>=0; i--){
			System.out.println();
			for(int j = 0; j < this.board.length; j++){
				System.out.print(this.board[i][j] + " ");
			}
		}
		System.out.println();
		System.out.println("0 1 2 3 4 5 6 7 8");
	}
	/**
	 * 'o' or 'x' make a move on column index
	 * @param player 'x' or 'o'
	 * @param index from 0 to 8
	 * @return true if is a legal move, false illegal move
	 */
	public boolean makeMove(String player, int index){
		if(index<0||index>8) return false;
		for(int i = 0; i< this.board.length; i++){
			if(this.board[i][index].equals("*")){
				this.board[i][index] = player;
				findConnection(i, index, player);
				return true;
			}
		}
		return false;
	}
	/**
	 * check if coordinate x ,y is valid in board
	 * @param x
	 * @param y
	 * @return true if coordinate exist
	 */
	private boolean isValidXy(int x, int y){
		if((x>=0&&x<=8) && (y>=0&&y<=8)){
			//	System.out.println(x + " , " + y + " is valid");
			return true;
		}
		else{
			//	System.out.println(x + " , " + y + " is not valid");

			return false;
		}
	}
	/**
	 * check if neighbor is the same
	 * @param x
	 * @param y
	 * @param player
	 * @return
	 */
	private boolean isSamePlayer(int x, int y, String player){
		if(this.board[x][y].equals(player)){
			//System.out.println("is same player");
			return true;
		}else{
			//System.out.println("not same player");
			return false;
		}
	}
	/**
	 * check if there is an edge between two coordinates, and update score
	 *  if there is an connection between same player's key.
	 * @param x
	 * @param y
	 * @param player
	 */
	private void findConnection(int x, int y, String player){	

		//check down
		if(isValidXy(x,y+1)){
			if(isSamePlayer(x,y+1,player)){
				if(player.equals("x")){
					xScore += 2;
				}
				else if(player.equals("o")){
					oScore+= 2;
				}
			}
		}
		//check left
		if(isValidXy(x-1,y)){
			if(isSamePlayer(x-1,y,player)){
				if(player.equals("x")){
					xScore += 2;
				}
				else if(player.equals("o")){
					oScore+= 2;
				}
			}
		}
		//check right
		if(isValidXy(x,y-1)){
			if(isSamePlayer(x,y-1,player)){
				if(player.equals("x")){
					xScore += 2;
				}
				else if(player.equals("o")){
					oScore+= 2;
				}
			}
		}
		//check left down
		if(isValidXy(x-1,y-1)){
			if(isSamePlayer(x-1,y-1,player)){
				if(player.equals("x")){
					xScore += 1;
				}
				else if(player.equals("o")){
					oScore+= 1;
				}
			}
		}
		//check right up
		if(isValidXy(x+1,y+1)){
			if(isSamePlayer(x+1,y+1,player)){

				if(player.equals("x")){
					xScore += 1;
				}
				else if(player.equals("o")){
					oScore+= 1;
				}
			}
		}
		//check right down
		if(isValidXy(x+1,y-1)){
			if(isSamePlayer(x+1,y-1,player)){
				if(player.equals("x")){
					xScore += 1;
				}
				else if(player.equals("o")){
					oScore+= 1;
				}
			}
		}
		//check left up
		if(isValidXy(x-1,y+1)){
			if(isSamePlayer(x-1,y+1,player)){
				if(player.equals("x")){
					xScore += 1;
				}
				else if(player.equals("o")){
					oScore+= 1;
				}
			}
		}
	}


	/**
	 * get 'x' or 'o' current score
	 * @param player
	 * @return 'x' or 'o' score
	 */

	public int getScore(String player){
		if(player.equals("x")){
			return this.xScore;
		}
		else if(player.equals("o")){
			return this.oScore;
		}
		else{
			return -1;
		}
	}
	/**
	 * check the last move by a player, 
	 * returns the column index
	 * @param player
	 * @param board
	 * @return
	 */
	public int lastMove(String player,String[][] board){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j< 9; j++){
				if(this.board[i][j]=="*"&& board[i][j] == player){
					return j;
				}
			}
		}
		return -1;
	}
	
}
