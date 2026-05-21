import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner leitor = new Scanner(System.in);
        String[][] tabelaJogo = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
        };
        



        int totalElementos = tabelaJogo.length * tabelaJogo[0].length;
        Thread.sleep(5000);

        boolean jogador1 = true;
        boolean jogador2 = false;

        mostrarTabela(tabelaJogo);
        Thread.sleep(3000);
        limpaTela();
        receberJogada(tabelaJogo, jogador1, jogador2, totalElementos, leitor);

    }

    public static void mostrarTabela(String[][] tabelaJogo){
        for(String[] lista : tabelaJogo){
            System.out.println(Arrays.toString(lista));
        }
    }

    public static void receberJogada (String [][] tabela, boolean jogador1, boolean jogador2, int totalElementos, Scanner leitor) throws Exception{
        int linha = -1;
        int coluna = -1;
        for(int i = 0; i < totalElementos; i++){            
            if(jogador1){
                while(jogador1){
                    mostrarTabela(tabela);
                    System.out.println();
                    System.out.println("Qual linha e coluna você deseja jogar o X jogador 1? ");
                    System.out.print("Linha: ");
                    linha = leitor.nextInt();
                    linha -= 1;
                    leitor.nextLine();
                    System.out.println("Coluna: ");
                    coluna = leitor.nextInt();
                    coluna -= 1;
                    leitor.nextLine();
                
                    if(tabela[linha][coluna].equals("x") || tabela[linha][coluna].equals("o")){
                        System.out.println("Essa linha já foi jogada ");
                        limpaTela();
                    } else {
                        tabela[linha][coluna] = "x";
                        jogador1 = !jogador1;
                        jogador2 = !jogador2;
                    }
                } 
            } else if(jogador2){
                while(jogador2){
                    mostrarTabela(tabela);
                    System.out.println();
                    System.out.println("Qual linha e coluna você deseja jogar O jogador 2? ");
                    System.out.print("Linha: ");
                    linha = leitor.nextInt();
                    linha -= 1;
                    leitor.nextLine();
                    System.out.print("Coluna: ");
                    coluna = leitor.nextInt();
                    coluna -= 1;
                    leitor.nextLine();
                
                    if(tabela[linha][coluna].equals("x") || tabela[linha][coluna].equals("o")){
                        System.out.println("Essa linha já foi jogada ");
                        Thread.sleep(4000);
                        limpaTela();
                    } else {
                        tabela[linha][coluna] = "o";
                        jogador2 = !jogador2;
                        jogador1 = !jogador1;
                    }
                }
            }
            limpaTela();
        }
    }

    public static void limpaTela() {
        try {
            // Detecta o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Linux ou Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Se não funcionar (ex: rodando em IDE), imprime várias linhas em branco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
