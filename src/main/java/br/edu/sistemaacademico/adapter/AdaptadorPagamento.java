package br.edu.sistemaacademico.adapter;

/**
 * Padrão ADAPTER: traduz {@link RegistradorPagamento#registrar(double)} (reais)
 * para {@link SistemaLegado#registrarEmCentavos(int)} (centavos).
 *
 * <p>Limitação conhecida: {@code double} não representa todos os valores decimais com
 * exatidão (ex.: 19.99 * 100 = 1998.9999999999998). Por isso a conversão usa
 * {@link Math#round(double)}. Em sistemas financeiros reais, o ideal seria usar
 * {@code BigDecimal} ou centavos inteiros; aqui mantemos {@code double} para seguir o
 * contrato pedido na atividade.
 */
public class AdaptadorPagamento implements RegistradorPagamento {

    private final SistemaLegado legado;

    public AdaptadorPagamento(SistemaLegado legado) {
        if (legado == null) {
            throw new IllegalArgumentException("O sistema legado não pode ser nulo.");
        }
        this.legado = legado;
    }

    @Override
    public void registrar(double reais) {
        if (Double.isNaN(reais) || Double.isInfinite(reais) || reais <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        long centavos = Math.round(reais * 100);
        if (centavos > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Valor grande demais para o sistema legado.");
        }
        legado.registrarEmCentavos((int) centavos);
    }
}
