package race;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.View;

/**
 * @author annah
 * class RaceModel describes a logic of moving blocks and all game
 */
public class RaceModel extends Thread{
	private JFrame frame;
	private ArrayList<MovingButton> buttons = new ArrayList<MovingButton>();
	private static final int count = 9;
	private static final int dx = 10;
    private static final int height = 50;
    private static final int width = 100;
    private static final int size = (count + 1)* height;
    private boolean running = false;
    private Random random= new Random();
    

    /**
     * Creation frame and group of buttons
     */
    public RaceModel() {
    	JFrame frame1 = new JFrame("RaceGame");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame1.setPreferredSize(new Dimension(size, size));
	    frame1.setBounds(500, 100, 0, 0);
	    frame1.setLayout(null);
	    
    	this.frame = frame1;
    	
	    buttons = new ArrayList<MovingButton>(count);
	    JButton[] bts= new JButton[count];
	    for (int i = 0; i < count; i++) {
	    	bts[i] = new JButton();
	    	bts[i].setName(String.valueOf(i+1));
	    	bts[i].setText(bts[i].getName());
	    	bts[i].setBackground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
	    	bts[i].setBounds(0, i * height, width, height);
	    }
	    for (int i = 0; i < count; i++){
	        buttons.add(new MovingButton(this, bts[i], String.valueOf(bts[i].getName())));
	        frame1.add(bts[i]);
	    }
	    frame1.pack();
	    frame1.setVisible(true);
	    
    }
	
	/**
	 * @param button - button in game
	 * @return is finished one button or not
	 */
	public boolean isFinished(MovingButton button) {
		if (button.getButton().getX() + button.getButton().getWidth() < size - 10) {
			return false;
		} else {
			this.setRunning(false);
			return true;
		}
		
	}

	/**
	 * moves buttons and restarts game
	 * @param mb - button in game
	 */
	public void move(MovingButton mb){
		if (isFinished(mb)) {
			frame.getContentPane().setBackground(mb.getButton().getBackground());
			View.viewMessageWinner(mb.getName());
			setRunning(false);
			RaceModel.restart();
		}
		mb.getButton().setBounds(mb.getButton().getX() + dx, mb.getButton().getY(), mb.getButton().getWidth(), mb.getButton().getHeight());

		int delay= random.nextInt(100)+10;
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * start of game
	 */
	public synchronized void start() {
		setRunning(true);
		for (MovingButton mb : buttons) {
			mb.start();
		}
	}
	
	/**
	 * restart of game
	 */
	public static synchronized void restart() {
		RaceModel model = new RaceModel();
		model.start();
		
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}


	


}
