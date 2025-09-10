/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Java_blessed
 */
public class Caminhao extends Veiculo {
    private int qtde_eixos;
    public Caminhao(String marca, String modelo) {
        super(marca,modelo);
    }
    @Override
    public void emitirSom() {
        System.out.println(getModelo() + "Faz: foonfoon fon!!!");
    }
    
    
    public void setEixos(int N_eixos) {
        this.qtde_eixos = N_eixos;
    }
    public int getEixos() {
        return this.qtde_eixos;
    }
}
