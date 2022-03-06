package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress saveGame1 = new GameProgress (9, 2, 2, 356.65);
        GameProgress saveGame2 = new GameProgress (5, 8, 5, 1203.00);
        GameProgress saveGame3 = new GameProgress (1, 10, 11, 2387.89);

        saveGame(saveGame1, "D://Games/savegames/save1.dat");
        saveGame(saveGame2, "D://Games/savegames/save2.dat");
        saveGame(saveGame3, "D://Games/savegames/save3.dat");

        List<String> fileNames = Arrays.asList("D://Games/savegames/save1.dat", "D://Games/savegames/save2.dat",
                "D://Games/savegames/save3.dat");
        zipFiles(fileNames, "D://Games/savegames/SaveGame.zip");

    }

    public static void saveGame(GameProgress saveGame, String way) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(saveGame);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(List<String> fileName, String way) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way))) {
            for (int i = 0; i < fileName.size(); i++) {
                try (FileInputStream fis = new FileInputStream(fileName.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + (i+1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
                File file = new File(fileName.get(i));
                file.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}





