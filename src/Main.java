import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		SimacogoBoard game = new SimacogoBoard();
		int xMove;
		int oMove;
		SimacogoAI ai;
		System.out.println("1 for Player vs Player, 2 for Player vs AI");
		String mode = sc.nextLine();
		if(mode.equals("2")){
			System.out.println("Select Difficulty: (1-10)");
			String diff = sc.nextLine();	
			ai = new SimacogoAI(Integer.parseInt(diff));
			game.setUpboard();
			game.showBoard();
			while(!game.isGameOver()){
				System.out.println("Now is your move (0-8)");
				oMove = Integer.parseInt(sc.nextLine());
				while(!game.makeMove("o", oMove)){
					System.out.println("Now is ' o ' move (0-8)");
					oMove = Integer.parseInt(sc.nextLine());
				}
				game.showBoard();
				if(game.isGameOver()){
					break;
				}
				System.out.println("Now is AI's move ");
				xMove = game.lastMove("x", ai.getNextMove(game.getCurrentBoardState()).getState());
				game.makeMove("x", xMove);
				game.showBoard();
			}
			System.out.println("The final Score is: "+ "'x' -> " + game.getScore("x") +  " 'o' -> " + game.getScore("o"));

			

		}else{
			game.setUpboard();
			game.showBoard();
			while(!game.isGameOver()){
				System.out.println("Now is ' o ' move (0-8)");
				xMove = Integer.parseInt(sc.nextLine());
				while(!game.makeMove("o", xMove)){
					System.out.println("Now is ' o ' move (0-8)");
					xMove = Integer.parseInt(sc.nextLine());
				}
				game.showBoard();
				if(game.isGameOver()){
					break;
				}
				System.out.println("Now is ' x ' move (0-8)");
				oMove = Integer.parseInt(sc.nextLine());
				while(!game.makeMove("x", oMove)){
					System.out.println("Now is ' x ' move (0-8)");
					oMove = Integer.parseInt(sc.nextLine());
				}
				game.showBoard();
			}
			System.out.println("The final Score is: "+ "'x' -> " + game.getScore("x") +  " 'o' -> " + game.getScore("o"));
		}
	}

}
