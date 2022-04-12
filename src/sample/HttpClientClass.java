package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sample.model.Note;
import sample.model.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HttpClientClass {

    public static List<Note> httpClientGetAll(String user_) {

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpGet request = new HttpGet("http://localhost:8080/api/get-note/" + user_ + "/get-all");
        final Gson gson = new Gson();

        try {

            final HttpResponse response = client.execute(request);
            final HttpEntity entity = response.getEntity();
            final String json = EntityUtils.toString(entity);
            final Type type = new TypeToken<ArrayList<Note>>(){}.getType();
            return gson.fromJson(json, type);

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    public static void httpClientPostNote(Note note) {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/post-note/create");
        Gson gson = new Gson();
        final String json = gson.toJson(note);

        try {
            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");
            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int httpClientPostAuth(User user) {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/login");
        Gson gson = new Gson();
        final String json = gson.toJson(user);

        try {
            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");
            client.execute(httpPost);
            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 400;
        }
    }

    public static int httpClientRegister(User user_) {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://localhost:8080/api/register");
        Gson gson = new Gson();
        final String json = gson.toJson(user_);

        try {
            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            client.close();
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            return 400;
        }
    }

    public static void httpClientPutNote(Note note) {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPut httpPut = new HttpPut("http://localhost:8080/api/edit-note/" + note.getUser() + "/" + note.getId());
        Gson gson = new Gson();
        final String json = gson.toJson(note);

        try {
            final StringEntity entity = new StringEntity(json);
            httpPut.setEntity(entity);
            httpPut.setHeader("Content-type", "application/json");
            client.execute(httpPut);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void httpClientDeleteNote(String user_, String id_) {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api/delete-note/" + user_ + "/" + id_);

        try {
            client.execute(httpDelete);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
