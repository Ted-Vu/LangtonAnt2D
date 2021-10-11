package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Direction;

/**
 * SettingsDialog initiated when user hits settings in the welcome menu
 * 
 * @author kevin & ted
 */
public class SettingsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 180;

	private JComboBox<String> boardSizeMenu;
	private JComboBox<String> antColorMenu;
	private JComboBox<String> antFacingMenu;

	private JButton applyButton;

	/**
	 * Construct settingsdialog and all of its necessary components
	 * 
	 * @param startGameMainFrame
	 */
	public SettingsDialog(StartMainFrame startMainFrame) {
		String[] boardSize = { "100 x 100 ", " 150 x 150 " };
		String[] boardColour = { "   BLACK   ", "   RED   ", "   GREEN   ", "   BLUE   " };
		String[] antInitialFacing = { "NORTH", "SOUTH", "EAST", "WEST" };

		boardSizeMenu = new JComboBox<String>(boardSize);
		antColorMenu = new JComboBox<String>(boardColour);
		antFacingMenu = new JComboBox<String>(antInitialFacing);

		applyButton = new JButton("APPLY");
		SettingsDialog settingsDialog = this;

		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startMainFrame.setAntColor(getAntColour());
				startMainFrame.setBoardSize(getBoardSize());
				startMainFrame.setInitialFacingDirection(getAntInitialFacingDirection());
				settingsDialog.dispose();
			}

		});

		setTitle("Settings");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		JPanel boardSizePanel = new JPanel();
		boardSizePanel.add(new JLabel("Board Size:"));
		boardSizePanel.add(boardSizeMenu);
		selectionPanel.add(boardSizePanel);
		JPanel numPiecesPanel = new JPanel();
		numPiecesPanel.add(new JLabel("Ant Colour:"));
		numPiecesPanel.add(antColorMenu);
		selectionPanel.add(numPiecesPanel);

		JPanel obstaclePanel = new JPanel();
		obstaclePanel.add(new JLabel("Facing of Ant:"));
		obstaclePanel.add(antFacingMenu);
		selectionPanel.add(obstaclePanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(applyButton);

		add(buttonPanel, BorderLayout.SOUTH);
		add(selectionPanel, BorderLayout.CENTER);

		setModal(true);
		setVisible(true);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Converting human UI to machine value of board size
	 * 
	 * @return boardSize
	 * @see String[] boardSize
	 */
	public java.awt.Color getAntColour() {
		int selectedIndex = antColorMenu.getSelectedIndex();
		// board size can be 9, 11, 13, 15
		if (selectedIndex == 0) {
			return java.awt.Color.BLACK;
		} else if (selectedIndex == 1) {
			return java.awt.Color.RED;
		} else if (selectedIndex == 2) {
			return java.awt.Color.GREEN;
		} else {
			return java.awt.Color.BLUE;
		}
	}

	public int getBoardSize() {
		int selectedIndex = boardSizeMenu.getSelectedIndex();

		if (selectedIndex == 0) {
			return 100;
		} else {
			return 150;
		}
	}

	/**
	 * Converting human UI to machine value of number of piece
	 * 
	 * @return numberOfPiece
	 * @see String[] numPieceMenu
	 */
	public Direction getAntInitialFacingDirection() {
		int selectedIndex = boardSizeMenu.getSelectedIndex();

		if (selectedIndex == 0) {
			return Direction.NORTH;
		} else if (selectedIndex == 1) {
			return Direction.SOUTH;
		} else if (selectedIndex == 2) {
			return Direction.EAST;
		} else {
			return Direction.WEST;
		}
	}
}