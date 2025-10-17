/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.victorhcamargo.simuladorrpg.service;

import edu.victorhcamargo.simuladorrpg.Interface.Equipamento;
import edu.victorhcamargo.simuladorrpg.model.EquipamentoEnum;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Java_blessed
 */
public class EquipamentoService {
    public static List<Equipamento> ObterEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        for (EquipamentoEnum item : EquipamentoEnum.values()){
            Equipamento e = new Equipamento();
            e.setNome(item.getNome());
            e.setBonus(item.getBonus());
            e.setTipo(item.getTipo());
            equipamentos.add(e);
        }
        return equipamentos;
    }
}
