package br.edu.sistemaacademico.factory;

public class CriadorResumido extends CriadorComprovante {

    @Override
    protected Comprovante criar() {
        return new ComprovanteResumido();
    }
}
