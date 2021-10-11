package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Board;

public class BoardPanel extends JPanel {

	private Board universe;
	private AppMainFrame mainFrame;
	private List<List<AbstractButton>> buttons;
	private Color antColor;

	public BoardPanel(AppMainFrame appMainFrame, Board universe, Color antColor) {
		this.antColor = antColor;
		this.universe = universe;
		setBackground(Color.WHITE);

		this.mainFrame = appMainFrame;

		setBackground(Color.WHITE);
		ButtonGroup group = new ButtonGroup();

		buttons = new ArrayList<>();

		JPanel btnContainerPanel = new JPanel();
		btnContainerPanel.setLayout(new GridLayout(universe.getSize(), universe.getSize(), 0, 0));

		for (int row = 0; row < universe.getSize(); ++row) {
			buttons.add(new ArrayList<AbstractButton>());

			for (int col = 0; col < universe.getSize(); ++col) {
				JButton currentButton = new JButton();
				buttons.get(row).add(currentButton);

				if (universe.getCellColor(row, col) == Board.Color.BLACK) {
					buttons.get(row).get(col).setBackground(Color.BLACK);
				}

				currentButton.setBorder(BorderFactory.createRaisedBevelBorder());
				btnContainerPanel.add(currentButton);
				group.add(currentButton);
			}
		}

		setLayout(new BorderLayout());
		add(btnContainerPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 800));
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(800, 800));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// we draw the cells
		for (int row = 0; row < universe.getSize(); row++) {
			for (int col = 0; col < universe.getSize(); col++) {
				if (universe.getCellColor(row, col) == Board.Color.BLACK) {
					buttons.get(row).get(col).setBackground(antColor);
				}
			}
		}
	}

}
