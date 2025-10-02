package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Java_blessed
 */
public class Produto {
    private int Codigo;
    private double Preco;
    private String Descricao;

    @Override
    public String toString() {
        return Codigo+"-"+Descricao;
    }
    
    public int getCodigo() {
        return Codigo;
    }

    public double getPreco() {
        return Preco;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    
}
