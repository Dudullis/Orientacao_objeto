
import java.util.Random;
import java.util.Scanner;

public class JogoAdivinha {
    public static void main(String[] args) {

        int numeros = 0;
        int nivel;
        int numTentativas = 0;
        boolean jogoAtivo = true;
        Scanner entrada = new Scanner(System.in);
        while (jogoAtivo) {
            do {
                System.out.println("Escolha o nivel de dificuldade: \n" +
                        "1 - Iniciante; \n" +
                        "2 - Intermediario; \n" +
                        "3 - Avançado; \n" +
                        "4 - Expert; \n" +
                        "5 - Sair; \n");
                nivel = entrada.nextInt();

                if (nivel <= 0 || nivel >= 7) {
                    System.out.println("Operacao desconhecida. Reedigite o nivel!\n");
                    System.out.println("---------------------------------------------------------\n");
                }
            } while (nivel < 1 || nivel > 6);
            switch (nivel) {
                case 1:
                    System.out.println("\nNivel Iniciante\nVoce tem 10 tentativas\nAdivinhe um numero entre 0 e 30.");
                    numeros = 30;
                    numTentativas = 10;
                    break;
                case 2:
                    System.out.println("Nivel Intermediario\nAdivinhe o numero entre 0 e 50.\n");
                    numeros = 50;
                    numTentativas = 8;
                    break;
                case 3:
                    System.out.println("Nivel Avançado\nAdivinhe o numero entre 0 e 100.\n");
                    numeros = 100;
                    numTentativas = 6;
                    break;
                case 4:
                    System.out.println("Nivel Expert\nAdivinhe o numero entre 0 e 200.\n");
                    numeros = 200;
                    numTentativas = 5;
                    break;
                case 5:
                    System.out.println("Game finalizado\n\n\nObrigado por jogar!\n");
                    System.exit(0);
            }
            Random random = new Random();
            int numRandom = random.nextInt(numeros + 1);
            int tentativas = 1;

            while (tentativas <= numTentativas) {
                System.out.printf("\nTentativa numero %d: ", tentativas);
                int num = entrada.nextInt();
                tentativas++;

                if (num > numRandom)
                    System.out.printf("%d é maior!\n", num);
                else if (num < numRandom)
                    System.out.printf("%d é menor!\n", num);
                 else
                    System.out.println("Voce acertou!");
                    System.out.printf("\nSeu número de tentativas foi: %d\n", tentativas -1); 
            }
            if (tentativas > numTentativas) 
                System.out.println("Voce excedeu o limite de tentativas.\nO numero era: " + numRandom);
                System.out.println("\nDeseja jogar novamente? (1 - Sim, 2 - Não)");
                int opcaoSaida = entrada.nextInt();
                while (opcaoSaida != 1 && opcaoSaida != 2) {
                    System.out.println("Operacao desconhecida. Reedigite o nivel!\n");
                    System.out.println("---------------------------------------------------------\n");
                    opcaoSaida = entrada.nextInt();
                }
                if(opcaoSaida == 2){
                    jogoAtivo = false;
                    System.out.println("Obrigado por jogar!");
                    entrada.close();
                }
               
        }
    }
}