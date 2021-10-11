package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Model.Ant;
import Model.Board;
import Model.Board.Color;
import Model.Direction;

/**
 * Welcome menu for the game
 * 
 * @author kevin & ted
 */
public class StartMainFrame extends JFrame {

	private static final long serialVersionUID = -8454047695594084099L;
	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 250;

	private java.awt.Color antColor;
	private int boardSize;
	private Direction initialFacingDirection;

	private Board universe;
	private Ant ant;
	private JButton startButton;
	private JButton configBtn;
	private JButton exitButton;

	private StartMainFrame startGameFrame = this; // for exit action to reference

	private SettingsDialog settingsDialog;

	/**
	 * Build components in this frame
	 * 
	 * @see
	 */
	public StartMainFrame() {
		startButton = new JButton("   START SIMULATION   ");
		configBtn = new JButton("SIMULATION SETTINGS");
		exitButton = new JButton("           EXIT           ");

		startButton.setAlignmentX(CENTER_ALIGNMENT);
		configBtn.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);

		setTitle("Langton's Ant Simulation");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel headingPanel = new JPanel();
		headingPanel.add(new JLabel("<html><h1><strong><i>Langton's Ant Simulation</i></strong><hr></h1></html>"));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(configBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(exitButton);

		StartMainFrame startFrame = this;

		// default configs
		boardSize = 100;
		antColor = java.awt.Color.black;
		initialFacingDirection = Direction.SOUTH;
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// default size 100 x 100
				universe = new Board(boardSize);

				// start in the middle
				universe.setCellColor(boardSize / 2, boardSize / 2, Color.WHITE);

				ant = new Ant(boardSize / 2, boardSize / 2, initialFacingDirection);

				AppMainFrame appMainFrame = new AppMainFrame(universe, antColor, ant);
				appMainFrame.setVisible(true);
				startFrame.dispose();
				appMainFrame.runSimulation(15000, 0, 1);
			}

		});
		configBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				settingsDialog = new SettingsDialog(startFrame);
			}

		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGameFrame.dispose();
			}

		});

		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(headingPanel, BorderLayout.NORTH);

		// for program termination when closing frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public void setAntColor(java.awt.Color antColor) {
		this.antColor = antColor;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public void setInitialFacingDirection(Direction initialFacingDirection) {
		this.initialFacingDirection = initialFacingDirection;
	}
	
	public int getBoardSize() {
		return this.boardSize;
	}
}