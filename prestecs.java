import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class prestecs {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        try {
            String filePath = "./prestecs.json";
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}