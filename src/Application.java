import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Application
{
public static void main (String [] args) throws IOException
{
        if (args.length != 0) {
                FindDuplicateFiles fdf = new FindDuplicateFiles();
                fdf.minFileLength = 1000; // 1ko
                fdf.regexFileName = ".*svg"; // only svg files

                Path d1 = Paths.get(args[0]);

                if (args.length == 2) {
                        Path d2 = Paths.get(args[1]);
                        fdf.find(d1, d2);
                } else {
                        fdf.find(d1);
                }

                for (Vector<File> files : fdf.duplicateFiles) {
                        for (File f : files) {
                                System.out.print(f.toString() + " ; ");
                        }
                        System.out.println();
                }
        } else {
                MainWindow window = new MainWindow("Search duplicates");
                window.setVisible(true);
        }
}
}
