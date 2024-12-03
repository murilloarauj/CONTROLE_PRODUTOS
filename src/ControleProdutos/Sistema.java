package ControleProdutos;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;
import conexao.Conexao;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conexao = Conexao.conectar(); // Conexão com o banco de dados

        if (conexao != null) {  // Verifica se a conexão foi bem-sucedida

            while (true) {
                // Exibe o menu principal com as opções de operações
                System.out.println("\nEscolha sua opção:");
                System.out.println("1 - Insira o Produto");
                System.out.println("2 - Atualize Produto");
                System.out.println("3 - Delete Produto");
                System.out.println("4 - Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consome a linha restante após o número
                
                if (opcao == 4) {
                    System.out.println("Até breve");
                    break;  // Sai do loop e encerra o programa
                }

                // Exibe a opção de escolha de tipo de produto (alimentício ou vestuário)
                System.out.println("Escolha o tipo de produto:");
                System.out.println("1 - Produto Alimentício");
                System.out.println("2 - Produto de Vestuário");
                int tipoProduto = scanner.nextInt();
                scanner.nextLine();  // Consome a linha restante após o número

                switch (opcao) {
                    case 1: // Inserir produto
                        if (tipoProduto == 1) {
                            // Produto Alimentício
                            System.out.println("Insira um Produto Alimentício");

                            // Solicita as informações do produto alimentício
                            System.out.print("Nome do produto: ");
                            String nomeAlim = scanner.nextLine();
                            System.out.print("Valor do produto: ");
                            float custoAlim = scanner.nextFloat();
                            System.out.print("Preço vendável: ");
                            float vendaAlim = scanner.nextFloat();
                            System.out.print("Valor calórico: ");
                            int calorias = scanner.nextInt();
                            System.out.print("Teor proteico: ");
                            int proteico = scanner.nextInt();
                            System.out.print("Teor de gordura: ");
                            int gordura = scanner.nextInt();
                            System.out.print("Teor de carboidrato: ");
                            int carboidrato = scanner.nextInt();
                            System.out.print("Validade (ano-mês-dia): ");
                            scanner.nextLine();  // Consome o \n restante
                            String validadeStr = scanner.nextLine();
                            LocalDate validade = LocalDate.parse(validadeStr);

                            // Cria um objeto ProdutoAlimenticio e salva no banco de dados
                            ProdutoAlimenticio produtoAlim = new ProdutoAlimenticio(nomeAlim, custoAlim, vendaAlim,
                                    calorias, proteico, gordura, carboidrato, validade);
                            produtoAlim.salvarProduto(conexao);  // Método de salvar o produto
                        } else if (tipoProduto == 2) {
                            // Produto de Vestuário
                            System.out.println("Inserir Produto de Vestuário");

                            // Solicita as informações do produto de vestuário
                            System.out.print("Nome do produto: ");
                            String nomeVestu = scanner.nextLine();
                            System.out.print("Valor do produto: ");
                            float custoVestu = scanner.nextFloat();
                            System.out.print("Preço vendável: ");
                            float vendaVestu = scanner.nextFloat();
                            System.out.print("Tamanho: ");
                            scanner.nextLine();  // Consome o \n restante
                            String tamanho = scanner.nextLine();
                            System.out.print("Cor: ");
                            String cor = scanner.nextLine();
                            System.out.print("Material: ");
                            String material = scanner.nextLine();

                            // Cria um objeto ProdutoVestuario e salva no banco de dados
                            ProdutoVestuario produtoVestu = new ProdutoVestuario(nomeVestu, custoVestu, vendaVestu, 
                                    tamanho, cor, material);
                            produtoVestu.salvarProduto(conexao);  // Método de salvar o produto
                        }
                        break;
                    
                    case 2: // Atualizar produto
                        System.out.print("Informe o ID do produto para atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();  // Consome a linha restante após o número

                        // Atualiza o produto com base no tipo
                        if (tipoProduto == 1) {
                            // Atualizar Produto Alimentício
                            System.out.println("Atualizar Produto Alimentício");

                            // Solicita as novas informações para o produto alimentício
                            System.out.print("Nome do produto: ");
                            String nomeAlim = scanner.nextLine();
                            System.out.print("Valor do produto: ");
                            float custoAlim = scanner.nextFloat();
                            System.out.print("Preço vendável: ");
                            float vendaAlim = scanner.nextFloat();
                            System.out.print("Valor calórico: ");
                            int calorias = scanner.nextInt();
                            System.out.print("Teor proteico: ");
                            int proteico = scanner.nextInt();
                            System.out.print("Teor de gordura: ");
                            int gordura = scanner.nextInt();
                            System.out.print("Teor de carboidrato: ");
                            int carboidrato = scanner.nextInt();
                            System.out.print("Validade (ano-mês-dia): ");
                            scanner.nextLine();  // Consome o \n restante
                            String validadeStr = scanner.nextLine();
                            LocalDate validade = LocalDate.parse(validadeStr);

                            // Cria o objeto ProdutoAlimenticio atualizado e salva no banco de dados
                            ProdutoAlimenticio produtoAlim = new ProdutoAlimenticio(nomeAlim, custoAlim, vendaAlim,
                                    calorias, proteico, gordura, carboidrato, validade);
                            produtoAlim.atualizarProduto(conexao, idAtualizar);  // Método de atualização do produto
                        } else if (tipoProduto == 2) {
                            // Atualizar Produto de Vestuário
                            System.out.println("Atualizar Produto de Vestuário");

                            // Solicita as novas informações para o produto de vestuário
                            System.out.print("Nome do produto: ");
                            String nomeVestu = scanner.nextLine();
                            System.out.print("Valor do produto: ");
                            float custoVestu = scanner.nextFloat();
                            System.out.print("Preço vendável: ");
                            float vendaVestu = scanner.nextFloat();
                            System.out.print("Tamanho: ");
                            scanner.nextLine();  // Consome o \n restante
                            String tamanho = scanner.nextLine();
                            System.out.print("Cor: ");
                            String cor = scanner.nextLine();
                            System.out.print("Material: ");
                            String material = scanner.nextLine();

                            // Cria o objeto ProdutoVestuario atualizado e salva no banco de dados
                            ProdutoVestuario produtoVestu = new ProdutoVestuario(nomeVestu, custoVestu, vendaVestu, 
                                    tamanho, cor, material);
                            produtoVestu.atualizarProduto(conexao, idAtualizar);  // Método de atualização do produto
                        }
                        break;

                    case 3: // Deletar produto
                        System.out.print("Qual ID para apagar: ");
                        int idDeletar = scanner.nextInt();
                        scanner.nextLine();  // Consome a linha restante após o número

                        // Deleta o produto com base no tipo
                        if (tipoProduto == 1) {
                            // Deletar Produto Alimentício
                            ProdutoAlimenticio.deletarProduto(conexao, idDeletar);  // Método de deletar produto
                        } else if (tipoProduto == 2) {
                            // Deletar Produto de Vestuário
                            ProdutoVestuario.deletarProduto(conexao, idDeletar);  // Método de deletar produto
                        }
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");  // Caso o usuário forneça uma opção inválida
                        break;
                }
            }

            scanner.close();  
        }
    }
}
