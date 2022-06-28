import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;

public class Main {
    @Option(name = "-r")
    private boolean regex;

    @Option(name = "-v")
    private boolean vFlag;

    @Option(name = "-i")
    private boolean iFlag;

    @Argument(required = true, index = 1)
    private String inputFile;

    @Argument(required = true, index = 0)
    private String word;

    public static void main(String[] args) {
        new Main().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }
        Grep core = new Grep(new File(inputFile), regex, vFlag, iFlag);
        try {
            core.wordFind(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
