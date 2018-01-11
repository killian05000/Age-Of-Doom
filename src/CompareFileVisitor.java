import java.nio.file.SimpleFileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class CompareFileVisitor extends SimpleFileVisitor<Path>
{
HashMap<Long, Vector<FileComparator> > indexFiles;
HashMap<String, Vector<File> > duplicateFiles;
long minFileLength = 0;

public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
throws IOException
{
        File f = file.toFile();
        if (f.canRead() && f.length() >= minFileLength) {
                Vector<FileComparator> fcs = indexFiles.get(f.length());
                if (fcs != null) {
                        for (FileComparator fc : fcs) {
                                if (fc.equals(new FileComparator(f))) {
                                        File f1 = fc._file;
                                        File f2 = f;
                                        if (!f1.toPath().equals(f2.toPath())) {
                                                String key = fc.digest();
                                                Vector<File> v;
                                                if(duplicateFiles.containsKey(key)) {
                                                        v = duplicateFiles.get(key);
                                                } else {
                                                        v = new Vector<File>();
                                                        duplicateFiles.put(key, v);
                                                }
                                                if (!v.contains(f1)) {
                                                        v.addElement(f1);
                                                }
                                                if (!v.contains(f2)) {
                                                        v.addElement(f2);
                                                }
                                        }
                                }
                        }
                }
        }
        return FileVisitResult.CONTINUE;
}
}
