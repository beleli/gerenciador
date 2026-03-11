package com.gerenciador;

import com.gerenciador.dao.ProdutoDAO;
import com.gerenciador.modelo.Produto;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("[1] Adicionar Produto");
            System.out.println("[2] Listar Produtos");
            System.out.println("[3] Atualizar Produto");
            System.out.println("[4] Remover Produto");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    adicionar(scanner);
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    atualizar(scanner);
                    break;
                case "4":
                    remover(scanner);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
        System.out.println("Encerrando...");
        System.exit(0);
    }

    private static void adicionar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço: ");
        Double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade: ");
        Integer quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Produto p = new Produto(nome, descricao, preco, quantidade, categoria);
        produtoDAO.salvar(p);
        System.out.println("Produto salvo com id=" + p.getId());
    }

    private static void listar() {
        List<Produto> list = produtoDAO.listar();
        if (list.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }
        for (Produto p : list) {
            System.out.println(p);
        }
    }

    private static void atualizar(Scanner scanner) {
        System.out.print("ID do produto a atualizar: ");
        Long id = Long.parseLong(scanner.nextLine());
        Produto p = produtoDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado");
            return;
        }
        System.out.print("Novo nome (enter para manter: " + p.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) p.setNome(nome);

        System.out.print("Nova descrição (enter para manter: " + p.getDescricao() + "): ");
        String descricao = scanner.nextLine();
        if (!descricao.trim().isEmpty()) p.setDescricao(descricao);

        System.out.print("Novo preço (enter para manter: " + p.getPreco() + "): ");
        String preco = scanner.nextLine();
        if (!preco.trim().isEmpty()) p.setPreco(Double.parseDouble(preco));

        System.out.print("Nova quantidade (enter para manter: " + p.getQuantidade() + "): ");
        String quantidade = scanner.nextLine();
        if (!quantidade.trim().isEmpty()) p.setQuantidade(Integer.parseInt(quantidade));

        System.out.print("Nova categoria (enter para manter: " + p.getCategoria() + "): ");
        String categoria = scanner.nextLine();
        if (!categoria.trim().isEmpty()) p.setCategoria(categoria);

        produtoDAO.atualizar(p);
        System.out.println("Produto atualizado.");
    }

    private static void remover(Scanner scanner) {
        System.out.print("ID do produto a remover: ");
        Long id = Long.parseLong(scanner.nextLine());
        produtoDAO.remover(id);
        System.out.println("Produto removido (se existia).");
    }
}

