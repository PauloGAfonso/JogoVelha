import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner leitor = new Scanner(System.in);
        String[][] tabelaJogo = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
        };
        boolean[] isVitoria = new boolean[3];

        int totalElementos = tabelaJogo.length * tabelaJogo[0].length;
        Thread.sleep(5000);

        boolean jogador1 = true;
        boolean jogador2 = false;

        mostrarTabela(tabelaJogo);
        Thread.sleep(3000);
        limpaTela();
        isVitoria = receberJogada(tabelaJogo, jogador1, jogador2, totalElementos, leitor, isVitoria);
        jogador1 = isVitoria[0];
        jogador2 = isVitoria[1];
        if(isVitoria[2] == true){
            if (jogador1) {
                mostrarTabela(tabelaJogo);
                System.out.println("Jogador 1 venceu, Parabéns!");
            }
            if (jogador2) {
                System.out.println("Jogador 2 venceu, Parabéns!");
            }
        } else {
            System.out.println("deu velha");
        }

    }

    public static void mostrarTabela(String[][] tabelaJogo){
        for(String[] lista : tabelaJogo){
            System.out.println(Arrays.toString(lista));
        }
    }

    public static boolean[] receberJogada (String [][] tabela, boolean jogador1, boolean jogador2, int totalElementos, Scanner leitor, boolean[] isVitoria) throws Exception{
        ArrayList<String> opcoesEscolhidas = new ArrayList<>();
        int linha = -1;
        int coluna = -1;
        for(int i = 0; i < totalElementos; i++){            
            if(jogador1){
                while(jogador1){
                    mostrarTabela(tabela);
                    System.out.println();
                    System.out.println("Qual numero voce deseja jogar o X jogador 1? ");
                    System.out.print("Opcao: ");
                    String escolha = leitor.nextLine();
                    boolean isRight = false;
                    while(isRight != true){
                        for(int l = 0; l < tabela.length; l++){
                            for(int j = 0; j < tabela[i].length; j++){
                                if(tabela[l][j].equals(escolha)){
                                    tabela[l][j] = "x";
                                    isVitoria = verificandoVitoria(tabela, jogador1, jogador2);
                                    if(isVitoria[2] == true){
                                        break;
                                    }
                                    jogador1 = !jogador1;
                                    jogador2 = !jogador2;
                                    opcoesEscolhidas.add(escolha);
                                } else if(opcoesEscolhidas.contains(escolha)){
                                    System.out.println("a opcao " + escolha + "já foi escolhida, por favor escolha outro!");
                                }
                            }
                        }
                    }
                
                    if(tabela[linha][coluna].equals("x") || tabela[linha][coluna].equals("o")){
                        System.out.println("Essa linha já foi jogada ");
                        limpaTela();
                    } else {
                        tabela[linha][coluna] = "x";
                        isVitoria = verificandoVitoria(tabela, jogador1, jogador2);
                        if(isVitoria[2] == true){
                            break;
                        }
                        
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
                        isVitoria = verificandoVitoria(tabela, jogador1, jogador2);
                        if(isVitoria[2] == true){
                            break;
                        }
                        jogador2 = !jogador2;
                        jogador1 = !jogador1;
                    }
                }
            }
            limpaTela();
            if(isVitoria[2] == true){
                break;
            }
        }
        return new boolean[]{jogador1, jogador2, isVitoria[2]};
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

    // 0 = jogador1, 1 = jogador2, 2 = isVitoria
    public static boolean[] verificandoVitoria(String[][] tabela, boolean jogador1, boolean jogador2){
        
        boolean isVitoria = false;
        for(int i = 0; i < tabela.length; i++){
            if(tabela[i][0].equals(tabela[i][1]) && tabela[i][1].equals(tabela[i][2])){ //Verificando Linha
                if(tabela[i][0].equals("x")){
                    jogador1 = true;
                    jogador2 = false;
                    isVitoria = true;
                } else if(tabela[i][0].equals("o")){
                    jogador1 = false;
                    jogador2 = true;
                    isVitoria = true;
                }
            } else if(tabela[0][i].equals(tabela[1][i]) && tabela[i][1].equals(tabela[2][i])){//Verificando coluna
                if(tabela[0][i].equals("x")){
                    jogador1 = true;
                    jogador2 = false;
                    isVitoria = true;
                } else if(tabela[0][i].equals("o")){
                    jogador1 = false;
                    jogador2 = true;
                    isVitoria = true;
                }
            } else if(tabela[0][0].equals(tabela[1][1]) && tabela[1][1].equals(tabela[2][2]) ){//Diagonais
                if(tabela[0][0].equals("x")){
                    jogador1 = true;
                    jogador2 = false;
                    isVitoria = true;
                } else if(tabela[0][0].equals("o")){
                    jogador1 = false;
                    jogador1 = true;
                    isVitoria = true;
                }
            } else if(tabela[0][2].equals(tabela[1][1]) && tabela[1][1].equals(tabela[2][0])){
                if(tabela[0][2].equals("x")){
                    jogador1 = true;
                    jogador2 = false;
                    isVitoria = true;
                } else if(tabela[0][2].equals("o")){
                    jogador1 = false;
                    jogador1 = true;
                    isVitoria = true;
                }
            }
        }
        return new boolean[]{jogador1, jogador2, isVitoria};   
    }
}