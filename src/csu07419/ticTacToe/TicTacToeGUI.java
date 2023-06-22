package csu07419.ticTacToe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame{

    JLabel playerTurn;
    JPanel rowPanel;
    JPanel rowLabelPanel;
    JLabel rowLabel;
    JPanel rowButtonsPanel;
    JPanel columnPanel;
    JPanel columnLabelPanel;
    JLabel columnLabel;
    JPanel columnButtonsPanel;
    JLabel positionSelected;
    JPanel bodyColumnPanel;

    JPanel gameBody;
    JLabel gameInfo;

    PlayBoard board;
    char currentPlayer = 'X';

    int row;
    int column;

    String gameProgress="IN PROGRESS";
    public TicTacToeGUI(){
        board= new PlayBoard();

       setTitle("TicTacToeGame");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        initializeAndAddingComponents();
        setUpListeners();


    }

    public void initializeAndAddingComponents(){
//        PLAYER TURN PART
        playerTurn=new JLabel("Player turn   "+ currentPlayer);
        playerTurn.setBounds(10, 10, 100, 30);
        add(playerTurn);

//        ROW LABEL WITH ITS BUTTONS PART
        rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBounds(10, 50, 405, 30);

        rowLabelPanel=new JPanel();
        rowLabel = new JLabel("ROW");
        rowLabelPanel.add(rowLabel);
        rowPanel.add(rowLabelPanel,BorderLayout.WEST);

        rowButtonsPanel=new JPanel(new GridLayout(1,8,2,0));
        for (int i = 0; i < 8; i++) {
            JButton button = new JButton(Integer.toString(i));
            rowButtonsPanel.add(button);
        }
        rowPanel.add(rowButtonsPanel,BorderLayout.CENTER);
        add(rowPanel,BorderLayout.WEST);

//        COLUMN LABEL WITH TS BUTTONS PART
        columnPanel = new JPanel(new BorderLayout());
        columnPanel.setBounds(10, 90, 405, 30);

        columnLabelPanel=new JPanel();
        columnLabel = new JLabel("COLUMN");
        columnLabelPanel.add(columnLabel);
        columnPanel.add(columnLabelPanel,BorderLayout.WEST);

        columnButtonsPanel=new JPanel(new GridLayout(1,8,2,0));
        for (int i = 0; i < 8; i++) {
            JButton button = new JButton(Integer.toString(i));
            columnButtonsPanel.add(button);
        }
        columnPanel.add(columnButtonsPanel,BorderLayout.CENTER);
        add(columnPanel,BorderLayout.WEST);

//        POSITION SELECTED PART
        positionSelected=new JLabel();
        positionSelected.setText("POSITION SELECTED     "+"["+row+","+column+"]");
        positionSelected.setBounds(10, 130, 500, 30);
        add(positionSelected);


//        BODY PART
        bodyColumnPanel = new JPanel(new GridLayout(1,board.columns));
        bodyColumnPanel.setBounds(50,170,500,30);
        bodyColumnPanel.add(new JLabel(" "));
        for (int j = 0; j < board.columns; j++) {
            JLabel bodyColumnLabel = new JLabel(Integer.toString(j));
            bodyColumnLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bodyColumnPanel.add(bodyColumnLabel);

        }
        add(bodyColumnPanel);

        gameBody = new JPanel(new GridLayout(board.rows, board.columns));
        gameBody.setBounds(50,200,500,200);

        for (int i = 0; i < board.rows; i++) {
            JLabel bodyRowLabel = new JLabel(Integer.toString(i));
            bodyRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gameBody.add(bodyRowLabel); // Add row number

            for (int j = 0; j < board.columns; j++) {
                JPanel emptyBoxPanel = new JPanel();
                emptyBoxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for empty box
                gameBody.add(emptyBoxPanel);
            }
        }
        add(gameBody, BorderLayout.CENTER);

//        GAME INFO PART
        gameInfo=new JLabel();
        gameInfo.setText("GAME INFO    "+gameProgress);
        gameInfo.setBounds(10,430,200,30);
        add(gameInfo);
    }

    public void setUpListeners(){
        // Listener for the row buttons
        ActionListener rowButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                row = Integer.parseInt(sourceButton.getText());
                positionSelected.setText("POSITION SELECTED     "+"["+row+","+column+"]");
            }
        };

        // Attaching the listener to the row buttons
        for (Component button : rowButtonsPanel.getComponents()) {
            if (button instanceof JButton) {
                ((JButton) button).addActionListener(rowButtonListener);
            }
        }

        // Listener for the column buttons
        ActionListener columnButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                column = Integer.parseInt(sourceButton.getText());
                positionSelected.setText("POSITION SELECTED     "+"["+row+","+column+"]");

                // Updating the play board with the selected position and current player
                board.placeToken(new TokenPosition(row, column), currentPlayer);

                // Updating the game body with the new move
                JPanel boxPanel = (JPanel) gameBody.getComponent(row * (board.columns + 1) + column + 1);
                boxPanel.removeAll();
                JLabel tokenLabel = new JLabel(Character.toString(currentPlayer));
                tokenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                boxPanel.add(tokenLabel);

                // Check for a win or tie
                if (board.checkWinner(new TokenPosition(row, column))) {
                    gameProgress ="Congrats " + currentPlayer + " WINS!";
                    gameInfo.setText("GAME INFO    "+gameProgress);
                    disableAllButtons();
                } else if (board.checkTie()) {
                    gameProgress = "It is a DRAW result!";
                    gameInfo.setText("GAME INFO    "+gameProgress);
                    disableAllButtons();
                }

                // Switch players
                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                playerTurn.setText("Player turn   "+ currentPlayer);
            }
        };

        // Attach the listener to the column buttons
        for (Component button : columnButtonsPanel.getComponents()) {
            if (button instanceof JButton) {
                ((JButton) button).addActionListener(columnButtonListener);
            }
        }
    }

    private void disableAllButtons() {
        // Disable row buttons
        for (Component button : rowButtonsPanel.getComponents()) {
            if (button instanceof JButton) {
                button.setEnabled(false);
            }
        }

        // Disable column buttons
        for (Component button : columnButtonsPanel.getComponents()) {
            if (button instanceof JButton) {
                button.setEnabled(false);
            }
        }
    }

    }


