/*
 * An intelligent agent to predict move in Simacogo game
 */
public class SimacogoAI {
	// successor returns the possible state after a move
	private SearchNodeSuccessor successor;
	//the number of moves the agent will predict
	private int aiIQ;
	/**
	 * constructor
	 * @param iq
	 */
	public SimacogoAI(int iq){
		this.successor  = new SearchNodeSuccessor();
		this.aiIQ =  iq;
	}
	/**
	 * get next move by  this agent
	 * @param node an AiSearchNode
	 * @return 
	 */
	public AiSearchNode getNextMove(AiSearchNode node){
		
		return alphaBetaPruning(node, aiIQ, Integer.MIN_VALUE, Integer.MAX_VALUE, true).getNode();
	}
	/**
	 * do a minmax search with alpha beta pruning
	 * @param node
	 * @param depth search depth
	 * @param alpha
	 * @param beta
	 * @param maxOrMin 
	 * @return the decision made with a node and a heuristic
	 */
	private AiDecision alphaBetaPruning(AiSearchNode node, int depth, int alpha, int beta, boolean maxOrMin){
		//check if it has reached the depth 0 or if the game is over after the move is made
		if(depth == 0 || node.isTerminal()){
			//return the heuristic for this move. 
			AiDecision decision = new AiDecision();
			decision.setNode(node);
			decision.setAlphaBeta(node.getxScore()-node.getoScore());
			return decision;
		}
		// if it is Max
		if(maxOrMin){
			// temp is used to compare with child heuristic to get the child with the max heuristic
			int temp = Integer.MIN_VALUE;
			
			AiDecision decision = new AiDecision();
			AiDecision returnValue = new AiDecision();
			// initialize the current heuristic
			decision.setAlphaBeta(temp);
			// get child of current state
			for(AiSearchNode child: successor.successor(node, "x")){
				// recursive call on min and get return decision
				returnValue = alphaBetaPruning(child,depth-1,alpha,beta,false);
				// check if the return decision is higher (better) than current decision
				if(returnValue.getAlphaBeta()>temp){
					decision.setNode(child);
				}
				//update current decision
				temp = Integer.max(temp, returnValue.getAlphaBeta());
				// update alpha
				alpha = Integer.max(alpha,temp);
				// stop search if beta is <= alpha
				if(beta<=alpha){
					break;
				}
			}
			
			//set return heuristic and return decision
			decision.setAlphaBeta(temp);
			
			return decision;
		}
		// if it is Min
		else{
			//temp is used to track the min value for Min
			int temp = Integer.MAX_VALUE;
			AiDecision decision = new AiDecision();
			AiDecision returnValue = new AiDecision();
			// initialize current min value
			decision.setAlphaBeta(temp);
			// get child of current state
			for(AiSearchNode child: successor.successor(node,"o")){
				// recursive call on max and get return decision
				returnValue = alphaBetaPruning(child,depth-1,alpha,beta,true);
				// check if the return decision is smaller (better) than current decision
				if(returnValue.getAlphaBeta()<temp){
					//update decision
					decision.setNode(child);
				}
				//update current best heuristic value
				temp = Integer.min(temp, returnValue.getAlphaBeta());
				//update beta
				beta = Integer.min(beta, temp);
				//stop searching if beta is <= alpha
				if(beta<=alpha){
					break;
				}
			}
			//set best heuristic and return decision

			decision.setAlphaBeta(temp);
			return decision;
			
		}

	}
}
