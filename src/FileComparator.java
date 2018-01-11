import java.io.File;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

public class FileComparator
{
File _file;
private String _digest;

FileComparator(File file)
{
        _file = file;
        _digest = null;
}

String digest() throws IOException
{
        if (_digest == null) {
                _digest = new DigestUtils(MD5).digestAsHex(_file);
        }
        return _digest;
}

boolean equals(FileComparator fc) throws IOException
{
        return digest().equals(fc.digest());
}
}
