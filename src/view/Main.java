package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rc = new RedesController();
		int system = 0;
		String process = "";
		int menu = 0;

		while (menu != 9){
			menu = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção: \n1 - Obter Sistema Operacional \n2 - Visualizar Rede com Ipv4 \n3 - Obter média de tempo em PING \n9 - Sair"));

			switch (menu){
			case 1:
				system = rc.os();
				break;
			case 2:
				if (system == 0){
					JOptionPane.showMessageDialog(null, "Obter sistema operacional na opção 1");
				} else {
					if (system == 1) {
						process = "ipconfig";
					} else {
						process = "ifconfig";
					}

					rc.readProcessAdap(process);
				}	
				break;
			
			case 3:
				if (system == 0){
					JOptionPane.showMessageDialog(null, "Obter sistema operacional na opção 1");
				} else {
					if (system == 1) {
						process = "PING -n 10 www.google.com.br";
					} else {
						process = "PING –c 10 www.google.com.br";
					}

					rc.readProcessPing(process);
				}	
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Programa finalizado");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
			}
		}






	}

}
