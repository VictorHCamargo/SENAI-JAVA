/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.victorhcamargo.e_shop;

import Produto.Produto;

/**
 *
 * @author Java_blessed
 */
public class E_shop {

    public static void main(String[] args) {
        Produto topeira = new Produto();
        topeira.setNome("Teste 1");
        topeira.setCodigo(1);
        topeira.setPreco((float) 24.90);
        topeira.setSaldo((float)34.90);
        topeira.setDescricao("Esse e meu primeiro teste");
        System.out.println(
                "Venda:"
                + " " + topeira.getNome()
                + " " + topeira.getDescricao()
                + " COD:" + topeira.getCodigo()
                + " Valor: " + topeira.getPreco()
                + " Saldo:" + topeira.getSaldo()
                + " Ao comprar: " + (topeira.getSaldo() - topeira.getPreco())
        );
    }
}
