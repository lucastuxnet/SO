package jPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class Simulacao {
    SequenciaPaginas paginas;
    int tipoSimulacao; //0: fifo, 1: otm, 2: lru, 3: sc, 4: wsm
    float hitRate; //em %
    int pageFaults = 0;
    int numeroQuadrosRam; // varia de 1 a 7
    JFrame frameSimu;
    JLabel pageFLabel;
    JLabel hitRLabel;

    public Simulacao(SequenciaPaginas paginas){
        this.pageFaults = 0;
        this.tipoSimulacao = 0;
        this.hitRate = 0;
        this.numeroQuadrosRam = 7;
        this.paginas = paginas;
        this.frameSimu = new JFrame("Simulacao");
        pageFLabel = new JLabel ("0");
        hitRLabel = new JLabel("0");

    }

    public float getHitRate(){ return this.hitRate; }
    public int getPageFaults(){ return this.pageFaults; }
    public int[] getSequenciaPaginas(){ return this.paginas.getSequencia(); }
    public void setNumeroQuadros(int numero){ this.numeroQuadrosRam = numero; }
    public int getNumeroQuadros(){ return this. numeroQuadrosRam; }
    public void addPageFault(){
        this.pageFaults += 1;
        pageFLabel.setText(Integer.toString(pageFaults));
    }
    public void setHitRate(){
        this.hitRate = (float) (1-(float) this.pageFaults/(float) paginas.getSequencia().length)*100;
        hitRLabel.setText(Float.toString(hitRate)); 
       }
    public void setTipoSimulacao(int tipo){ this.tipoSimulacao = tipo; }
    public void setSequencia(int[] paginas){ this.paginas.setSequencia(paginas); }

    public void gerarJanela(){ //geramento da janela
        this.frameSimu.setSize(1000,200);
        //criacao das linhas
        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l3 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();

        l1.setLayout(new BoxLayout(l1, BoxLayout.LINE_AXIS));
        l2.setLayout(new BoxLayout(l2, BoxLayout.LINE_AXIS));
        l3.setLayout(new BoxLayout(l3, BoxLayout.LINE_AXIS));
        l4.setLayout(new BoxLayout(l4, BoxLayout.LINE_AXIS));
        l5.setLayout(new BoxLayout(l5, BoxLayout.LINE_AXIS));
        l6.setLayout(new BoxLayout(l6, BoxLayout.LINE_AXIS));
        JButton fechar = new JButton("FECHAR PROGRAMA");
        fechar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                System.exit(0);
            }
        });
        l1.add(new JLabel("Numeros Paginas : "));
        l1.add(new JLabel(Arrays.toString(paginas.getSequencia())));
        l2.add(new JLabel("Page Faults : "));
        l2.add(this.pageFLabel);
        l3.add(new JLabel("Hit Rate : "));
        l3.add(this.hitRLabel);
        l6.add(fechar);
        JLabel algoritmo;
        if(tipoSimulacao == 0){
            algoritmo = new JLabel("Algoritmo : FIFO - First In First Out");
        }else if(tipoSimulacao ==1){
            algoritmo = new JLabel("Algoritmo : OTM - Optimal");
        }else if(tipoSimulacao == 2){
            algoritmo = new JLabel("Algoritmo : LRU - Last Recently Used");
        }else if(tipoSimulacao == 3){
            algoritmo = new JLabel("Algoritmo : SC - Second Chance");
        }else if(tipoSimulacao == 4){
            algoritmo = new JLabel("Algoritmo : WSM - Working Set Model");
        }else{
            algoritmo = new JLabel("Algoritmo : none");
        }
        l4.add(algoritmo);
        l5.add(new JLabel("Quantidade Quadros de RAM : "));
        l5.add(new JLabel(Integer.toString(this.getNumeroQuadros())));

        
        JPanel boxsimu = new JPanel();
        boxsimu.setLayout(new BoxLayout(boxsimu, BoxLayout.PAGE_AXIS));
        boxsimu.add(l1);
        boxsimu.add(l2);
        boxsimu.add(l3);
        boxsimu.add(l4);
        boxsimu.add(l5);
        boxsimu.add(l6);

        this.frameSimu.getContentPane().add(boxsimu);
        this.frameSimu.setVisible(true);
    }

    public void simular(){
        if(tipoSimulacao == 0){
            simularFifo();
        }else if(tipoSimulacao == 1){
            simularOtm();
        }else if(tipoSimulacao == 2){
            simularLru();
        }else if(tipoSimulacao == 3){
            simularSc();
        }else if(tipoSimulacao == 4){
            simularWsm();
        }
        this.frameSimu.setVisible(false);
        this.pageFLabel.setText(Integer.toString(this.getPageFaults()));
        this.setHitRate();
        this.hitRLabel.setText(Float.toString(this.getHitRate()));

    }

    public void simularFifo(){                                      /**SIMULACAO FIFO */
                                            /** escolhe para sai a página que entrou na memória há mais tempo */
        int[] pages = getSequenciaPaginas();
        int capacity = getNumeroQuadros();  

        //Para representar o conjunto de páginas atuais é usado
        // um conjunto não ordenado (Set)
        HashSet<Integer> pagesSet = new HashSet<>(capacity);      
        //  Usamos o Queue para podermos armazenar as páginas
        // no modo FIFO (fila ordenada, primeiro que entrar vai ser o primeiro a sair)
        Queue<Integer> indexes = new LinkedList<>() ;

        // Começa da página inicial
        for (int i=0; i<pages.length; i++)
        {
            // Verifica se o conjunto pode conter mais páginas
            if (pagesSet.size() < capacity)
            {
                // Insere a página no conjunto se ainda não estiver presente,
                // o que representa uma page fault
                if (!pagesSet.contains(pages[i]))
                {
                    pagesSet.add(pages[i]);

                    // Incrementa a quantidade de page faults
                    addPageFault();

                    // Coloca a página atual na fila
                    indexes.add(pages[i]);
                }
            }

            // Se o conjunto de páginas estiver cheio, será necessário realizar FIFO
            // Remove a primeira página da fila tanto do conjunto (pagesSet)
            // quanto da fila (indexes) e insere a página atual (pages[i])
            else
            {
                // Verfica se a página atual já não está dentro do conjunto
                if (!pagesSet.contains(pages[i]))
                {
                    //A função .peek() retorna o primeiro elemento da fila, sem removê-lo
                    //Aqui guardamor o primeiro elemento da fila na variável val
                    int val = indexes.peek();

                    //Aqui nós removemos o primeiro elemento da fila com o .poll()
                    indexes.poll();

                    // Aqui removemos a página de index guardada em val do conjunto de páginas
                    pagesSet.remove(val);

                    // Inserimos a página atual
                    pagesSet.add(pages[i]);

                   // Colocamos a página atual na fila
                    indexes.add(pages[i]);

                    // Incrementa a quantidade de page faults
                    addPageFault();
                }
            }
        }
        setHitRate();
    }


    public void simularOtm(){                                       /** SIMULACAO OTM */
                                        /** escolhe para sair a página que levará  mais tempo para ser novamente necessária */
        int[] paginas = getSequenciaPaginas();
        int n = getNumeroQuadros();
        int[] memoria = new int[n];
        int[] tempo = new int[n];
        for(int i = 0; i<paginas.length; i++){
            /**
            System.out.print("i = ");
            System.out.println(i);
            System.out.print("memoria = ");
            System.out.println(Arrays.toString(memoria));
            */
            Boolean hit = false;
            int jmax = 0;
            int tempomax = 0;
            for(int j = 0; j<n; j++){
                System.out.print("j = ");
                System.out.println(j);
                if(memoria[j]==paginas[i]){                         //verifica se existe a pagina no bloco de memoria
                    hit = true;
                }  
                /**
                System.out.print("hit = ");
                System.out.println(Boolean.toString(hit));
                */
                int k = i;
                while(( k<paginas.length-1 )&&( memoria[j] != paginas[k])){        //atualiza o tempo para a proxima aparição dessa pagina
                    k++;
                    /**
                    System.out.print("k = ");
                    System.out.println(k);
                    System.out.print("memoria[j] != paginas[k] ? ");
                    System.out.println(Boolean.toString(memoria[j] != paginas[k]));
                    */
                }
                tempo[j] = k;
                if(k > tempomax){                                   //acha o valor de tempo maximo
                    tempomax = k;
                    jmax = j;
                }
                /** 
                System.out.print("tempo max = ");
                System.out.print(tempomax);
                System.out.print(" ; jmax = ");
                System.out.println(jmax);
                System.out.println("");
                */
            }
            if(!hit){                                               //se o endereço de pagina falhou:
                addPageFault();
                memoria[jmax] = paginas[i];                         //alocar no bloco que contem a pagina com mais tempo de espera
            }
        }
        setHitRate();
    }


    public void simularLru(){                                   /** SIMULACAO LRU */
                                        /** escolhe para sair a página que não é referenciada há mais tempo */
        int[] paginas = getSequenciaPaginas();
        int n = getNumeroQuadros();
        int[] memoria = new int[n];

        int[] historico = new int[n];

        for(int i = 0; i<n; i++){
            memoria[i] = -1;
            historico[i] = -1;
        }

        for(int i = 0; i<paginas.length; i++){              //para cada valor da lista de paginas: 
            Boolean presencia = false;   
            for(int j = 0; j<n; j++){                       //o endereco da pagina ja esta presente na memoria ?
                if(paginas[i] == memoria[j]){       
                    presencia = true; 
                }
            }

            if(presencia){                                  //se sim:

                int j=0;

                while(historico[j]!=paginas[i]){            //achar a posicao do endereco no historico
                    j++;

                }
                int enderecoHistorico = j;
                int temp = historico[enderecoHistorico];

                for(j=enderecoHistorico-1; j>=0; j--){      //atualizar o historico
                    historico[j+1]=historico[j];
                }
                historico[0] = temp;
            }else{                                          //se não:
                addPageFault();
                int ultimo = historico[n-1];                //verificar o ultimo endereco de pagina a ter sido chamado

                int j=0;

                while(memoria[j]!= ultimo){                 //achar a posicao desse ultimo endereco na memoria
                    j++;
                }
                int enderecoMemoria = j;

                memoria[enderecoMemoria]=paginas[i];        //atualizar valor na memoria

                for(j=n-2;j>=0;j--){
                    historico[j+1]=historico[j];            //atualizar o historico
                }
                historico[0] = paginas[i];
            }
        }
        setHitRate();
    }


    public void simularSc(){
        int[] paginas = getSequenciaPaginas();
        int frames = getNumeroQuadros();
        int[] arr = new int[frames];

        int pointer,i,l,x,pf;

        // Inicialmente consideramos que o quadro 0 deve ser substituído
        pointer = 0;

        // Número de page faults
        pf = 0;

        // Nenhuma página inicialmente no quadro,
        // que é indicado pelo -1
        Arrays.fill(arr,-1);

        // Criamos um array de second chance
        boolean second_chance[] = new boolean[frames];

        // Pega o tamanho do array
        l = paginas.length;

        for(i = 0; i<l; i++)
        {

            x = paginas[i];
            //achar um update
            Boolean found = false;
            for(int j = 0; j < frames; j++)
            {
                
                if(arr[j] == x)
                {
                    // Marca que a página é apta para second chance
                    second_chance[j] = true;
    
                    // found = true, já que houve um hit (já existente)
                    // assim, não há necessidade de substituir nenhuma página
                    found = true;
                }
            }
            // Encontra se existe a necessidade de substituir
            // qualquer página
            if(!found)
            {
                // Seleciona e atualiza a página vítima
                while(second_chance[pointer])
                {
                    // Marcado como false, já que tem uma chance
                    // e será substituído na próxima vez, a menos que seja acessado novamente
                    second_chance[pointer] = false;
        
        
                    pointer = (pointer+1)%frames;
                }
                // Aqui nós achamos a página para substituir
                if(!second_chance[pointer])
                {
                    // Substitui com a nova página
                    arr[pointer] = x;
    
    
                    pointer = (pointer+1)%frames;
                }
                //Atualiza a quantidade de page faults
                addPageFault();
            }
        }
        setHitRate();
    }


    public void simularWsm(){                                                       /** SIMULACAO WSM */
        long tempoInicial = System.currentTimeMillis();
        int[] paginas = getSequenciaPaginas();
        int n = getNumeroQuadros();
        int[] memoria = new int[n];
        long[] tempos = new long[n];
        long tmax;
        int jmax;
        long tau = 40;                                                                  //tau = 40ms tempo do working set
        for(int i=0; i<n; i++){                                                         //initialização
            memoria[i] = -1;
            tempos[i] = System.currentTimeMillis()-tempoInicial;
        }
        for(int i = 0; i<paginas.length; i++){ 
            tmax = 0;
            jmax = 0; 
            Boolean hit = false;
            for(int j = 0; j<n; j++){                                                   //o endereco da pagina ja esta presente na memoria ?
                /**
                System.out.println("");
                System.out.print("i : ");
                System.out.print(i);
                System.out.print("; j : ");
                System.out.println(j);
                System.out.print("Temps : ");
                System.out.println(System.currentTimeMillis()-tempoInicial);
                System.out.print("tempos : ");
                System.out.println(Arrays.toString(tempos));
                System.out.print("memoria : ");
                System.out.println(Arrays.toString(memoria));
                System.out.print("pagina : ");
                System.out.println(paginas[i]);
                */
                if(paginas[i] == memoria[j]){                                           // Se sim :      
                    tempos[j] = System.currentTimeMillis()-tempoInicial;                //reiniciar tempo no endereco
                    hit = true;
                }else if(System.currentTimeMillis()-tempoInicial-tempos[j] > tau){      //Se não e o tempo de alocacao > tau :                       
                    tmax = tau;                                                         //priorizar a alocacao nesse endereco em caso de falha
                    jmax = j;
                }else{                                                                  //se não e o tempo de alocacao <= tau :                                                      
                    if(System.currentTimeMillis()-tempoInicial-tempos[j] > tmax){       //atualizar tempo maximo
                        tmax = System.currentTimeMillis()-tempoInicial-tempos[j];
                        jmax = j;
                    }
                }
                /**
                System.out.print("tmax : ");
                System.out.println(tmax);
                System.out.print("jmin : ");
                System.out.println(jmax);
                */
                if(j == n-1){ 
                    if(!hit){                                                           //se parcorer toda a memoria sem achar onde alocar a pagina:
                        addPageFault();                                                 // adicionar um Page Fault
                        memoria[jmax] = paginas[i];                                     //alocar pagina nesse endereco de memoria
                        tempos[jmax] = System.currentTimeMillis()-tempoInicial;         //reiniciar tempo do endereco
                    }
                }
            }
        }
        setHitRate();
    }

}   