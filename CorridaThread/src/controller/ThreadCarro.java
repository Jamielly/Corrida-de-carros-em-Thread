package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
	
		private int idCarro;
		private static int posChegada;
		private static int posSaida;
		private Semaphore semaforo;
		
		public ThreadCarro(int idCarro, Semaphore semaforo) { 
			this.idCarro = idCarro; //para tirar redundancia 
			this.semaforo = semaforo; 
	}

		@Override
		public void run() { // metodo run();
			carroAndando();
		//---------Seção se tornou crítica------------
			try { 
				semaforo.acquire(); // semaforo fica preso se for de permissao 1, vira um deadlock
				carroEstacionado();
		//--------------Fim da seção critica-----------
			}catch(Exception e) { // se falhar o try, 
				e.printStackTrace();
			}finally { // revisa e solta, caso funcione o try 
			semaforo.release();
			// Fim da seção crítica
			carroSaindo();
		}
		}
		private void carroAndando() {
			int distanciaTotal = (int) ((Math.random()* 1001) + 1000);
			int distanciaPercorrida = 0;
			int deslocamento = 100;
			while (distanciaPercorrida < distanciaTotal) {
				distanciaPercorrida += deslocamento;
				distanciaPercorrida = distanciaPercorrida + deslocamento;
				try {
					long tempo = 0;
					sleep(tempo); // pausa na Thread, metodo sleep;
				}catch(InterruptedException e) {
					System.out.println(">" + idCarro + "andou" + distanciaPercorrida + "metros... ate chegar");
				}
				posChegada++;
				System.out.println(">>"+idCarro + "foi o" + posChegada + "o. a chegar");
			}
		}
		private void carroEstacionado() {
			System.out.println(">" + idCarro + "estacionou");
			int tempo = (int)((Math.random()*2001)+ 1000);
			try {
				sleep(tempo);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		private void carroSaindo() {
			posSaida++;
			System.out.println(">>" + idCarro + "foi" +posSaida + "o. a sair");
		}
//ctrl + shift + f> identação do código
		}


