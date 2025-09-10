//  HERANÇA
// A classe Carro "herda" todos os atributos e métodos da classe Veiculo.
// Um Carro É um Veiculo.
public class Carro extends Veiculo {
    private int qtde_portas;
    // Construtor que chama o construtor da classe mãe (super)
    public Carro(String marca, String modelo) {
        super(marca, modelo);
    }
    // Implementação obrigatória do método abstrato da classe mãe
    @Override
    public void emitirSom() {
        System.out.println(getModelo() + " faz: Bibi!");
    }
    public void setPortas(int N_portas) {
        this.qtde_portas = N_portas;
    }
    public int getPortas() {
        return this.qtde_portas;
    }
}