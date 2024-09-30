package co.edu.uptc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.presenter.Presenter;

public class CalculateCenterPanel extends JPanel{
	private JRadioButton excentDiscountRbtn;
	private JRadioButton earlyDiscountRbtn;
    private ButtonGroup buttonGroup;
    private JLabel discountsTitleLabel;
    private Presenter presenter;
    
	public CalculateCenterPanel(Presenter presenter) {
		this.presenter = presenter;
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(173,216,230));
		initComponents();
		addComponents();
		
	}

	public void initComponents() {
		earlyDiscountRbtn = new JRadioButton("Descuento periodo Pronto Pago");
		earlyDiscountRbtn.setBackground(new Color(173,216,230));
        excentDiscountRbtn = new JRadioButton("Descuento Predio Excento o excluido");
        excentDiscountRbtn.setBackground(new Color(173,216,230));
        buttonGroup = new ButtonGroup();
        buttonGroup.add(earlyDiscountRbtn);
        buttonGroup.add(excentDiscountRbtn);
 
	}
	
    public void addComponents() {
    	 GridBagConstraints gbc = new GridBagConstraints();
    	 gbc.gridx = 0;      //[0,0] x-columnas// y-filas
    	 gbc.gridy = 0;
    	 gbc.gridwidth = 2;
    	 gbc.anchor = GridBagConstraints.CENTER;
    	 
         discountsTitleLabel = new JLabel("Aplicar Descuento(solo puede escoger uno)");	 
         discountsTitleLabel.setFont(new Font("Arial",Font.BOLD,16));
     	 add(discountsTitleLabel,gbc);
//--------------------------------------------------------------------------------------------     	 
     	 gbc.anchor = GridBagConstraints.WEST;
     	 gbc.gridx = 0; //[0,1] Que ocupe 2 columnas
     	 gbc.gridy = 1;
     	 
     	 add(earlyDiscountRbtn,gbc);
//---------------------------------------------------------------------------------------------     	 
         gbc.gridx = 0;
     	 gbc.gridy = 2; //[0,2] que ocupe 2 columnas
     	 add(excentDiscountRbtn,gbc);
    }
    
    public JRadioButton getRButton1() {
    	return excentDiscountRbtn;
    }
    
    public JRadioButton getRButton2() {
    	return earlyDiscountRbtn;
    }
}
