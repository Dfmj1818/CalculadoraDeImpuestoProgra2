package co.edu.uptc.view;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import co.edu.uptc.model.PanelConstants;
import co.edu.uptc.presenter.Presenter;

public class MainPanel extends JPanel {

	private Presenter presenter;
	private JButton consultPropertyBtn;
	private JButton calculateTaxBtn;
	private JButton createPropertyBtn;
	private CardLayout cardLayout;
	private CalculatePanel calculatePanel;
	private CreatePropertyPanel createPropertyPanel;
	private ConsultPropertyPanel consultPropertyPanel;
	private EntryPanel entryPanel;

	public MainPanel(Presenter presenter) {
		this.presenter = presenter;
		setUpLayout();
		setUpPanels();
	}

	public void setUpLayout() {
		cardLayout = new CardLayout();
		setLayout(cardLayout);
	}


	private void setUpPanels() {

		entryPanel = new EntryPanel();
		calculatePanel = new CalculatePanel(presenter);
		createPropertyPanel = new CreatePropertyPanel(presenter);
		consultPropertyPanel = new ConsultPropertyPanel(presenter);

		add(entryPanel,PanelConstants.ENTRYPANEL);
		add(calculatePanel,PanelConstants.CALCULATEPANEL);
		add(createPropertyPanel,PanelConstants.CREATEPROPERTYPANEL);
		add(consultPropertyPanel,PanelConstants.CONSULTPROPERTYPANEL);

		// Inicialmente, solo muestra uno de los paneles
		calculatePanel.setVisible(false);
		createPropertyPanel.setVisible(false);
		consultPropertyPanel.setVisible(false);
	}


	public void setVisibleCalculatePanel() {
		this.cardLayout.show(this,PanelConstants.CALCULATEPANEL);
	}

	public void setVisibleCreatePropertyPanel() {
		this.cardLayout.show(this,PanelConstants.CREATEPROPERTYPANEL);
	}

	public void setVisibleConsultPropertyPanel(){
		this.cardLayout.show(this,PanelConstants.CONSULTPROPERTYPANEL);
	}

}

