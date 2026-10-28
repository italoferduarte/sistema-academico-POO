# Sistema Acadêmico Simplificado — Atividade Integradora (Java e POO)

## Contextualização
Trabalho da **UniAteneu**, disciplina **Laboratório de Programação Orientada a Objetos**, tema *Sistema Acadêmico Simplificado* (conteúdos das Aulas 01 a 08). A **Parte I** (questões teóricas) é entregue em TXT próprio; este repositório contém a **Parte II** (implementação prática).

## Objetivo
Implementar, em Java 21 e no terminal, um fluxo de matrícula que demonstra POO (encapsulamento, associação, herança, polimorfismo) e seis padrões de projeto: Singleton, Strategy, Observer, Adapter, Factory Method e Facade.

## Tecnologias
Java 21 · Maven (opcional) · JUnit 5 · Git/GitHub · VS Code · UTF-8. Nenhuma biblioteca externa em produção.

## Funcionalidades
- `Aluno` e `Curso` com validação e encapsulamento; aluno mantém uma `List<Curso>`.
- Descontos intercambiáveis (0%, 10%, 20%).
- Pagamento registrado em centavos por um sistema legado, via adaptador.
- Notificações simuladas (e-mail e auditoria) após matrícula bem-sucedida.
- Comprovante completo ou resumido.
- Fachada que executa matrícula + comprovante em uma chamada.

## Padrões de projeto
| Padrão | Onde |
|---|---|
| Singleton | `config/Configuracao` |
| Strategy | `strategy/Desconto` + 3 implementações |
| Observer | `observer/ObservadorMatricula`, `AvisoEmail`, `Auditoria` (assinados em `ServicoMatricula`) |
| Adapter | `adapter/AdaptadorPagamento` (`RegistradorPagamento` → `SistemaLegado`) |
| Factory Method | `factory/CriadorComprovante` → `CriadorTexto`/`CriadorResumido` |
| Facade | `facade/FachadaAcademica` |

Detalhes em [`docs/PADROES_DE_PROJETO.md`](docs/PADROES_DE_PROJETO.md).

## Estrutura de pastas
```
sistema-academico/
├── pom.xml
├── README.md
├── .gitignore
├── src/main/java/br/edu/sistemaacademico/
│   ├── Main.java
│   ├── model/      Aluno, Curso
│   ├── config/     Configuracao
│   ├── strategy/   Desconto, SemDesconto, DescontoEstudante, DescontoPromocional
│   ├── observer/   ObservadorMatricula, AvisoEmail, Auditoria
│   ├── adapter/    RegistradorPagamento, SistemaLegado, AdaptadorPagamento
│   ├── factory/    Comprovante, ComprovanteTexto, ComprovanteResumido,
│   │               CriadorComprovante, CriadorTexto, CriadorResumido
│   ├── service/    ServicoMatricula
│   ├── facade/     FachadaAcademica
│   └── util/       FormatadorMoeda
├── src/test/java/br/edu/sistemaacademico/   (7 classes de teste, 26 testes)
└── docs/
    ├── ANALISE_IMPLEMENTACAO.md
    ├── PADROES_DE_PROJETO.md
    ├── ROTEIRO_APRESENTACAO.md
    ├── RELATORIO_EXECUCAO.md
    ├── parte1/        (colocar aqui o PDF da Parte I)
    └── evidencias/    (colocar aqui execucao.png)
```

## Pré-requisitos
- JDK 21 (comando `javac -version` deve mostrar 21).
- Maven 3.9+ (opcional, para testes automatizados). Sem Maven, veja "Compilar sem Maven".
- VS Code com a extensão *Extension Pack for Java*.

## Abrir no VS Code
`Arquivo > Abrir Pasta...` e escolha a pasta `sistema-academico`. A extensão reconhece o `pom.xml` automaticamente.

## Compilar e executar
Com Maven:
```
mvn compile exec:java
```
Compilar sem Maven (a partir da raiz do projeto):
```
# Linux/macOS
mkdir -p out && javac -encoding UTF-8 -d out $(find src/main -name "*.java") && java -cp out br.edu.sistemaacademico.Main

# Windows (PowerShell)
mkdir out -Force; javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main -Filter *.java).FullName; java -cp out br.edu.sistemaacademico.Main
```
> Se os acentos aparecerem trocados no terminal do Windows, rode `chcp 65001` antes ou use `java -Dstdout.encoding=UTF-8 ...`.

## Rodar os testes
```
mvn test
```
Os testes usam JUnit 5 (baixado pelo Maven na primeira execução).

## Saída esperada
```
Sistema: Sistema Acadêmico
[Sistema legado] Pagamento registrado: 18000 centavos
[E-mail simulado] Matrícula realizada: aluno Ana no curso Java (valor final R$ 180,00)
[Auditoria] Registro: Matrícula realizada: aluno Ana no curso Java (valor final R$ 180,00)
=== COMPROVANTE DE MATRÍCULA ===
Aluno: Ana
Curso: Java
Valor pago: R$ 180,00
================================
Cursos de Ana: 1
```
Esta é a saída observada na execução real feita durante o desenvolvimento (ver `docs/RELATORIO_EXECUCAO.md`).

## Como cada padrão foi aplicado (resumo)
- **Singleton:** `Configuracao` tem construtor privado, instância `static final` e `getInstancia()`.
- **Strategy:** `ServicoMatricula` recebe um `Desconto` no construtor; trocar a política não muda o serviço.
- **Observer:** `ServicoMatricula` guarda uma lista de `ObservadorMatricula` e os avisa só depois da matrícula concluída.
- **Adapter:** `AdaptadorPagamento` implementa `RegistradorPagamento` (reais) e chama `SistemaLegado` (centavos) com `Math.round`.
- **Factory Method:** `CriadorComprovante.criar()` é abstrato; cada subclasse decide qual `Comprovante` instanciar.
- **Facade:** `FachadaAcademica.matricularEEmitir` esconde serviço + comprovante, deixando `Main` simples.

## Critérios de validação
- [x] Compila com Java 21 · [x] `Main` executa o fluxo completo · [x] descontos 200 → 200/180/160
- [x] adaptador converte reais em centavos · [x] observadores só notificados em sucesso
- [x] dois comprovantes · [x] aluno guarda o curso · [x] 26 testes passaram (ver relatório para a ressalva sobre o executor)

## Enviar ao GitHub
1. Crie um repositório **vazio** no GitHub (sem README) e copie a URL.
2. Na raiz do projeto:
```
git init                      # só se ainda não for um repositório
git status                    # confere os arquivos alterados
git add .
git commit -m "Atividade Integradora: Sistema Acadêmico Simplificado (Java e POO)"
git branch -M main
git remote add origin <URL-DO-SEU-REPOSITORIO>     # exemplo: https://github.com/SEU-USUARIO/sistema-academico.git
git push -u origin main
```
3. Não envie senhas, tokens ou arquivos `.env` (o `.gitignore` já bloqueia `.env`).

## Informações para a entrega acadêmica
- Aluno: Italo Fernandes Duarte · UniAteneu · Laboratório de POO · Atividade Integradora (Aulas 01–08).
- Entregar: link do repositório, PDF da Parte I e captura de tela da execução (`docs/evidencias/execucao.png`).
