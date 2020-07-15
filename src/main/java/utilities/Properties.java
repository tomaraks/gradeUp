package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    public static final String BASE_URI = getBaseUri();

    private static String getBaseUri() {
        if (System.getProperty("baseUri") != null) {
            return System.getProperty("baseUri");
        } else {
            try {
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "application.properties");
                java.util.Properties prop = new java.util.Properties();

                prop.load(fis);
                return prop.get("baseUri").toString();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
