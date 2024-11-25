package com.example.estoque.controller;

import com.example.estoque.service.ProdutoService;
import com.example.estoque.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoService.findAllProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id){
        return produtoService.findProdutoById(id)
                .map(produto -> new ResponseEntity<>(produto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto){
        return produtoService.saveProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto){
        try {
            return  new ResponseEntity<>(produtoService.updateProduto(id, produto),HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        produtoService.deleteByID(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
