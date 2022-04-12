package sample;

import org.apache.commons.io.FileUtils;
import sample.model.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;

public class HtmlHandler {
    public static String x;

    public static void save(Note note) {
        try {
            File dir = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + note.getUser());
            File htmlTemplateFile = new File("template.html");
            String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
            htmlString = htmlString.replace("$title", note.getTitle());
            htmlString = htmlString.replace("$body", note.getUser() + "<br>" + note.getNoteDate() + "<br><br>" + note.getContent());
            note.setId(UUID.randomUUID().toString());
            File newHtmlFile = new File(dir + "\\" + note.getId() + ".html");
            FileUtils.writeStringToFile(newHtmlFile, htmlString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void edit(String user_, String id_, Note note) {
        try {
            File dir = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user_);
            File htmlTemplateFile = new File("template.html");
            String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
            htmlString = htmlString.replace("$title", note.getTitle());
            htmlString = htmlString.replace("$body", user_ + "<br>" + note.getNoteDate() + "<br><br>" + note.getContent());
            File newHtmlFile = new File(dir + "\\" + id_ + ".html");
            FileUtils.writeStringToFile(newHtmlFile, htmlString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String name(String user, String date) {
        x = date;
        int k = 0;
        File f = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user + "\\" + x + ".html");
        while (f.isFile()) {
            k++;
            f = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user + "\\" + x + "(" + k + ")" + ".html");
        }
        if (k != 0) {
            return (x + "(" + k + ")");
        } else {
            return x;
        }

    }

    public static void deleteNote(String user_, String id_){
        File file = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user_ + "\\" + id_ + ".html");
        file.delete();
    }

    public static Note getData(String id_, String user_) {
        File file = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user_ + "\\" + id_ + ".html");
        StringBuilder fileContent = new StringBuilder();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                fileContent.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] content = new String[4];
        content[0] = fileContent.substring(fileContent.indexOf("<body>") + 6, fileContent.indexOf("<br>"));
        content[1] = fileContent.substring(fileContent.indexOf("<title>") + 7, fileContent.indexOf("</title>"));
        content[3] = fileContent.substring(fileContent.indexOf("<br>") + 4, fileContent.indexOf("<br><br>"));
        content[2] = fileContent.substring(fileContent.indexOf("<br><br>") + 8, fileContent.indexOf("</body>"));
        return new Note(content[0], content[1], content[2], content[3], file.getName().substring(0, file.getName().length() - 5));
    }

    public static Note[] getList(String user) {

        File folder = new File(System.getProperty("user.dir") + "\\" + "users" + "\\" + user);
        if ((folder.list()) != null) {
            Note[] n = new Note[folder.list().length];
            int i = 0;
            for (final File fileEntry : folder.listFiles()) {
                n[i] = getData(fileEntry.getName().substring(0, fileEntry.getName().length() - 5), user);
                i++;
            }
            return n;
        } else {
            return null;
        }

    }
}