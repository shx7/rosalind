import util.AbstractCmdProgram;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AbstractCmdProgram cmdProgram = new gc.Main();
        try {
            cmdProgram.run(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
