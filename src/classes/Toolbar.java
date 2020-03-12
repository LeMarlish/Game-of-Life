package classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import others.MyButtonListener;
import others.Status;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {

	private JLabel currentSpeed;
	private JLabel generation;
	private JButton slowDown;
	private JButton speedUp;
	private JButton run;
	private JButton pause;
	private JButton clear;

	private int speed = 2;
	private int gen = 0;
	
	private MyButtonListener buttonListener;

	public Toolbar() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		currentSpeed = new JLabel("Normal");
		generation = new JLabel("Generation " + gen);
		slowDown = new JButton("<<");
		speedUp = new JButton(">>");

		run = new JButton("RUN");
		pause = new JButton("PAUSE");
		clear = new JButton("CLEAR");
		run.setForeground(Color.BLACK); // ----
		run.setBackground(Color.GREEN);
		pause.setBackground(Color.RED);
		pause.setForeground(Color.WHITE); // Styles
		pause.setFocusPainted(false);
		run.setFocusPainted(false);
		clear.setFocusPainted(false); // ----

		clear.setBackground(Color.CYAN);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.insets = new Insets(0, 5, 0, 0);
		add(generation, c);

		// Positions
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 20, 0, 0);
		add(slowDown, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		add(currentSpeed, c);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.1;
		c.insets = new Insets(0, 20, 0, 20);
		add(speedUp, c);
		c.gridy = 2;
		c.gridx = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipadx = 20;
		add(run, c);
		c.gridy = 2;
		c.insets = new Insets(100, 0, 0, 0);
		add(pause, c);
		c.insets = new Insets(200, 0, 0, 0);
		c.gridy = 2;
		add(clear, c);

		run.addActionListener(this);
		pause.addActionListener(this);
		clear.addActionListener(this);
		speedUp.addActionListener(this);
		slowDown.addActionListener(this);

	}

	public void updateGeneration() {
		gen++;
		generation.setText("Generation " + gen);
	}
	public void updateGeneration(int zero) {
		generation.setText("Generation " + 0);
		gen = 0;
		
	}

	public void updateSpeedText(int n) {
		speed += n;
		switch (speed) {
		case 0:
			currentSpeed.setText("Slowest");
			break;
		case 1:
			currentSpeed.setText("Slow");
			break;
		case 2:
			currentSpeed.setText("Normal");
			break;
		case 3:
			currentSpeed.setText("Fast");
			break;
		case 4:
			currentSpeed.setText("Fastest");
			break;
		default:
			if(speed < 0) {
				speed = 0;
			} else if (speed > 4) {
				speed = 4;
			}
		}

	}

	public void setButtonListener(MyButtonListener listener) {
		this.buttonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button != null) {
			if (button == run) {
				buttonListener.setStatus(Status.RUNNING);
			} else if (button == pause) {
				buttonListener.setStatus(Status.PAUSED);
			} else if (button == clear) {
				buttonListener.clearMap();
			} else if (button == speedUp) {
				updateSpeedText(1);
				buttonListener.modifiedSpeed(1);
			} else if (button == slowDown) {
				buttonListener.modifiedSpeed(-1);
				updateSpeedText(-1);
			}
		}
	}
}
