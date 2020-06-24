import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame{
    private JButton totalButton;
    private JButton exitButton;
    private JButton receptButton;
    private JButton resetButton;

    private JPanel HlavniPanel;
    private JTextPane textPane1;
    private JLabel CostMeat;
    private JLabel CostDrink;
    private JLabel CostItem;
    private JLabel CostTax;
    private JLabel Cost;

    private JSpinner meat0;
    private JSpinner meat1;
    private JSpinner meat2;
    private JSpinner meat3;
    private JSpinner meat4;

    private JSpinner drink0;
    private JSpinner drink1;
    private JSpinner drink2;
    private JSpinner drink3;
    private JSpinner drink4;

    //vkladam ceny do pole
    private JSpinner[][] jidloPole = {{meat0, meat1,meat2,meat3,meat4},{drink0,drink1,drink2, drink3, drink4}};
    private int[][] cena = {{145, 85, 120, 76, 90}, {40, 79, 45, 56, 69}};

    public Main() {
        setContentPane(HlavniPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new ExitClose();
            }
        });
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                compute();
            }
        });
        receptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                write();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ExitClose();
            }
        });
    }

    //pocita vypocti v dolni casti okna >>>
    public void compute(){
        //ukladam do 2d pole hodnoty z JSpinneru
        int[][] jidlo = {
                {(int) meat0.getValue(),(int) meat1.getValue(),(int) meat2.getValue(),(int) meat3.getValue(),(int) meat4.getValue()},
                {(int)drink0.getValue(),(int) drink1.getValue(),(int) drink2.getValue(),(int) drink3.getValue(),(int) drink4.getValue()}
        };

        int costMeat = 0, costDrink = 0;

        //uklada hodnoty a prochazi pole >>>
        for (int x=0;x<=jidlo.length-1;x++){
            costMeat += (jidlo[0][x]*cena[0][x]);
            costDrink += (jidlo[1][x]*cena[1][x]);
        }

        //vypocti zbivajicich hodnot
        int costTotal = costDrink+costMeat;
        double tax = (costTotal*0.21*100)/100;
        double cost = costTotal+tax;

        //nastavuje hodnoty do JLabel
        this.CostDrink.setText(costDrink+" kc");
        this.CostMeat.setText(costMeat+" kc");
        this.CostItem.setText(costTotal+" kc");
        this.CostTax.setText(tax+" kc");
        this.Cost.setText(cost+" kc");
    }
    public void write(){
        //neresit
    }
    //resetuje hodnoty na nulu
    public void reset(){
        //nastavy hodnoty 0 na JLabel
        this.CostDrink.setText("");
        this.CostMeat.setText("");
        this.CostItem.setText("");
        this.CostTax.setText("");
        this.Cost.setText("");

        for (int x = 0; x<jidloPole.length;x++){
            jidloPole[0][x].setValue(0);
        }
    }

    public static void main(String[] args) {
        JFrame x = new Main();

    }
}
