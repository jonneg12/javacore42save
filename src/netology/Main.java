package netology;

import java.io.*;
import java.util.zip.*;

public class Main {

    public static void main(String[] args) {

        String pathToSave = "D:\\Games\\Games\\savegames";

        // Создать три экземпляра класса GameProgress.
        GameProgress save1 = new GameProgress(10, 5, 2, 2.2);
        GameProgress save2 = new GameProgress(20, 7, 5, 12.2);
        GameProgress save3 = new GameProgress(30, 10, 10, 22.2);

        // Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        serialisation(pathToSave, save1, "\\save1.dat");
        serialisation(pathToSave, save2, "\\save2.dat");
        serialisation(pathToSave, save3, "\\save3.dat");

//        Созданные файлы сохранений из папки savegames запаковать в архив zip.
       try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToSave + "\\saves.zip"))){

            ZipEntry entry1 = new ZipEntry(pathToSave + "\\save1.dat");
            zout.putNextEntry(entry1);

            ZipEntry entry2 = new ZipEntry(pathToSave + "\\save2.dat");
            zout.putNextEntry(entry2);

            ZipEntry entry3 = new ZipEntry(pathToSave + "\\save3.dat");
            zout.putNextEntry(entry3);

            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//        Удалить файлы сохранений, лежащие вне архива.
        File[] files = new File(pathToSave).listFiles();
        for (File file : files) {
            if (!file.getName().endsWith(".zip")){
                file.delete();
            }
        }
    }

    private static void serialisation(String pathToSave, GameProgress save, String s) {
        try (FileOutputStream fos = new FileOutputStream(pathToSave + s);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

