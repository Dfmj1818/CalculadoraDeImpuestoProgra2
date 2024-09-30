package co.edu.uptc.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import co.edu.uptc.model.ButtonConstants;
import co.edu.uptc.model.Discount;
import co.edu.uptc.model.PanelConstants;
import co.edu.uptc.model.Property;
import co.edu.uptc.model.PropertyType;
import co.edu.uptc.model.ResidentialPropertyRate;
import co.edu.uptc.presenter.Presenter;

public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu propertyMenu;
	private JMenuItem consultPropertyItem;
	private JMenuItem createPropertyItem;
	private JMenu calculatePropertyMenu;
	private JMenuItem calculatePropertyItem;
    private MainPanel mainPanel;
	
	public MainFrame(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		this.setTitle("Calculador De Impuestos Prediales Version 2.0");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMainPanel(mainPanel);
		createPropertyMenu();
		addActionComandsToItems();
		addActionListeners();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initMainPanel(MainPanel mainPanel) {
		this.setContentPane(mainPanel);
	}

	public void createPropertyMenu() {
		// Crear barra de menús
		menuBar = new JMenuBar();

		// Menú "Predios"
		propertyMenu = new JMenu("Predios");
		consultPropertyItem = new JMenuItem("Consultar Predio");
		createPropertyItem = new JMenuItem("Crear Predio");

		propertyMenu.add(consultPropertyItem);
		propertyMenu.add(createPropertyItem);
		menuBar.add(propertyMenu);

		calculatePropertyMenu = new JMenu("Liquidacion");
		calculatePropertyItem = new JMenuItem("Calcular Liquidacion"); 
		calculatePropertyMenu.add(calculatePropertyItem);
		menuBar.add(calculatePropertyMenu);

		setJMenuBar(menuBar);
	}

	public void addActionComandsToItems() {
		consultPropertyItem.setActionCommand(ButtonConstants.CONSULT);
		createPropertyItem.setActionCommand(ButtonConstants.CREATE);
		calculatePropertyItem.setActionCommand(ButtonConstants.CALCULATE);
	}
	
	public void addActionListeners() {
		consultPropertyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainPanel.setVisibleConsultPropertyPanel();
			}
		});
		
		createPropertyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainPanel.setVisibleCreatePropertyPanel();
			}
		});
		
		calculatePropertyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisibleCalculatePanel();
			}
		});
	}

	
}

