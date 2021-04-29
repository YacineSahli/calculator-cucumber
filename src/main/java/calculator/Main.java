package calculator;

import cli.CommandLineInterface;
import cli.Mode;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main {

    public static void main(String[] args) {
        CommandLineInterface cal_interface = new CommandLineInterface(Mode.CALCULATOR);
        cal_interface.run();
    }

}
