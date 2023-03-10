import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    static StringBuilder logsBuilder = new StringBuilder();
    public static void main(String[] args) {
        writeLogs("Начинаем распаковывать игру");
        String[] array = {"C:/Apps/Games/", "/src/", "/res/", "/savegames/", "/temp/", "/src/main/", "/src/test/", "/res/drawables/", "/res/vectors/", "/res/icons/"};
        File installDir = new File(array[0]);
        createPath(installDir);
        for (int i = 1; i < array.length; i++) {
            installDir = new File(array[0] + array[i]);
            createPath(installDir);
        }
        File fileMain_java = new File("C:/Apps/Games/src/main/", "Main.java");
        File fileUtils_java = new File("C:/Apps/Games/src/main/", "Utils.java");
        createFile(fileMain_java);
        createFile(fileUtils_java);

        File fileTemp_txt = new File("C:/Apps/Games/temp/", "temp.txt");
        try {
            if (fileTemp_txt.createNewFile()) {
                writeLogs("Файл успешно создан " + fileTemp_txt.getAbsolutePath());
                FileWriter writer = new FileWriter(fileTemp_txt);
                writer.write(logsBuilder.toString());
                writer.flush();
                writer.close();
            } else writeLogs("Ошибка создания " + fileTemp_txt.getAbsolutePath());
        } catch (IOException e) {
            writeLogs(e.getMessage());
        }
        System.out.println(logsBuilder.toString());
    }
    public static void writeLogs(String args) {
        Date currDate = new Date();
        logsBuilder.append(currDate + ": " + args + "\n");
    }
    public static void createPath(File args) {
        File someDirs = new File(args.toURI());
        if (someDirs.mkdir())
            writeLogs("папка " + someDirs.toPath() + " создана");
        else {
            writeLogs("ошибка создания " + someDirs.toPath());
        }
    }
    public static void createFile(File args) {
        File someFile = new File(args.toURI());
        try {
            if (someFile.createNewFile()) {
                writeLogs("Файл успешно создан " + someFile.getAbsolutePath());
            } else writeLogs("Ошибка создания " + someFile.getAbsolutePath());
        } catch (IOException e) {
            writeLogs(e.getMessage());
        }
    }
}