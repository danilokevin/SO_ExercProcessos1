package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController 
{
	public RedesController(){
		super();
	}
	
	public int os(){
		int option = 0;
		String os = System.getProperty("os.name");
		JOptionPane.showMessageDialog(null, "Sistema Operacional em execução: " + os);
		
		if (os.contains("Windows")){
			option = 1;
		} else {
			option = 2;
		}
		return option;
	}
	
	public void readProcessAdap(String process){
		
		String vetorAdap[] = new String[5];
		String ipv4[] = new String[5];
		int contAdap = 0, i = 0;
		int[] guardaAdap = new int[5];
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) { //
				
				if (linha.contains("Adaptador")){
					vetorAdap[contAdap] = linha;
					contAdap++;
				} 
				
				if (linha.contains("IPv4")){
					String[] vetorPalavras = linha.split(" ");
					ipv4[i] = vetorPalavras[vetorPalavras.length-1];
					guardaAdap[i] = contAdap-1; //vai guardar o índice do vetor onde tem um Adaptador com ipv4
					i++;
				} 
				
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		for (i = 0; i < guardaAdap.length; i++){
			if (guardaAdap[i] != 0){
				JOptionPane.showMessageDialog(null, vetorAdap[guardaAdap[i]] + "\nIpva4: " + ipv4[i]);				
			}
		}
		
	}
	
	public void readProcessPing(String process, int sistema){
		
		float media = 0, x = 0;;
		int cont= 0, i, j;
		String msg;
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) { //
				
				if (sistema == 1){
					if (linha.contains("Resposta")){
						i = linha.indexOf("=");
						j = linha.indexOf("ms");
						msg = linha.substring(i+1, j);
						x = Integer.parseInt(msg);
						media = media + x;
						cont++;
					}
				} else {
					if (linha.contains("Resposta")){
						i = linha.lastIndexOf("time=");
						j = linha.lastIndexOf(" ms");
						msg = linha.substring(i+1, j);
						x = Integer.parseInt(msg);
						media = media + x;
						cont++;
					}
				}
				
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Resultado: \nTempo médio em ms: " + media/cont);
		
		
	}
	
}
