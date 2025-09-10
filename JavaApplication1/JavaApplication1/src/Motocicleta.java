//  HERANÇA
// A classe Motocicleta também herda de Veiculo.
public class Motocicleta extends Veiculo {
    private boolean antena_pipa;

    public Motocicleta(String marca, String modelo) {
        super(marca, modelo);
    }

    // Implementação obrigatória e específica para a Motocicleta
    @Override
    public void emitirSom() {
        System.out.println(getModelo() + " faz: Vrummm!");
    }
    public void setAntena(boolean temAntena){
        this.antena_pipa = temAntena;
    }
    public boolean getAntena() {
        return this.antena_pipa;
    }
}