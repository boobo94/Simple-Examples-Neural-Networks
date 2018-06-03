import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        int dimension = 5;
        int hidden_nodes = 20;
        int output_nodes = 5;
        int templates_number = output_nodes * 4;

//        int sabloaneIn[][] = {
//
//                // A
//                {
//                        0, 1, 1, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 1, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0
//                },
//                {
//                        1, 1, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 1, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0
//                },
//                {
//                        0, 0, 1, 1, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 1, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1
//                },
//                {
//                        1, 1, 1, 1, 1,
//                        1, 0, 0, 0, 1,
//                        1, 1, 1, 1, 1,
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1
//                },
//
//
//                // E
//                {
//                        0, 1, 1, 1, 0,
//                        0, 1, 0, 0, 0,
//                        0, 1, 1, 1, 0,
//                        0, 1, 0, 0, 0,
//                        0, 1, 1, 1, 0
//                },
//                {
//                        1, 1, 1, 0, 0,
//                        1, 0, 0, 0, 0,
//                        1, 1, 1, 0, 0,
//                        1, 0, 0, 0, 0,
//                        1, 1, 1, 0, 0
//                },
//                {
//                        0, 0, 1, 1, 1,
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 1, 1,
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 1, 1
//                },
//                {
//                        1, 1, 1, 1, 1,
//                        1, 0, 0, 0, 0,
//                        1, 1, 1, 1, 1,
//                        1, 0, 0, 0, 0,
//                        1, 1, 1, 1, 1
//                },
//
//
//                // I
//                {
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 0, 0,
//                        0, 0, 1, 0, 0
//                },
//                {
//                        0, 0, 0, 0, 1,
//                        0, 0, 0, 0, 1,
//                        0, 0, 0, 0, 1,
//                        0, 0, 0, 0, 1,
//                        0, 0, 0, 0, 1
//                },
//                {
//                        1, 0, 0, 0, 0,
//                        1, 0, 0, 0, 0,
//                        1, 0, 0, 0, 0,
//                        1, 0, 0, 0, 0,
//                        1, 0, 0, 0, 0
//                },
//                {
//                        0, 1, 0, 0, 0,
//                        0, 1, 0, 0, 0,
//                        0, 1, 0, 0, 0,
//                        0, 1, 0, 0, 0,
//                        0, 1, 0, 0, 0
//                },
//
//
//                // O
//                {
//                        0, 1, 1, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 1, 1, 0
//                },
//                {
//                        1, 1, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 1, 1, 0, 0
//                },
//                {
//                        0, 0, 1, 1, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 1, 1
//                },
//                {
//                        1, 1, 1, 1, 1,
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1,
//                        1, 1, 1, 1, 1
//                },
//
//
//                // U
//                {
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 0, 1, 0,
//                        0, 1, 1, 1, 0
//                },
//                {
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 0, 1, 0, 0,
//                        1, 1, 1, 0, 0
//                },
//                {
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 0, 1,
//                        0, 0, 1, 1, 1
//                },
//                {
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1,
//                        1, 0, 0, 0, 1,
//                        1, 1, 1, 1, 1
//                },
//        };
//
//        int[][] sabloaneOut = new int[][]{
//                // A
//                {1, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0},
//
//                // E
//                {0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 0},
//
//                // I
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//
//                // O
//                {0, 0, 0, 1, 0},
//                {0, 0, 0, 1, 0},
//                {0, 0, 0, 1, 0},
//                {0, 0, 0, 1, 0},
//
//                // U
//                {0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 1}
//
//        };

        int[][] sabloaneIn = readTemplatesFromFile("/Users/boobo94/Google Drive/University/IESI/Semestru 2/TSIA/RecogniseVowels/src/in.txt", dimension);
        int[][] sabloaneOut = readTemplatesFromFile("/Users/boobo94/Google Drive/University/IESI/Semestru 2/TSIA/RecogniseVowels/src/out.txt", dimension);

        templates_number = sabloaneIn.length;

        FeedForward nn = new FeedForward(hidden_nodes, output_nodes, templates_number, dimension);
        nn.Train(sabloaneIn, sabloaneOut);


        int[] testIn = {
                1, 1, 1, 0, 0,
                1, 0, 0, 0, 0,
                1, 1, 1, 0, 0,
                1, 0, 0, 0, 0,
                1, 1, 1, 0, 0
        };

        double result[] = nn.Check(testIn);

        WhatVowel(result);

    }

    private static int[][] readTemplatesFromFile(String fileName, int dim) {
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


    private static void WhatVowel(double[] arr) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};
        double max = arr[0];
        int index = 0;
        for (int i = 0; i < arr.length; i++)
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }

        System.out.println("\n\n This vowel must be " + vowels[index]);
    }
}
