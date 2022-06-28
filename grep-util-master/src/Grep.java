import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class Grep {
    private File file;
    private boolean regex;
    private boolean vFlag;
    private boolean iFlag;

    public Grep(File file, boolean regex, boolean vFlag, boolean iFlag) {
        this.file = file;
        this.regex = regex;
        this.vFlag = vFlag;
        this.iFlag = iFlag;
    }

    public void wordFind(String keyword) throws IOException {
        if (!regex) {
            keyword = Pattern.quote(keyword);
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        Pattern keywordPattern;

        if (iFlag) {
            keywordPattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        } else keywordPattern = Pattern.compile(keyword);


        while (line != null) {
            String[] tokens = line.split(" ");
            if (vFlag) {
                int i = 0;
                for (String word : tokens) {
                    if (keywordPattern.matcher(word).matches()){
                        i++;
                    }
                }
                if (i == 0){
                    System.out.println(line);
                }
            }
            else {
                for (String word : tokens) {
                    if (keywordPattern.matcher(word).matches()) {
                        System.out.println(line);
                        break;
                    }
                }

            }
            line = reader.readLine();
        }
    }
}