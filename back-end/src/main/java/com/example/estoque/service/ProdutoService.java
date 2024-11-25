package com.example.estoque.service;

import com.example.estoque.model.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAllProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> findProdutoById(Long id){
        return produtoRepository.findById(id);
    }

    public Produto saveProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto updateProduto){
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(updateProduto.getNome());
                    produto.setPreco(updateProduto.getPreco());
                    produto.setQuantidade(updateProduto.getQuantidade());
                    produto.setCategoria(updateProduto.getCategoria());
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto not found"));

    }

    public void deleteByID(Long id){
        produtoRepository.deleteById(id);
    }
}
