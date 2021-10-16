import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Model.Ant;
import Model.Board;
import Model.Board.Color;
import Model.Direction;
import View.AppMainFrame;
import View.BoardPanel;
import View.StartMainFrame;

public class Driver {

	private Board universe;
	private Ant ant;

	private AppMainFrame appMainFrame;
	private BoardPanel boardPanel;

	public Driver() {
//		universe = new Board(100);
//		universe.setCellColor(50, 50, Color.WHITE);
//
//		ant = new Ant(50, 50, Direction.SOUTH);
//
//		appMainFrame = new AppMainFrame(universe);
//		appMainFrame.setVisible(true);
//		boardPanel = appMainFrame.getBoardPanel();
	}

	public void runSimulation(int maxSteps, long delay, long period) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// we stop the evolution when max steps for the ant is reached
				if (!(ant.steps < maxSteps && ant.isInWorld(universe))) {
					timer.cancel();
				} else {
					// we repaint the panel
					SwingUtilities.invokeLater(() -> {
						boardPanel.repaint();
					});

					// the ant makes a step in the world according to the rules of the Langton's Ant
					ant.step(universe);
				}
			}
		}, delay, period);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			StartMainFrame startMainFrame = new StartMainFrame();
			startMainFrame.setVisible(true);
		});

	}
}
