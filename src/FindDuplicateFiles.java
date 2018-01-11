import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class FindDuplicateFiles
{
Vector<Vector<File> > duplicateFiles;
long minFileLength = 0;
String regexFileName = ".*";

public void find(Path dir) throws IOException
{
        HashMap<Long, Vector<FileComparator> > indexFiles = new HashMap<Long, Vector<FileComparator> >();
        HashMap<String, Vector<File> > duplicateFiles = new HashMap<String, Vector<File> >();

        IndexVisitor indexVisitor = new IndexVisitor();
        indexVisitor.indexFiles = indexFiles;
        indexVisitor.regexFileName = regexFileName;
        indexVisitor.minFileLength = minFileLength;
        Files.walkFileTree(dir, indexVisitor);

        for (Vector<FileComparator> fileComparators : indexFiles.values()) {
                for (int i = 0; i < fileComparators.size(); ++i) {
                        for (int j = i + 1; j < fileComparators.size(); ++j) {
                                File f1 = fileComparators.elementAt(i)._file;
                                File f2 = fileComparators.elementAt(j)._file;
                                if (!f1.toPath().equals(f2.toPath())) {
                                        if (fileComparators.elementAt(i).equals(fileComparators.elementAt(j))) {
                                                String key = fileComparators.elementAt(i).digest();
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
        this.duplicateFiles = new Vector<Vector<File> >(duplicateFiles.values());
}

public void find(Path dir1, Path dir2) throws IOException
{
        HashMap<Long, Vector<FileComparator> > indexFiles = new HashMap<Long, Vector<FileComparator> >();

        IndexVisitor indexVisitor = new IndexVisitor();
        indexVisitor.indexFiles = indexFiles;
        indexVisitor.minFileLength = minFileLength;
        indexVisitor.regexFileName = regexFileName;
        Files.walkFileTree(dir1, indexVisitor);

        CompareFileVisitor compareFileVisitor = new CompareFileVisitor();
        compareFileVisitor.duplicateFiles = new HashMap<String, Vector<File> >();;
        compareFileVisitor.indexFiles = indexFiles;
        compareFileVisitor.minFileLength = minFileLength;
        Files.walkFileTree(dir2, compareFileVisitor);
        this.duplicateFiles = new Vector<Vector<File> >(compareFileVisitor.duplicateFiles.values());
}
}
