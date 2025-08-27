package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
	// ex1 retorna tipo do SO
	private String os() {
		return System.getProperty("os.name");
	}
	
	// ex2 mostra adaptadores e IPv4
	public void ip() {
		String so = "";
		// verifica o Sistema Operacional SO.
		if (os().contains("Windows")) {
			so = "ipconfig";
		} else if (os().contains("Linux")) {
			so = "ifconfig";
		} else {
			System.out.println("Sistema Operacional não encotrado");
		}

		try {
			Process p = Runtime.getRuntime().exec(so);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();
			while (linha != null) {
				// Windows: linha com "Adaptador" e próxima com "IPv4"
				if (linha.contains("Adaptador") || linha.contains("adapter")) {
					System.out.println(linha.trim());
				}
				if (linha.contains("IPv4")) {
					System.out.println(linha.trim());
				}
				// Linux: ifconfig/ip addr -> buscar "inet " mas não "inet6"
				if ((linha.contains("inet ")) && (!linha.contains("inet6"))) {
				    String[] partes = linha.trim().split("\\s+"); // separa por espaços
				    if (partes.length > 1) {
				        System.out.println(partes[1]); // só o IP (10.0.2.15 ou 127.0.0.1)
				    }
				}
				//System.out.println(linha.trim());
				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	// ex3 ping
	public void ping() {
		String comando = "";

		if (os().contains("Windows")) {
			comando = "ping -4 -n 10 www.google.com.br";
		} else if (os().contains("Linux")) {
			comando = "ping -4 -c 10 www.google.com.br";
		} else {
			System.out.println("Sistema Operacional não encotrado");
		}

		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			
			String linha = buffer.readLine();
			while (linha != null) {
				//System.out.println(linha);
			    if (linha.contains(",") && linha.toLowerCase().contains("ms")) {
			        String[] partes = linha.split("=");
			        if (partes.length > 3) {
			            String media = partes[3].trim(); // última parte é a média
			            System.out.println("Tempo médio: " + media);
			        }
			    }

			    if (linha.contains("rtt min/avg/max")) {
			        String[] partes = linha.split("=");
			        if (partes.length > 1) {
			            String[] valores = partes[1].trim().split("/");
			            String media = valores[1]; // posição 1 = avg
			            System.out.println("Tempo médio: " + media + " ms");
			        }
			    }

			    linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
