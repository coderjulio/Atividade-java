package com.example.Atividade.Model;

import com.example.Atividade.Main.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

