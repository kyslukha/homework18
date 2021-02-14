package ua.com.alevel;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;


public class Tree {
    public static void main(String[] args) {
        File project = new File("E://java");
        String prefix = "  ";

        LinkedList<File> files = createSortedFiles(project);

        explore(files, prefix);
    }

    private static LinkedList<File> createSortedFiles(File file) {
        LinkedList<File> files = new LinkedList<>(Arrays.asList(file.listFiles()));
        files.sort(new FileComparator());

        return files;
    }

    private static void explore(LinkedList<File> files, String prefix) {
        for (int index = 0; index < files.size(); index++) {
            if (files.get(index).isFile()) {
                System.out.println(prefix + files.get(index).getName());
            } else {
                System.out.println("*" + files.get(index).getName());
                files.addAll(createSortedFiles(files.get(index)));
            }
        }
    }
}