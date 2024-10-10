package com.example.Atividade.Controller;

import com.example.Atividade.Main.Produto;
import com.example.Atividade.Model.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private List<Produto> produtos = new ArrayList<>();

    @GetMapping("/lista")
    public List<Produto> listaProduto() {
        List<Produto> produtosBanco = produtoRepository.findAll();
        List<Produto> todososprodutos = new ArrayList<>(produtos);
        todososprodutos.addAll(produtosBanco);
        return todososprodutos;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarporId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/adicionar")
    public Produto adicionarProdutoLista(@RequestBody Produto produto) {
        produto.setId(produtos.size() + 1);
        produtos.add(produto);
        return produto;
    }

    @PostMapping("/criar")
    public Produto criarProduto(@RequestBody Produto produto) {
        produto.setId(produtos.size() + 1);
        return produtoRepository.save(produto);
    }


    @PutMapping("/atualizar-lista/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Produto produtoatualizado) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(produtoatualizado.getNome());
                produto.setPreco(produtoatualizado.getPreco());
                return ResponseEntity.ok(produto);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/atualizar-banco/{id}")
    public ResponseEntity<Produto> atualizarProdutoBanco(@PathVariable long id, @RequestBody Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            Produto produtoSalvo = produtoRepository.save(produto);
            return ResponseEntity.ok(produtoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/deletar-lista/{id}")
    public ResponseEntity<Void> deletarProdutoLista(@PathVariable Long id) {
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);

        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProdutoBanco(@PathVariable Long id){
        Optional<Produto> produtoexistente = produtoRepository.findById(id);
        if(produtoexistente.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}