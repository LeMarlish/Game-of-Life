package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import classes.Game;
import classes.Toolbar;
import others.MyButtonListener;
import others.Status;

@SuppressWarnings("serial")
public class Launch extends JFrame {

	public static void main(String[] args) {
		// Create window and start game
		Game game = new Game();
		JFrame frame = new JFrame("The Game of Life");
		Toolbar toolbar = new Toolbar();

		frame.setLayout(new BorderLayout());
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
		frame.setSize(1265, 638);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(toolbar, BorderLayout.EAST);
		frame.add(game);

		// Game loop.
		while (true) {

			if (game.getStatus() == Status.RUNNING) {
				toolbar.updateGeneration();
				game.update();
			}
			// Communicate Toolbar with Game.
			toolbar.setButtonListener(new MyButtonListener() {

				@Override
				public void setStatus(Status status) {
					game.setStatus(status);
				}

				@Override
				public void modifiedSpeed(int speed) {
					game.setGameSpeed(speed);
				}

				@Override
				public void clearMap() {
					game.clearMap();
					game.setStatus(Status.PAUSED);
					toolbar.updateGeneration(0);
				}

			});
		}

	}
}
