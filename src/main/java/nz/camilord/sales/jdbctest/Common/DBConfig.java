package nz.camilord.sales.jdbctest.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBConfig
{
    private static Properties properties;

    public DBConfig() {
        properties = new Properties();
        autoload();
    }

    private static void autoload() {

        File file = new File("db.config");
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isMatch(line, "(.*)localhost(.*)")) {
                    properties.setProperty("host", getConfigValue(line));
                } else if (isMatch(line, "(.*)dbname(.*)")) {
                    properties.setProperty("dbname", getConfigValue(line));
                } else if (isMatch(line, "(.*)username(.*)")) {
                    properties.setProperty("username", getConfigValue(line));
                } else if (isMatch(line, "(.*)password(.*)")) {
                    properties.setProperty("password", getConfigValue(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Properties
     */
    public Properties getConfig() {
        return properties;
    }

    private static boolean isMatch(String line, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.matches();
    }

    private static String getConfigValue(String line) {
        String[] tmp = line.split("=");
        return tmp[1].trim();
    }

}
