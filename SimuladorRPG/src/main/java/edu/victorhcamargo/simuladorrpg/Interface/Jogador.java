/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.victorhcamargo.simuladorrpg.Interface;

import edu.victorhcamargo.simuladorrpg.model.TipoEquipamento;
import java.util.List;

/**
 *
 * @author Java_blessed
 */
public class Jogador {
    private String nome;
    private int nivel;
    private Equipamento cabeca;
    private Equipamento armadura;
    private Equipamento calcado;
    private Equipamento mao;
    private Equipamento item_geral;
    private List<Equipamento> inventario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Equipamento getCabeca() {
        return cabeca;
    }

    private boolean setCabeca(Equipamento cabeca) {
        if(cabeca.getTipo()== TipoEquipamento.CABECAL) {
            this.cabeca = cabeca;
            return true;
        } else {return false;}
    }

    public Equipamento getArmadura() {
        return armadura;
    }

    private boolean setArmadura(Equipamento armadura) {
        if(armadura.getTipo()== TipoEquipamento.ARMADURA) {
            this.armadura = armadura;
            return true;
        } else {return false;}
    }

    public Equipamento getCalcado() {
        return calcado;
    }

    private boolean setCalcado(Equipamento calcado) {
        if(calcado.getTipo()== TipoEquipamento.CALCADO) {
            this.calcado = calcado;
            return true;
        } else {return false;}
    }

    public Equipamento getMao() {
        return mao;
    }

    private boolean setMao(Equipamento mao) {
        if(mao.getTipo()== TipoEquipamento.MAO) {
            this.mao = mao;
            return true;
        } else {return false;}
    }

    public Equipamento getItem_geral() {
        return item_geral;
    }

    private boolean setItem_geral(Equipamento item_geral) {
        if(item_geral.getTipo()== TipoEquipamento.ITEM_GERAL) {
            this.item_geral = item_geral;
            return true;
        } else {return false;}
    }

    public List<Equipamento> getInventario() {
        return inventario;
    }

    public void setInventario(List<Equipamento> inventario, String nome_cabeca, String nome_armadura, String nome_calcado, String nome_mao, String nome_item) {
        for (Equipamento item : inventario) {
            if(item.getNome().equals(nome_cabeca)){
                this.setCabeca(item);
            }       
            if(item.getNome().equals(nome_armadura)){
                this.setArmadura(item);
            }
            if(item.getNome().equals(nome_mao)){
                this.setMao(item);
            }
            if(item.getNome().equals(nome_calcado)){
               this.setCalcado(item) ;
            }
            if(item.getNome().equals(nome_item)){
               this.setItem_geral(item);
            }
        }
    }

    
    
}
