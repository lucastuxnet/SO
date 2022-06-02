package jPanel;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SequenciaPaginas {
    public int[] seq;


    public void definirAleatorio(int tamanho){
        this.seq = new int[tamanho];
        for(int i = 0; i < tamanho; i++){
            Random ran = new Random();
            int numero = ran.nextInt(9);
            this.seq[i] =  numero;
        }
    }

    public void definirComString(String listaSeq){
        /** formato do string : "1;2;3;4"   **/
        String stringArray[];
        stringArray = listaSeq.split(";");
        this.seq = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++){
            try{ 
                this.seq[i] = Integer.parseInt(stringArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("Numero com formato errado");
            }       
        }
    }

    public void escreverSequencia(){
        JFrame jan = new JFrame("Exemplo : 0;5;4;1;2");
        jan.setSize(500,150);
        JTextField entrada = new JTextField();
        JButton validationButton= new JButton("Validar");
        JButton aleatorio = new JButton("Aleatorio");
        validationButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                definirComString(entrada.getText());
                jan.setVisible(false);
            }
        });
        aleatorio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int num = rand.nextInt(50);
                definirAleatorio(num);
                jan.setVisible(false);
            }
        });
        jan.setLayout(new BorderLayout());
        String center = BorderLayout.CENTER;
        jan.getContentPane().add(entrada, center);
        String north = BorderLayout.NORTH;
        String south = BorderLayout.SOUTH;
        jan.getContentPane().add(aleatorio, south);
        jan.getContentPane().add(validationButton, north);
        jan.setVisible(true);
    }

    public int[] getSequencia(){ return seq; }
    public void setSequencia(int[] seq){ this.seq = seq; }

}