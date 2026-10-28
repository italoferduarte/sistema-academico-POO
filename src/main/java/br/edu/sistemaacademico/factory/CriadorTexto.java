package br.edu.sistemaacademico.factory;

public class CriadorTexto extends CriadorComprovante {

    @Override
    protected Comprovante criar() {
        return new ComprovanteTexto();
    }
}
