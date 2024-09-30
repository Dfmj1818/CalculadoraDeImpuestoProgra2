package co.edu.uptc.view;

import javax.swing.JOptionPane;

public class IoManager {

	public IoManager() {
		
	}
	
	public int readInt(String message){
		return Integer.parseInt(JOptionPane.showInputDialog(message));
	}
	
	public String formatDouble(Double numberToFormat) {
		return String.format("%..2f COP", numberToFormat);
	}

	public String readString(String message){
		return JOptionPane.showInputDialog(message);
	}

	public Double readDouble(String message){
		return Double.parseDouble(JOptionPane.showInputDialog(message));
	}
	
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void printOkMessage(String message){
		JOptionPane.showMessageDialog(null, message,"Proceso Exitoso :)",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void printErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message,"Error",JOptionPane.PLAIN_MESSAGE);
	}
	
	public Double castStringToDouble(String input) {
		return Double.parseDouble(input);
	}
	
	public int castStringToInt(String input){
		return Integer.parseInt(input);
	}
}
