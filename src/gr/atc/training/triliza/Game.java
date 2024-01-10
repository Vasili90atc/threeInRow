package gr.atc.training.triliza;
import java.util.Scanner;

public class Game {
	int[] positions = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int wrap = 3;
	Scanner sc;

	public Game() {
		sc = new Scanner(System.in);
		System.out.println("Welcome to the three-in-a-row game!");
		print_board();
	}

	public void gameOn() {

		int winner = 0;
		int moves_made = 0;

		while (true) {
			// Player 1 moves
			playerMakesMove(1);
			moves_made++;
			winner = checkForWinner();
			print_board();
			if (winner != 0 || moves_made == positions.length)
				break;

			// Player 2 moves
			playerMakesMove(2);
			moves_made++;
			winner = checkForWinner();
			print_board();
			if (winner != 0 || moves_made == positions.length)
				break;
		}
		if (winner == 0) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Player " + winner + " won!");
		}
		sc.close();
	}

	private int checkForWinner() {
		// Check in column
		for (int j = 0; j < 3; j++) {
			if (positions[j] == positions[j+3] && positions[j+3] == positions[j+6] ) {
				// Winner found
				return positions[j];
			}
		}
		// Check in row
		for (int i = 0; i < 3; i=i+wrap) {
			if (positions[i] == positions[i+1] && positions[i+1] == positions[i+2] ) {
				// Winner found
				return positions[i];
			}
		}
		// Check central diagonal
		if (positions[0] == positions[4] && positions[4] == positions[8]) {
			// Winner found
			return positions[0];
		}
		// Check secondary diagonal
		if (positions[2] == positions[4] && positions[4] == positions[6]) {
			// Winner found
			return positions[0];
		}
		return 0;
	}

	private void playerMakesMove(int playerId) {
		System.out.println("Enter your move, player " + playerId);
		int move = getPlayerMove();
		positions[move] = playerId;
	}

	private int getPlayerMove() {
		int move = sc.nextInt() -1;
		while (move < 0 || move >= positions.length || positions[move] != 0) {
			if (move < 0 || move >= positions.length) {
				System.out.println("Your move must be in the board.");
			} else if (positions[move] != 0) {
				System.out.println("Your move must be on an empty square.");
			}
			move = sc.nextInt();
		}
		return move;
	}

	private void print_board() {
		int elements_in_row = 0;
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] == 0)
				System.out.print(i+1);
			else if (positions[i] == 1)
				System.out.print("O");
			else if (positions[i] == 2)
				System.out.print("X");

			elements_in_row++;
			if (elements_in_row == wrap) {
				System.out.println();
				elements_in_row = 0;
			} else {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
}