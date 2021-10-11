package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Model.Ant;
import Model.Board;

public class AppMainFrame extends JFrame {

	private static final long serialVersionUID = -6241584551658525365L;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 800;

	private BoardPanel boardPanel;

	private Board universe;
	private Ant ant;

	private AppMainFrame appMainFrame;

	/**
	 * Construct component here
	 */
	public AppMainFrame(Board universe, Color antColor, Ant ant) {

		this.ant = ant;
		this.universe = universe;
		boardPanel = new BoardPanel(this, universe, antColor);

		setTitle("Langton Ant Simulation");

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - FRAME_WIDTH / 2, screenDimension.height / 2 - FRAME_HEIGHT / 2);

		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.add(boardPanel);

		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// for program termination when closing frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
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

}
