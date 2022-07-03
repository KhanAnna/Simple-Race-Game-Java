package view;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author annah
 * View class
 */
public class View {
	
	/**
	 * shows window with message about winner
	 * @param name - name of button-winner
	 */
	public static void viewMessageWinner(String name) {
		showMessageDialog(null, "THE WINNER IS PLAYER-"+ name + "\nPress 'OK' to restart" );
	}

}
