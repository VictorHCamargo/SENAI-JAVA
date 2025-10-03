/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.victorhcamargo.padaria.produtoVenda;

import model.Produto;

/**
 *
 * @author Java_blessed
 */
public class ProdutoVenda {
    private Produto produto;
    private int quantidade;
    private double total;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = this.quantidade * this.produto.getPreco();
    }

    @Override
    public String toString() {
        return quantidade + " x "+ produto.getDescricao() + " = " + total;
    }
    
}
