package jPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        JFrame fen = new JFrame("Projeto Final Sistemas Operacionais 26/10/2021");
        fen.setSize(600,400);

        /** CRIACAO DA INTERFACE DE COMECO */

        JPanel l1 = new JPanel();
        JPanel l2 = new JPanel();
        JPanel l3 = new JPanel();
        JPanel l4 = new JPanel();
        JPanel l5 = new JPanel();
        JPanel l6 = new JPanel();
        JPanel l7 = new JPanel();
        JPanel l8 = new JPanel();
        JPanel l9 = new JPanel();
        JPanel l10 = new JPanel();
        JPanel l11 = new JPanel();
        JPanel l12 = new JPanel();

        l1.setLayout(new BoxLayout(l1, BoxLayout.LINE_AXIS));
        l2.setLayout(new BoxLayout(l2, BoxLayout.LINE_AXIS));
        l3.setLayout(new BoxLayout(l3, BoxLayout.LINE_AXIS));
        l4.setLayout(new BoxLayout(l4, BoxLayout.LINE_AXIS));
        l5.setLayout(new BoxLayout(l5, BoxLayout.LINE_AXIS));
        l6.setLayout(new BoxLayout(l6, BoxLayout.LINE_AXIS));
        l7.setLayout(new BoxLayout(l7, BoxLayout.LINE_AXIS));
        l8.setLayout(new BoxLayout(l8, BoxLayout.LINE_AXIS));
        l9.setLayout(new BoxLayout(l9, BoxLayout.LINE_AXIS));
        l10.setLayout(new BoxLayout(l10, BoxLayout.LINE_AXIS));
        l11.setLayout(new BoxLayout(l11, BoxLayout.LINE_AXIS));
        l1.add(new JLabel("Professor: Marcelo Zanchetta do Nascimento"));
        l2.add(new JLabel("Igor Augusto Costa e Souza - 11221EMT008"));
        l3.add(new JLabel("João Victor de Oliveira - 11611BSI215"));
        l4.add(new JLabel("Lucas Albino Martins - 12011ECP022"));
        l5.add(new JLabel("Nicolas Fischmann - 12011EMT032"));
        JButton fifo = new JButton("FIFO - First In First Out");
        //fen.getContentPane().add(fifo);
        l6.add(fifo);
        JButton otm = new JButton("OTM - Optimal");
        //fen.getContentPane().add(otm);
        l7.add(otm);
        JButton lru = new JButton("LRU - Least Recently Used");
        //fen.getContentPane().add(lru);
        l8.add(lru);
        JButton sc = new JButton("SC - Second-Chance");
        //fen.getContentPane().add(sc);
        l9.add(sc);
        JButton wsm = new JButton("WSM - Working-Set Model");
        //fen.getContentPane().add(wsm);
        l10.add(wsm);
        JButton lancar = new JButton("comecar");
        l12.add(lancar);
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(l1);
        box.add(l2);
        box.add(l3);
        box.add(l4);
        box.add(l5);
        box.add(l6);
        box.add(l7);
        box.add(l8);
        box.add(l9);
        box.add(l10);
        box.add(l11);
        box.add(l12);
        
        

        /** DEFINICAO DAS AÇÕES DOS BOTTÕES */
        SequenciaPaginas sequencia = new SequenciaPaginas();
        Simulacao simu = new Simulacao(sequencia);

        fifo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sequencia.escreverSequencia();
                simu.setTipoSimulacao(0);
                l11.setVisible(true);
            }
        });

        otm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sequencia.escreverSequencia();
                simu.setTipoSimulacao(1);
                l11.setVisible(true);
            }
        });

        lru.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sequencia.escreverSequencia();
                simu.setTipoSimulacao(2);
                l11.setVisible(true);
            }
        });

        sc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sequencia.escreverSequencia();
                simu.setTipoSimulacao(3);
                l11.setVisible(true);
            }
        });

        wsm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                sequencia.escreverSequencia();
                simu.setTipoSimulacao(4);
                l11.setVisible(true);
            }
        });

        lancar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                simu.simular();
                simu.gerarJanela();
                fen.setVisible(false);
            }
        });
        l11.add(new JLabel("Quantidade Quadros de RAM : "));
        JButton um = new JButton("1");
        um.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(1);
                l12.setVisible(true);
            }
        });
        JButton dois = new JButton("2");
        dois.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(2);
                l12.setVisible(true);
            }
        });
        JButton treis = new JButton("3");
        treis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(3);
                l12.setVisible(true);
            }
        });
        JButton quatro = new JButton("4");
        quatro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(4);
                l12.setVisible(true);
            }
        });
        JButton cinco = new JButton("5");
        cinco.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(5);
                l12.setVisible(true);
            }
        });
        JButton seis = new JButton("6");
        seis.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(6);
                l12.setVisible(true);
            }
        });
        JButton sete = new JButton("7");
        sete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(7);
                l12.setVisible(true);
            }
        });
        JButton oito = new JButton("8");
        oito.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(8);
                l12.setVisible(true);
            }
        });

        JButton nove = new JButton("9");
        nove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                simu.setNumeroQuadros(9);
                l12.setVisible(true);
            }
        });
        l11.add(um);
        l11.add(dois);
        l11.add(treis);
        l11.add(quatro);
        l11.add(cinco);
        l11.add(seis);
        l11.add(sete);
        l11.add(oito);
        l11.add(nove);
        fen.getContentPane().add(box);
        fen.setVisible(true);
        l11.setVisible(false);
        l12.setVisible(false);
        
        
        
    }
}
