package br.edu.sistemaacademico.adapter;

/** Sistema antigo, que só entende valores em centavos inteiros. Não é alterado pelo adaptador. */
public class SistemaLegado {

    public void registrarEmCentavos(int centavos) {
        System.out.println("[Sistema legado] Pagamento registrado: " + centavos + " centavos");
    }
}
