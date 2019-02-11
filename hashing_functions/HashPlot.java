import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class HashPlot {

    //this method simply hashes the input based on m, a, and b
    private static int HashKey(int k, int m, int a, int b){
        int hash = (a*k+b)%m;
        return hash;
    }

    private static int[] readInputSeq(String filename){
        try{
            File file = new File(filename);
            FileReader fReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fReader);
            //assuming that the sequence will always be on one line
            String line = reader.readLine();
            //split the input into single entries
            String[] sequence = line.split(", ");
            int[] seq = new int[sequence.length];
            //convert string to integer and store in the seq array
            for (int i = 0; i<seq.length; i++){
                seq[i] = Integer.parseInt(sequence[i]);
            }
            return seq;
        } catch (IOException e ){
            System.out.println(e);
        }
        return null;
    }

    private static int[] generateOutput(int[] input, int m, int a, int b){
        int[] outputSeq = new int[input.length];
        //calculate the hashKey for each input in the input array and store it in the output array at
        // a corresponding index i
        for (int i = 0; i<input.length; i++){
            outputSeq[i] = HashKey(input[i], m, a, b);
        }

        //then write the output sequence to a text file
        try {
            FileWriter fw = new FileWriter("output_sequence");
            for (int i = 0; i<outputSeq.length; i++){
                fw.write(outputSeq[i]+", ");
            }
            fw.flush();
            fw.close();
        } catch (IOException e ){
            System.out.println(e);
        }
        return outputSeq;
    }


    private static class plotGraph extends JComponent{
        public void paintComponent(Graphics g){
            // First read the input sequence
            int[] inputSeq = readInputSeq("src/input_sequence");
            //calculate the hashKey for each input in the sequence, store it in the output seq array
            //set the parameters for hashing, modify a and b
            int[] outputSeq = generateOutput(inputSeq, 102, 5,7);

            /*
            comments on each of the parameters that we chose are in readme.txt
            bad distribution:
            case1:
            m = 100, a = 10, b = 5

            case2:
            m = 90, a = 15, b = 14

            case3:
            m = 100, a = 50, b = 1
             */

            /*
            Good distribution:
            case 1:
            m = 100, a = 7, b = 7

            case2:
            m = 95, a = 9, b = 9

            case3:
            m = 102, a = 5, b = 7

             */

            // then for every number in the input sequence, we calculate its hash and put it in the corresponding i position of the output array

            //get the height and width of the window
            int x = getWidth();
            int y = getHeight();

            //draw the marginal rectangles representing hte labels
            g.fillRect(0, y-13, x, 13);
            g.fillRect(0, 0, 25, y);

            //labeling x-axis
            g.setColor(Color.white);
            for (int i = 0; i<1050; i+=50){
                String toDraw = String.valueOf(i);
                g.drawString(toDraw, i+50, y-2);
            }

            //labeling y-axis
            for (int i = 0; i<=100; i+=10){
                String toDraw = String.valueOf(i);
                g.drawString(toDraw, 2, y-(i*5));
            }


            //plotting the data
            g.setColor(Color.red);
            for (int i = 0; i<outputSeq.length; i++){
                //please notice that we're shifting the input by 50 and scaling the output by 5 to better represent the data
                g.drawOval(inputSeq[i]+50, (y-(outputSeq[i]*5)), 3,3);
            }
        }
    }

    public static void main(String[] args){
        plotGraph graph = new plotGraph();
        ///java Graphics
        JFrame frame = new JFrame("Plot");
        frame.setSize(1100,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(graph);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
