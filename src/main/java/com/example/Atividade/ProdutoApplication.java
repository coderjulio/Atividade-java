package com.example.Atividade;

import com.example.Atividade.Controller.ProdutoController;
import com.example.Atividade.Main.Produto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            ProdutoController produtoController = context.getBean(ProdutoController.class);

            // Criando e adicionando produtos
            Produto produto1 = new Produto();
            produto1.setNome("Camisa");
            produto1.setPreco(45.0);
            produtoController.adicionarProdutoLista(produto1);

            Produto produto2 = new Produto();
            produto2.setNome("Calça");
            produto2.setPreco(150.0);
            produtoController.adicionarProdutoLista(produto2);

            Produto produto3 = new Produto();
            produto3.setNome("Boné");
            produto3.setPreco(30.0);
            produtoController.adicionarProdutoLista(produto3);

            // Listando todos os produtos
            System.out.println("Lista de Produtos:");
            produtoController.listaProduto().forEach(produto ->
                    System.out.println(produto.getId() + ": " + produto.getNome() + " - R$" + produto.getPreco())
            );
        };
    }
}
