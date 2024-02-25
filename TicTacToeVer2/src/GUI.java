import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
class GUI {
    private int size = 3; private int winLine = 3;
    private int totalCells = size * size; private int emptyCellCount = totalCells;
    private int ScoreP1 = 0; int ScoreP2 = 0;
    final String turnP1String = "Ход игрока 1 - X";
    final String turnP2String = "Ход игрока 2 - O";
    final String winP1 = "Победил игрок 1 - X";
    final String winP2 = "Победил игрок 2 - O";
    private boolean GameOver = false;
    boolean player1Turn = true; boolean player2Turn = false;
    final String player1Symbol = "X"; final String player2Symbol = "O";
    private JPanel fieldButtons;
    private JPanel gamePanel;
    private JPanel choosePanel;
    private JButton[][] buttonArray;
    private JComboBox<Integer> winLineBox;
    private JComboBox<Integer> sizeBox;
    private JButton newGameButton;
    private JButton cleanFieldButton;
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JButton resetScoreButton;
    JFrame window = new JFrame();
    void gui() {
        // создание окна и панелей

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        choosePanel = new JPanel();
        gamePanel = new JPanel();
        // настройка окна
        window.setTitle("Крестики-нолики");
        window.setPreferredSize(new Dimension(740, 625));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //настройка splitPane
        splitPane.setDividerLocation(213);
        splitPane.setLeftComponent(choosePanel);
        splitPane.setRightComponent(gamePanel);

        fillChoosePanel(); //Заполнение choosePanel
        fillGamePanel();// содержимое gamePanel
        // добавление в панелей в окно
        window.getContentPane().add(splitPane);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    private void fillField() {
        buttonArray = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttonArray[i][j] = new JButton();
                buttonArray[i][j].addActionListener(this::actionPerformed);
                buttonArray[i][j].setBackground(Color.WHITE);
                buttonArray[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 300 / size));
                buttonArray[i][j].setBorder(new LineBorder(Color.black));
                buttonArray[i][j].setForeground(Color.BLACK);
                buttonArray[i][j].setOpaque(true);
                fieldButtons.add(buttonArray[i][j]);
            }
        }
        fieldButtons.setLayout(new GridLayout(size, size, 0, 0));
    }
    private void fillChoosePanel() {
        Font labelFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        Font boldFont = new Font("Comic Sans MS", Font.BOLD, 16);
        JLabel sizeLabel = new JLabel("Размер поля");
        sizeLabel.setPreferredSize(new Dimension(160, 40));
        sizeLabel.setHorizontalAlignment(0);
        sizeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        sizeLabel.setForeground(Color.white);
        sizeBox = new JComboBox<>();
        //заполнение списка sizeBox
        final DefaultComboBoxModel<Integer> defComboBoxSize = new DefaultComboBoxModel<>();
        for (int i = 3; i < 20; i++) {
            defComboBoxSize.addElement(i);
        }
        sizeBox.setModel(defComboBoxSize);
        sizeBox.setFont(boldFont);
        sizeBox.setBackground(Color.lightGray);
        sizeBox.setBorder(new LineBorder(Color.black));
        sizeBox.setEditable(false);
        sizeBox.setPreferredSize(new Dimension(160, 40));
        sizeBox.addActionListener(this::SizeComboBoxActionPerformed);
        JLabel winLineLabel = new JLabel("Размер линии победы");
        winLineLabel.setPreferredSize(new Dimension(160, 40));
        winLineLabel.setHorizontalAlignment(0);
        winLineLabel.setFont(labelFont);
        winLineLabel.setForeground(Color.white);
        winLineBox = new JComboBox<>();
        // заполение списка winLine
        final DefaultComboBoxModel<Integer> defComboBoxLine = new DefaultComboBoxModel<>();
        defComboBoxLine.addElement(3);
        winLineBox.setModel(defComboBoxLine);
        winLineBox.setFont(boldFont);
        winLineBox.setBackground(Color.lightGray);
        winLineBox.setBorder(new LineBorder(Color.black));
        winLineBox.addActionListener(this::SizeComboBoxActionPerformed);
        winLineBox.setEditable(false);
        winLineBox.setPreferredSize(new Dimension(160, 40));
        newGameButton = new JButton("Новая игра");
        newGameButton.setPreferredSize(new Dimension(160, 40));
        newGameButton.setBackground(Color.lightGray);
        newGameButton.setBorder(new BasicBorders.MenuBarBorder(Color.black,Color.lightGray));
        newGameButton.setFont(boldFont);
        newGameButton.addActionListener(this::actionPerformed);
        cleanFieldButton = new JButton("Очистить поле");
        cleanFieldButton.setPreferredSize(new Dimension(160, 40));
        cleanFieldButton.setBackground(Color.lightGray);
        cleanFieldButton.setBorder(new BasicBorders.MenuBarBorder(Color.black,Color.lightGray));
        cleanFieldButton.setFont(boldFont);
        cleanFieldButton.addActionListener(this::actionPerformed);
        resetScoreButton = new JButton("Сбросить счёт");
        resetScoreButton.setPreferredSize(new Dimension(160, 40));
        resetScoreButton.setBackground(Color.lightGray);
        resetScoreButton.setBorder(new BasicBorders.MenuBarBorder(Color.black,Color.lightGray));
        resetScoreButton.setFont(boldFont);
        resetScoreButton.addActionListener(this::actionPerformed);

        choosePanel.setLayout(new GridBagLayout());
        GridBagConstraints conChoose = new GridBagConstraints();

        conChoose.gridx = 1;
        conChoose.gridy = 0;
        choosePanel.add(sizeLabel, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 1;

        choosePanel.add(sizeBox, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 2;
        choosePanel.add(winLineLabel, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 3;
        choosePanel.add(winLineBox, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 4;
        conChoose.insets = new Insets(40, 0, 0, 0);
        choosePanel.add(newGameButton, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 5;
        conChoose.insets = new Insets(20, 0, 0, 0);
        choosePanel.add(cleanFieldButton, conChoose);

        conChoose.gridx = 1;
        conChoose.gridy = 6;
        conChoose.insets = new Insets(20, 0, 0, 0);
        choosePanel.add(resetScoreButton, conChoose);

        choosePanel.setBackground(Color.darkGray);
    }
    private void fillGamePanel() {
        gamePanel.setSize(960, 720);
        gamePanel.setMaximumSize(new Dimension(980, 720));
        gamePanel.setPreferredSize(new Dimension(830, 740));
        gamePanel.setMinimumSize(new Dimension(670, 460));
        gamePanel.setBackground(Color.lightGray);
        playerLabel = new JLabel(turnP1String);
        playerLabel.setPreferredSize(new Dimension(320, 40));
        playerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        scoreLabel = new JLabel(ScoreP1 + " - " + ScoreP2);
        scoreLabel.setPreferredSize(new Dimension(320, 40));
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        // заполнение поля
        fieldButtons = new JPanel();
        fieldButtons.setMaximumSize(new Dimension(640, 640));
        fieldButtons.setPreferredSize(new Dimension(570, 570));
        fieldButtons.setMinimumSize(new Dimension(480, 480));
        fieldButtons.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        fillField(); // создание fieldButtons

        //заполнение gamePanel
        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints conGame = new GridBagConstraints();

        conGame.anchor = GridBagConstraints.PAGE_START;
        conGame.fill = GridBagConstraints.NONE;
        conGame.gridx = 1;
        conGame.gridy = 0;
        conGame.insets = new Insets(20, 0, 0, 0);
        gamePanel.add(playerLabel, conGame);

        conGame.anchor = GridBagConstraints.CENTER;
        conGame.gridx = 1;
        conGame.gridy = 2;
        int h = gamePanel.getHeight() - playerLabel.getHeight() * 2;
        double wx = 0;
        for (double i = 0; gamePanel.getWidth() * i < h + 1; i += 0.01) {
            if (gamePanel.getWidth() * i == h) wx = i;
        }
        conGame.weightx = wx;
        gamePanel.add(fieldButtons, conGame);

        conGame.anchor = GridBagConstraints.PAGE_END;
        conGame.fill = GridBagConstraints.NONE;
        conGame.gridx = 1;
        conGame.gridy = 4;
        conGame.insets = new Insets(5, 0, 15, 0);
        gamePanel.add(scoreLabel, conGame);
        gamePanel.setOpaque(true);
    }
    private void actionPerformed(ActionEvent e) {
        WinCheck winCheck = new WinCheck();
        Object source = e.getSource();
        if (source == cleanFieldButton) {
            clearField();
        }
        if (source == newGameButton) {
            fieldButtons.removeAll();
            resetGame();
        }
        if (source == resetScoreButton){
            resetScore();
        }
        if (source != cleanFieldButton && source != newGameButton && source!= resetScoreButton) {
            if (!GameOver) {
                emptyCellCount--;
                if (player1Turn) {
                    player1Turn = false;
                    player2Turn = true;
                    setSymbol((JButton) e.getSource(), player1Symbol, turnP2String);
                    if (winCheck.checkWinner(size, winLine, buttonArray, (JButton) source)){
                        ScoreP1++;
                        scoreLabel.setText(ScoreP1 + " - " + ScoreP2);
                        playerLabel.setText(winP1);
                        GameOver = true;
                    }
                } else {
                    player2Turn = false;
                    player1Turn = true;
                    setSymbol((JButton) e.getSource(), player2Symbol, turnP1String);
                    if (winCheck.checkWinner(size, winLine, buttonArray, (JButton) source)) {
                        ScoreP2++;
                        scoreLabel.setText(ScoreP1 + " - " + ScoreP2);
                        playerLabel.setText(winP2);
                        GameOver = true;
                    }
                }
                if (emptyCellCount == 0) {
                    GameOver = true;
                    clearField();
                }
            }
        }
    }
    private void SizeComboBoxActionPerformed(ActionEvent ev) {
        if (ev.getSource() == sizeBox) {
            if (sizeBox.getSelectedItem() != null) {
                size = (int) sizeBox.getSelectedItem();
                resetGame();
            }
            final DefaultComboBoxModel<Integer> defComboBoxLine = new DefaultComboBoxModel<>();
            if (size == 3) {
                defComboBoxLine.addElement(3);
                winLineBox.setModel(defComboBoxLine);
            }
            if (size >= 4 && size <8) {
                defComboBoxLine.addElement(3);
                defComboBoxLine.addElement(4);
                winLineBox.setModel(defComboBoxLine);
            }
            if (size >= 8) {
                for (int i = 3; i < 6; i++) {
                    defComboBoxLine.addElement(i);
                    winLineBox.setModel(defComboBoxLine);
                }
            }
        }
        if (ev.getSource() == winLineBox) {
            if (winLineBox.getSelectedItem() != null) {
                winLine = (int) winLineBox.getSelectedItem();
            }
        }
    }
    private void setSymbol(JButton button, String symbol, String labelText) {
        button.setText(symbol);
        button.setEnabled(false);
        playerLabel.setText(labelText);
    }
    private void resetGame() {

        fieldButtons.removeAll();
        fieldButtons.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        fillField();
        fieldButtons.revalidate();
        clearField();
        totalCells = size * size; emptyCellCount = totalCells;
        GameOver = false;
    }
    private void resetScore(){
        ScoreP1 = 0; ScoreP2 = 0;
        scoreLabel.setText(ScoreP1 + " - " + ScoreP2);
    }
    private void clearField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttonArray[i][j].setText("");
                buttonArray[i][j].setEnabled(true);
            }
        }
        player1Turn = true;
        player2Turn = false;
        GameOver = false;

        playerLabel.setText(turnP1String);
        emptyCellCount = totalCells;
    }
}
