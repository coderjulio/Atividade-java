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
            produto1.setNome("Produto 1");
            produto1.setPreco(10.0);
            produtoController.adicionarProdutoLista(produto1);

            Produto produto2 = new Produto();
            produto2.setNome("Produto 2");
            produto2.setPreco(20.0);
            produtoController.adicionarProdutoLista(produto2);

            Produto produto3 = new Produto();
            produto3.setNome("Produto 3");
            produto3.setPreco(30.0);
            produtoController.adicionarProdutoLista(produto3);

            // Listando todos os produtos
            System.out.println("Lista de Produtos:");
            produtoController.listaProduto().forEach(produto ->
                    System.out.println(produto.getId() + ": " + produto.getNome() + " - " + produto.getPreco())
            );
        };
    }
}
