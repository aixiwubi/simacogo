import java.util.ArrayList;
/*
 * Successor used by AI to return child state (Search Node)
 */
public class SearchNodeSuccessor{
	public SearchNodeSuccessor(){

	}
	/**
	 * get the state after a move
	 * @param node
	 * @param player
	 * @param index
	 * @return
	 */
	private AiSearchNode makeMove(AiSearchNode node, String player, int index){
		//get a game board
		SimacogoBoard board = new SimacogoBoard();
		//update the board to current state
		board.updateBoard(node.getState());
		board.updateOScore(node.getoScore());
		board.updateXScore(node.getxScore());
		// do a move 
		if(board.makeMove(player, index)){
			//return the state wrapped in a node
			return board.getCurrentBoardState();
		}

		return null;
	}

	/**
	 * return all possible states
	 * @param node
	 * @param player
	 * @return
	 */
	public ArrayList<AiSearchNode> successor(AiSearchNode node, String player){
		ArrayList<AiSearchNode> nodes = new ArrayList<AiSearchNode>();
		//try moves on all columns
		for(int i = 0; i < 9 ; i++){
			AiSearchNode child = makeMove(node,player,i);
			if(child!=null){
				//if the return is a node (means there is a legal move) the add to return nodes
				nodes.add(child);
			}
		}
		return nodes;
	}
	

}
