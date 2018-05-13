import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View{

    public JFrame mainpanel= new JFrame("계산기");

    private int width_max = 800, height_max = 500;

    private JPanel keypadpanel = new JPanel();
    private JButton[] keypad = new JButton[25];

    private JLabel shownum = new JLabel("0", SwingConstants.RIGHT);
    private JLabel before_shownum = new JLabel("0", SwingConstants.RIGHT);
    private JLabel memory = new JLabel("계산 기록", SwingConstants.LEFT);

    private DefaultListModel<String> susic_memory = new DefaultListModel<>();
    private JList susic_list = new JList();

    private Model model = new Model();

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String[] test;
            int result;


            test = e.toString().substring(48, 53).split(",");
            System.out.println(test[0]);

            result = model.get_signal(test[0]);
            if(result == 1){
                shownum.setText(model.getNumber_input());
            }else if(result == 2){
                before_shownum.setText(model.getSave_last(2) + " " + model.getOperator());
                shownum.setText(model.getSave_last(1));

                susic_memory.add(0, before_shownum.getText() + " = " + shownum.getText());
                susic_list.setModel(susic_memory);

            }else if(result == 3){
                before_shownum.setText(model.getSave_last(3) + " " + model.getOperator() + " " + model.getSave_last(2));
                shownum.setText(model.getSave_last(1));

                susic_memory.add(0, before_shownum.getText() + " = " + shownum.getText());
                susic_list.setModel(susic_memory);

            }else if(result == 4){

                shownum.setText(model.getSave_last(1));


            }
        }
    };

    private void init_keypad(){

        int a;
        String[] key_word = {"%", "CE", "C", "DEL", "/", "sqrt", "7", "8", "9", "*", "x^2", "4", "5", "6", "-", "x^3", "1", "2", "3", "+", "1/x", "+/-", "0", ".", "="};

        keypadpanel.setLayout(new GridLayout(5, 5, 10, 10));

        for(a = 0; a < 25; a++){
            keypad[a] = new JButton(key_word[a]);
            keypad[a].setFont(new Font("Bitstream Sans Mono", Font.PLAIN, 18));
            keypad[a].addActionListener(listener);
            keypadpanel.add(keypad[a]);
        }

        keypadpanel.setBounds(0, this.height_max - 400, 450, 350);
    }

    public View(){

        init_keypad();
        shownum.setBounds(0, 0, 450 - 10, 140);
        shownum.setFont(new Font("Bitstream Sans Mono", Font.BOLD, 30));
        before_shownum.setBounds(0, 0, 450 - 10, 50);
        before_shownum.setFont(new Font("Bitstream Sans Mono", Font.BOLD, 20));
        memory.setBounds(480, 10, width_max - 30, 40);
        memory.setFont(new Font("나눔고딕", Font.BOLD, 25));
        susic_list.setBounds(480, 60, width_max - 30, height_max - 30);
        susic_list.setFont(new Font("Bitstream Sans Mono", Font.PLAIN, 25));

        mainpanel.setSize(this.width_max,this.height_max);
        mainpanel.add(keypadpanel);
        mainpanel.add(shownum);
        mainpanel.add(before_shownum);
        mainpanel.add(memory);
        mainpanel.add(susic_list);

        mainpanel.setLayout(null);
        mainpanel.setVisible(true);
        mainpanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
