import java.util.Scanner;
/**
 * Simacogo Game
 * Able to play against human and AI
 * @author wendongyang
 *
 */

public class Main {
	/**
	 * check is input is and digit
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?"); 
	}
	
	public static void main(String[] args) {
		//get a scanner to get user input
		Scanner sc = new Scanner(System.in);

		// get an instance of the game
		SimacogoBoard game = new SimacogoBoard();
		int xMove;
		int oMove;
		SimacogoAI ai;
		System.out.println("1 for Player vs Player, 2 for Player vs AI");
		// ask what mode to play
		String mode = sc.nextLine();
		// against AI
		if(mode.equals("2")){
			System.out.println("Select Difficulty: (1-10)");
			// get AI difficulty
			String diff = sc.nextLine();	
			while(!isNumeric(diff)){
				diff = sc.nextLine();
			}
			ai = new SimacogoAI(Integer.parseInt(diff));
			game.setUpboard();
			game.showBoard();
			//get user move
			while(!game.isGameOver()){
				System.out.println("Now is your move (0-8)");
				String nl = sc.nextLine();
				//check if is digit otherwise get user input again
				while(!isNumeric(nl)){
					System.out.println("Please Enter an digit ");
					nl = sc.nextLine();
				}
				oMove = Integer.parseInt(nl);
				//check if can make legal move
				while(!game.makeMove("o", oMove)){
					// if cant make the move the ask for input again
					System.out.println("Now is ' o ' move (0-8)");
					nl = sc.nextLine();
					while(!isNumeric(nl)){
						System.out.println("Please Enter an digit ");
						nl = sc.nextLine();
					}
					oMove = Integer.parseInt(nl);
				}
				game.showBoard();
				//check if game is over 
				if(game.isGameOver()){
					break;
				}
				// AI now will make a move
				System.out.println("Now is AI's move ");
				double startTime = System.currentTimeMillis();
				//get AI's move. getNextMove returns a state, lastMove will return the index of the move made
				xMove = game.lastMove("x", ai.getNextMove(game.getCurrentBoardState()).getState());
				// make a move to the current game
				game.makeMove("x", xMove);
				double endTime = System.currentTimeMillis();
				// print time it took for AI to Make a move
				System.out.println("It took " + (endTime-startTime)/1000 + " secs for AI make a Play");
				game.showBoard();
			}
			System.out.println("The final Score is: "+ "'x' -> " + game.getScore("x") +  " 'o' -> " + game.getScore("o"));

			

		}
		// Player vs Player
		else{
			game.setUpboard();
			game.showBoard();
			String nl ;
			
			while(!game.isGameOver()){
				//get user o 's move and check if it is a digit
				System.out.println("Now is ' o ' move (0-8)");
				nl = sc.nextLine();
				while(!isNumeric(nl)){
					System.out.println("Please Enter an digit ");
					nl = sc.nextLine();
				}
				oMove = Integer.parseInt(nl);
				// check if is an legal move
				while(!game.makeMove("o", oMove)){
					System.out.println("Now is ' o ' move (0-8)");
					nl = sc.nextLine();
					while(!isNumeric(nl)){
						System.out.println("Please Enter an digit ");
						nl = sc.nextLine();
					}
					oMove = Integer.parseInt(nl);
				}
				game.showBoard();
				// check if game is over
				if(game.isGameOver()){
					break;
				}
				
				System.out.println("Now is ' x ' move (0-8)");
				// check if x's input
				nl = sc.nextLine();
				while(!isNumeric(nl)){
					System.out.println("Please Enter an digit ");
					nl = sc.nextLine();
				}
				// check if x made an legal move
				xMove = Integer.parseInt(nl);
				while(!game.makeMove("x", xMove)){
					System.out.println("Now is ' x ' move (0-8)");
					nl = sc.nextLine();
					while(!isNumeric(nl)){
						System.out.println("Please Enter an digit ");
						nl = sc.nextLine();
					}
					xMove = Integer.parseInt(nl);
				}
				game.showBoard();
			}
			System.out.println("The final Score is: "+ "'x' -> " + game.getScore("x") +  " 'o' -> " + game.getScore("o"));
		}
	}

}
