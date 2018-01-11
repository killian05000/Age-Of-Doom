import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class IndexVisitor extends SimpleFileVisitor<Path>
{
HashMap<Long, Vector<FileComparator> > indexFiles;
long minFileLength = 0;

public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
throws IOException
{
        File f = file.toFile();
        if (f.canRead() && f.length() >= minFileLength) {
                Vector<FileComparator> v;
                if(indexFiles.containsKey(f.length())) {
                        v = indexFiles.get(f.length());
                } else {
                        v = new Vector<FileComparator>();
                        indexFiles.put(f.length(), v);
                }
                v.addElement(new FileComparator(f));
        }
        return FileVisitResult.CONTINUE;
}
}
