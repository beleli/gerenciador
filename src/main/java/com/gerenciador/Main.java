package com.gerenciador;

import com.gerenciador.dao.ProdutoDAO;
import com.gerenciador.erro.LeituraException;
import com.gerenciador.modelo.Produto;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do gerenciador de produtos
 * Implementa um sistema de menu para CRUD de produtos
 */
public class Main {
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAXIMO_TENTATIVAS = 3;

    public static void main(String[] args) {
        try {
            boolean executando = true;

            while (executando) {
                exibirMenu();
                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1":
                        adicionarProduto();
                        break;
                    case "2":
                        listarProdutos();
                        break;
                    case "3":
                        atualizarProduto();
                        break;
                    case "4":
                        removerProduto();
                        break;
                    case "5":
                        executando = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }

            System.out.println("Encerrando aplicação...");
        } finally {
            scanner.close();
            System.exit(0);
        }
    }

    /**
     * Exibe o menu principal
     */
    private static void exibirMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("[1] Adicionar Produto");
        System.out.println("[2] Listar Produtos");
        System.out.println("[3] Atualizar Produto");
        System.out.println("[4] Remover Produto");
        System.out.println("[5] Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Adiciona um novo produto
     */
    private static void adicionarProduto() {
        System.out.println("\n--- Adicionar Novo Produto ---");

        try {
            String nome = lerString("Nome: ", false);
            String descricao = lerString("Descrição: ", true);
            Double preco = lerDouble("Preço: ", true);
            Integer quantidade = lerInteger("Quantidade: ", true);

            Produto produto = new Produto(nome, descricao, preco, quantidade);
            produtoDAO.salvar(produto);
            System.out.println("Produto salvo com sucesso! ID: " + produto.getId());
        } catch (LeituraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lista todos os produtos cadastrados
     */
    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoDAO.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        for (Produto p : produtos) {
            System.out.println(p.toString());
        }
    }

    /**
     * Atualiza um produto existente com validação de entrada
     */
    private static void atualizarProduto() {
      System.out.println("\n--- Atualizar Produto ---");
        try {
            Long id = lerLong("ID do produto a atualizar: ");
            Produto produto = produtoDAO.buscarPorId(id);
            if (produto == null) {
                System.out.println("Produto com ID " + id + " não encontrado.");
                return;
            }

            produto.setNome(lerString("Nome: ", false));
            produto.setDescricao(lerString("Descrição: ", true));
            produto.setPreco(lerDouble("Preço: ", true));
            produto.setQuantidade(lerInteger("Quantidade: ", true));

            produtoDAO.atualizar(produto);
            System.out.println("Produto atualizado com sucesso!");
        } catch (LeituraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove um produto pelo ID com validação
     */
    private static void removerProduto() {
        System.out.println("\n--- Remover Produto ---");

        try {
            Long id = lerLong("ID do produto a remover: ");
            Produto produto = produtoDAO.buscarPorId(id);
            if (produto == null) {
                System.out.println("Produto com ID " + id + " não encontrado.");
                return;
            }
            produtoDAO.remover(produto.getId());
            System.out.println("Produto removido com sucesso!");
        } catch (LeituraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lê uma string do usuário com validação
     * @param mensagem Mensagem a exibir
     * @param opcional Valor pode ser vazio
     **/
    private static String lerString(String mensagem, Boolean opcional) throws LeituraException {
        for (int attempt = 1; attempt <= MAXIMO_TENTATIVAS; attempt++) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                if (opcional) {
                    return null;
                }
                System.out.println("Erro: O campo não pode estar vazio. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
            } else {
                return input;
            }
        }
        throw new LeituraException();
    }

    /**
     * Lê um Long do usuário com validação
     * @param mensagem Mensagem a exibir
     * @return Long validado ou null se exceder o máximo de tentativas
     */
    public static Long lerLong(String mensagem) throws LeituraException {
        for (int attempt = 1; attempt <= MAXIMO_TENTATIVAS; attempt++) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Erro: O campo não pode estar vazio. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
                continue;
            }

            try {
                long value = Long.parseLong(input);
                if (value < 0) {
                    System.out.println("Erro: O valor deve ser positivo. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Digite um número válido. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
            }
        }
        throw new LeituraException();
    }

    /**
     * Lê um Integer do usuário com validação
     * @param mensagem Mensagem a exibir
     * @param canBeEmpty Valor pode ser vazio
     * @return Long validado ou null se exceder o máximo de tentativas
     */
    public static Integer lerInteger(String mensagem, Boolean canBeEmpty) throws LeituraException {
        for (int attempt = 1; attempt <= MAXIMO_TENTATIVAS; attempt++) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();

            if (input.isEmpty() & canBeEmpty) {
                return null;
            }
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Erro: O valor deve ser positivo. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Digite um número válido. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
            }
        }
        throw new LeituraException();
    }

    /**
     * Lê um Double do usuário com validação
     * @param mensagem Mensagem a exibir
     * @param canBeEmpty Valor pode ser vazio
     * @return Long validado ou null se exceder o máximo de tentativas
     */
    public static Double lerDouble(String mensagem, Boolean canBeEmpty) throws LeituraException {
        for (int attempt = 1; attempt <= MAXIMO_TENTATIVAS; attempt++) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();

            if (input.isEmpty() & canBeEmpty) {
                return null;
            }
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Erro: O valor deve ser positivo. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Digite um número válido. Tentativa " + attempt + "/" + MAXIMO_TENTATIVAS);
            }
        }
        throw new LeituraException();
    }
}
