import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.io.FileReader;

public class RandomWord {

    public static void main(String[] args) {

        //FileReader input = new FileReader("text.txt");
        String str, champion = null, contender = null;
        int i = 1;

        if (!StdIn.isEmpty()) {
            champion = StdIn.readString();

            while (!StdIn.isEmpty()) {
                i++;
                contender = StdIn.readString();

                if (StdRandom.bernoulli(1.0/(double)i))
                    champion = contender;
            }
        }

        StdOut.println(champion);

    }
}
