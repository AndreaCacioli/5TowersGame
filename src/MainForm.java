import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class MainForm extends JFrame
{
    private JButton button4;
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JButton button0;
    private JButton buttonHelp;
    private JButton buttonSolve;
    private JPanel mainPanel;
    private JButton randomizeButton;
    private JLabel movesLabel;
    private JLabel necessaryMoves;

    final private JButton[] inputButtons;
    State currentState;
    int moves;
    Color completedTile = Color.CYAN;
    Color clickableTile = Color.green;

    final String[] helpMessages = {"Hey", "Io?", "Mh"};

    MainForm()
    {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(800,900);
            this.add(mainPanel);
            this.pack();
            inputButtons=new JButton[5];
            inputButtons[0] = button0;
            inputButtons[1] = button1;
            inputButtons[2] = button2;
            inputButtons[3] = button3;
            inputButtons[4] = button4;
            updateMap(inputButtons, State.randomState());
            this.setVisible(true);
            randomizeButton.addActionListener(actionEvent -> {
                moves = 0;
                updateMap(inputButtons, State.randomState());
            });
            initializeButtonsListeners();

            buttonHelp.addActionListener(actionEvent -> {
                LinkedList<Move> moves = Towers.calc(currentState);
                Random r = new Random();
                String newMessage = null;
                String oldMessage = null;
                while(newMessage == null || newMessage.compareTo(oldMessage) == 0)
                {
                    newMessage = helpMessages[r.nextInt(helpMessages.length)];
                    oldMessage = inputButtons[moves.get(0).flippedTower].getText();
                    inputButtons[moves.get(0).flippedTower].setText(newMessage);
                }
            });
            buttonSolve.addActionListener(actionEvent -> {
                LinkedList<Move> moves = Towers.calc(currentState);
                for (int i = 0; i < moves.size(); i++)
                {
                    int index = moves.get(i).flippedTower;
                    inputButtons[index].doClick(500);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                }
            });
    }

    private void updateMap(JButton[] inputButtons, State state)
    {
        for(int i = 0; i < state.towers.length; i++)
        {
            if(!state.towers[i])
            {
                inputButtons[i].setText("X");
                inputButtons[i].setBackground(completedTile);
            }
            else
            {
                inputButtons[i].setText("O");
                inputButtons[i].setBackground(clickableTile);
            }
            inputButtons[i].setEnabled(!state.towers[i]);
            currentState = state;
        }
        movesLabel.setText("Moves: " + moves);
        necessaryMoves.setText("Necessary Moves: " + Towers.calc(currentState).size());
        if(currentState.isCompleted()) JOptionPane.showMessageDialog(null, "You are the best Dude!!!");
    }


    private void initializeButtonsListeners()
    {
        button0.addActionListener(actionEvent -> {
            moves++;
            currentState.flip(0);
            updateMap(inputButtons, currentState);
        });
        button1.addActionListener(actionEvent -> {
            moves++;
            currentState.flip(1);
            updateMap(inputButtons, currentState);
        });
        button2.addActionListener(actionEvent -> {
            moves++;
            currentState.flip(2);
            updateMap(inputButtons, currentState);
        });
        button3.addActionListener(actionEvent -> {
            moves++;
            currentState.flip(3);
            updateMap(inputButtons, currentState);
        });
        button4.addActionListener(actionEvent -> {
            moves++;
            currentState.flip(4);
            updateMap(inputButtons, currentState);
        });
    }

    public static void main(String[] args) {
        new MainForm();
    }
}
