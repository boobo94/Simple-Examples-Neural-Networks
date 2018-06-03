import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Form {
    private JPanel panel1;
    private JButton saveButton;
    private JButton clearButton;
    private JPanel controlButtonPanel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JTextField textFieldO;
    private JTextField textFieldU;
    private JTextField textFieldA;
    private JTextField textFieldE;
    private JTextField textFieldI;
    private JTextField textField00;
    private JTextField textField01;
    private JTextField textField02;
    private JTextField textField03;
    private JTextField textField04;
    private JPanel inputPanel;
    private JTextField textField10;
    private JTextField textField20;
    private JTextField textField30;
    private JTextField textField40;
    private JTextField textField41;
    private JTextField textField43;
    private JTextField textField44;
    private JTextField textField34;
    private JTextField textField24;
    private JTextField textField14;
    private JTextField textField13;
    private JTextField textField12;
    private JTextField textField11;
    private JTextField textField21;
    private JTextField textField31;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField32;
    private JTextField textField33;
    private JTextField textField42;
    private JButton checkButton;

    private int[] dataIn;
    private int[] dataOut;

    private String filePath = "/Users/boobo94/Google Drive/University/IESI/Semestru 2/TSIA/RecogniseVowels/src/";

    public Form() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField00.setText("0");
                textField01.setText("0");
                textField02.setText("0");
                textField03.setText("0");
                textField04.setText("0");
                textField10.setText("0");
                textField11.setText("0");
                textField12.setText("0");
                textField13.setText("0");
                textField14.setText("0");
                textField20.setText("0");
                textField21.setText("0");
                textField22.setText("0");
                textField23.setText("0");
                textField24.setText("0");
                textField30.setText("0");
                textField31.setText("0");
                textField32.setText("0");
                textField33.setText("0");
                textField34.setText("0");
                textField40.setText("0");
                textField41.setText("0");
                textField42.setText("0");
                textField43.setText("0");
                textField44.setText("0");
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dimension = 5;
                int hidden_nodes = 20;
                int output_nodes = 5;
                int templates_number;


                int[][] sabloaneIn = readTemplatesFromFile(filePath + "in.txt");
                int[][] sabloaneOut = readTemplatesFromFile(filePath + "out.txt");

                templates_number = sabloaneIn.length;

                FeedForward nn = new FeedForward(hidden_nodes, output_nodes, templates_number, dimension);
                nn.Train(sabloaneIn, sabloaneOut);


                dataIn = new int[dimension * dimension];
                dataIn[0] = Integer.parseInt(textField00.getText());
                dataIn[1] = Integer.parseInt(textField01.getText());
                dataIn[2] = Integer.parseInt(textField02.getText());
                dataIn[3] = Integer.parseInt(textField03.getText());
                dataIn[4] = Integer.parseInt(textField04.getText());
                dataIn[5] = Integer.parseInt(textField10.getText());
                dataIn[6] = Integer.parseInt(textField11.getText());
                dataIn[7] = Integer.parseInt(textField12.getText());
                dataIn[8] = Integer.parseInt(textField13.getText());
                dataIn[9] = Integer.parseInt(textField14.getText());
                dataIn[10] = Integer.parseInt(textField20.getText());
                dataIn[11] = Integer.parseInt(textField21.getText());
                dataIn[12] = Integer.parseInt(textField22.getText());
                dataIn[13] = Integer.parseInt(textField23.getText());
                dataIn[14] = Integer.parseInt(textField24.getText());
                dataIn[15] = Integer.parseInt(textField30.getText());
                dataIn[16] = Integer.parseInt(textField31.getText());
                dataIn[17] = Integer.parseInt(textField32.getText());
                dataIn[18] = Integer.parseInt(textField33.getText());
                dataIn[19] = Integer.parseInt(textField34.getText());
                dataIn[20] = Integer.parseInt(textField40.getText());
                dataIn[21] = Integer.parseInt(textField41.getText());
                dataIn[22] = Integer.parseInt(textField42.getText());
                dataIn[23] = Integer.parseInt(textField43.getText());
                dataIn[24] = Integer.parseInt(textField44.getText());

                double result[] = nn.Check(dataIn);

                WhatVowel(result);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTemplatesInFile(dataIn, filePath + "in.txt");
                addTemplatesInFile(dataOut, filePath + "out.txt");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Form");
        frame.setContentPane(new Form().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private static int[][] readTemplatesFromFile(String fileName) {
        ArrayList<ArrayList<Integer>> templates = new ArrayList<>();

        FileReader fr;
        BufferedReader bfr;

        try {
            fr = new FileReader(fileName);
            bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                ArrayList<Integer> temp = new ArrayList<>();

                System.out.println(line);

                StringTokenizer tk = new StringTokenizer(line);
                while (tk.hasMoreElements()) {
                    String tokenCrt = tk.nextToken();
                    temp.add(Integer.parseInt(tokenCrt));
                }
                templates.add(temp);

            }


        } catch (IOException e) {
            System.out.println("Error accessing file.");
            System.exit(1);
        }

        // prepare to in[][]
        int[][] temp = new int[templates.size()][templates.get(0).size()];

        for (int i = 0; i < templates.size(); i++)
            for (int j = 0; j < templates.get(0).size(); j++) {
                temp[i][j] = templates.get(i).get(j);
            }

        return temp;
    }

    private void addTemplatesInFile(int[] data, String fileName) {
        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println();
            for (int i = 0; i < data.length; i++) {
                out.print(data[i] + " ");
            }
        } catch (IOException e) {
            System.out.println("Error accessing file.");
            System.exit(1);
        }
    }


    private void WhatVowel(double[] arr) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        double max = arr[0];
        int index = 0;
        for (int i = 0; i < arr.length; i++)
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }


        System.out.println("\n\n This vowel must be " + vowels[index]);

        textFieldA.setText("0");
        textFieldE.setText("0");
        textFieldI.setText("0");
        textFieldO.setText("0");
        textFieldU.setText("0");

        switch (index) {
            case 0:
                textFieldA.setText("1");
                dataOut = new int[]{1, 0, 0, 0, 0};
                break;
            case 1:
                textFieldE.setText("1");
                dataOut = new int[]{0, 1, 0, 0, 0};
                break;
            case 2:
                textFieldI.setText("1");
                dataOut = new int[]{0, 0, 1, 0, 0};
                break;
            case 3:
                textFieldO.setText("1");
                dataOut = new int[]{0, 0, 0, 1, 0};
                break;
            default:
                dataOut = new int[]{0, 0, 0, 0, 1};
                textFieldU.setText("1");

        }

    }

}
