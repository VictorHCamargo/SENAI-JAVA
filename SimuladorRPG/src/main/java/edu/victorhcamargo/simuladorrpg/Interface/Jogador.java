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
    private int poder;

    

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

    public boolean setCabeca(Equipamento cabeca) {
        if(cabeca.getTipo()== TipoEquipamento.CABECAL) {
            this.cabeca = cabeca;
            return true;
        } else {return false;}
    }

    public Equipamento getArmadura() {
        return armadura;
    }

    public boolean setArmadura(Equipamento armadura) {
        if(armadura.getTipo()== TipoEquipamento.ARMADURA) {
            this.armadura = armadura;
            return true;
        } else {return false;}
    }

    public Equipamento getCalcado() {
        return calcado;
    }

    public boolean setCalcado(Equipamento calcado) {
        if(calcado.getTipo()== TipoEquipamento.CALCADO) {
            this.calcado = calcado;
            return true;
        } else {return false;}
    }

    public Equipamento getMao() {
        return mao;
    }

    public boolean setMao(Equipamento mao) {
        if(mao.getTipo()== TipoEquipamento.MAO) {
            this.mao = mao;
            return true;
        } else {return false;}
    }

    public Equipamento getItem_geral() {
        return item_geral;
    }

    public boolean setItem_geral(Equipamento item_geral) {
        if(item_geral.getTipo()== TipoEquipamento.ITEM_GERAL) {
            this.item_geral = item_geral;
            return true;
        } else {return false;}
    }
    
    public int getPoder() {
        this.poder = this.getNivel();
        if(cabeca != null) {
            poder += cabeca.getBonus();
        }
        if(armadura != null) {
            poder += armadura.getBonus();
        }
        if(calcado != null) {
            poder += calcado.getBonus();
        }
        if(item_geral != null) {
            poder += item_geral.getBonus();
        }
        if(mao != null) {
            poder += mao.getBonus();
        }
        return poder;
    }
    public void removerInventario() {
        cabeca = null;
        armadura = null;
        calcado = null;
        mao = null;
        item_geral = null;
    }
    @Override
    public String toString() {
        return "Jogador: " + nome + ", nivel = " + nivel;
    }
    
}
