/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.victorhcamargo.e_shop;

import Produto.Produto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Java_blessed
 */
public class E_shop {

    public static void main(String[] args) throws IOException{
        Produto topeira1 = new Produto(0);
        topeira1.setNome("Teste 1");
        topeira1.setPreco((float) 24.90);
        topeira1.setSaldo((float)34.90);
        topeira1.setDescricao("Esse e meu primeiro teste");
        
        Produto topeira2 = new Produto(1);
        topeira2.setNome("Teste 2");
        topeira2.setPreco((float) 104.90);
        topeira2.setSaldo((float)3.90);
        topeira2.setDescricao("Esse e meu segundo teste");
        
        Produto topeira3 = new Produto(2);
        topeira3.setNome("Teste 3");
        topeira3.setPreco((float) 90.78);
        topeira3.setSaldo((float) 4.90);
        topeira3.setDescricao("Esse e meu terceiro teste");
        
        Produto topeira4 = new Produto(3);
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        int qtd;
        qtd = Integer.parseInt(
                                   reader.readLine()
        );
        String nome;
        nome = "\nDigite seu nome: " + reader.readLine();
        float preco;
        preco = Float.parseFloat(reader.readLine());
        float saldo;
        saldo = Float.parseFloat(reader.readLine());
        String descricao;
        descricao = reader.readLine();
        topeira4.setNome(nome);
        topeira4.setPreco(preco);
        topeira4.setSaldo(saldo);
        topeira4.setDescricao(descricao);
        System.out.println(
                "Digite seu nome: \n" + nome + "\nDigite o preço do produto: \n"
                + preco + "\nDigite o saldo disponível:\n" + saldo
                + "\nDigite a descricao do produto:\n " + descricao +
                "Venda: "
                + topeira4.getCodigo() + " "+ topeira4.getNome()
                + topeira4.getPreco()+ "x"+ qtd + " ="
                + (qtd*topeira4.getPreco())
        );
    }
}
