
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.regex.*;

public class Main{

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("palabra clave);
            return;
        }

        String htmlFileName = args[0];
        String keyword = args[1];
        String logFileName = "file-" + keyword + ".log";

        try {
            String content = loadHTMLContent(htmlFileName);
            searchKeywordAndLog(content, keyword, htmlFileName, logFileName);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    private static String loadHTMLContent(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }
    private static void searchKeywordAndLog(String content, String keyword, String htmlFileName, String logFileName) throws IOException {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b");
        Matcher matcher = pattern.matcher(content);

        BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFileName));

        logWriter.write("Archivo: " + htmlFileName + "\n");
        System.out.println("Buscando la palabra: " + keyword + "\n");

        boolean found = false;
        while (matcher.find()) {
            found = true;
            int position = matcher.start();
            System.out.println("Palabra encontrada en el lugar: " + position);
            logWriter.write("Palabra encontrada en el lugar: " + position + "\n");
        }

        if (!found) {
            System.out.println("No se encontró la palabra en el archivo de texto.");
            logWriter.write("No se encontró la palabra en el archivo de texto.\n");
        }

        logWriter.close();
    }
}
