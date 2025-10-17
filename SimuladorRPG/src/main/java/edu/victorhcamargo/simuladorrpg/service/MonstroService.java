/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.victorhcamargo.simuladorrpg.service;

import edu.victorhcamargo.simuladorrpg.Interface.Monstro;
import edu.victorhcamargo.simuladorrpg.model.MonstroEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Java_blessed
 */
public class MonstroService {
    public static List<Monstro> ObterMonstros(){
        List<Monstro> monstros = new ArrayList<>();
        for (MonstroEnum item : MonstroEnum.values()){
            Monstro m = new Monstro();
            m.setNivel(item.getNivel());
            m.setNome(item.getNome());
            monstros.add(m);
        }
        return monstros;
    }
    

    
}
