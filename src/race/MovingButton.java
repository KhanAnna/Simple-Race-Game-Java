package race;

import javax.swing.JButton;

/**
 * @author annah
 * class MovingButton describes button in game
 */
public class MovingButton extends Thread{
    private JButton button;
    private RaceModel model;
    
	/**
	 * Constructor with parameters
	 * @param model - model of race game
	 * @param button - button in game
	 * @param name - name of button-player
	 */
	public MovingButton(RaceModel model, JButton button, String name){
		this.model = model;
        this.button = button;
        this.setName(name);
    }
	
	/**
	 * If this thread was constructed using a separate Runnable run object, 
	 * then that Runnable object's run method is called;
	 * otherwise, this method does nothing and returns. 
	 */
	@Override
	public void run() {
		while(model.isRunning()) {
			model.move(this);
		}
	}

	/**
	 * @return the button
	 */
	public JButton getButton() {
		return button;
	}
	/**
	 * @param button the button to set
	 */
	public void setButton(JButton button) {
		this.button = button;
	}
}
