package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Principal {

	
	public static void main(String[] args) {
		RedesController redes = new RedesController();
		int opc = 0;

		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog(
					"MENU\n"
					+ "1 - Configuração IP\n"
					+ "2 - Teste Ping\n"
					+ "9 - Finalizar"));
			
			switch (opc) {
			case 1:
				redes.ip();
				break;
			case 2:
				redes.ping();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Fim!");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
		}	
	}
}