## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

.JAR : finaLSO.jar

COMO USAR O APLICATIVO:
-lancar o .jar
-escolher o metodo de alocação de memoria virtual
-uma janela se abre para definir a lista de endereços de páginas.
        -escrever a lista no campo de texo no formato seguinte: "1;2;3;4;5" sem os "" e apertar validar
        -ou apertar aleatorio
-a janela se fecha
-escolher a quantidade de blocos de memoria RAM
-apertar o bottão "começar"
-uma janela se abre com os resultados da simulação

CLASSES:
-App.java : Main Class
    -Lança a interface de começo para escolher o algoritmo de alocação de memoria, o numéro de blocos de memoria de RAM e a lista de endereços de Páginas

-SequenciaPaginas : Class
    -Permite escrever a lista de edereços de Páginas na mão, ou gerar uma lista aleatoria.

-Simulação : Class
    - Executa a Simulação em função dos parametros definidos antes.
    Methodes:
    - Getters e Setters,
    - gerarJanela : abre a janela apos a simulação terminada para mostrar os resultados
    - simulacao : escolhe a boa simulação para fazer, executa ela, e abre a janela com os resultados
    - simulacaoFifo : simula com um algoritmo FIFO
    - simulacaoLru : simula com um algoritmo LRU
    - simulacaoSc : simula com um algoritmo de SC
    - simulacaoOtm : simula com um algoritmo Otm
    - simulacaoWsm : simula com o modelo Wsm

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.


