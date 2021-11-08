package com.example.getpostdelupdatefx1;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import okhttp3.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;


//import com.squareup.okhttp.MultipartBuilder;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
import javax.swing.*;
import java.io.IOException;

public class HelloController {

    @FXML
    private TableColumn tablecolumn_id, tablecolumn_name;

    @FXML
    private TableView tableview_json;

    @FXML
    private TextField textfield_id, textfield_name;

    OkHttpClient client = new OkHttpClient();

    public void GetRestAPI(ActionEvent e) throws IOException {
        Request r = new Request.Builder()
                .url("http://localhost:8080/elcam/all").build();
        try {
            Response resp = client.newCall(r).execute();
            //listview_Json.getItems().add(resp.body().string());

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(resp.body().string());
            JSONArray jsonArray = (JSONArray)obj;

            String id = "", name = "";

            ObservableList<jsonObject> data = FXCollections.observableArrayList(
            );

            for (var i = 0; i < jsonArray.toArray().length; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                id = jsonObject.get("id").toString();
                name = jsonObject.get("name").toString();
                data.add(new jsonObject(id, name));

                //listview_json.getItems().add(jsonObject.get("name"));
            }

            tablecolumn_id.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("id"));
            tablecolumn_name.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("name"));

            tableview_json.setItems(data);
        }
        catch (IOException exception) {

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void PostRestAPI(ActionEvent e) throws IOException {

        String json = "{\r\n" +
                " \"name\" : \"" + textfield_name.getText() + "\" \r\n" +
                "}";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("http://localhost:8080/elcam/add").post(body).build();

        System.out.println(json);

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }


    public void UpdateRestAPI(ActionEvent e) throws IOException {

        String json = "{\r\n" +
                " \"name\" : \"" + textfield_name.getText() + "\" \r\n" +
                "}";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("http://localhost:8080/elcam/update/" + textfield_id.getText()).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }


    public void DeleteRestAPI(ActionEvent e) throws IOException {

        Request request = new Request.Builder().url("http://localhost:8080/elcam/delete/" + textfield_id.getText()).delete().build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

    public void LoginRestAPI(ActionEvent e) throws IOException {

        String json = "{\r\n" +
                " \"name\" : \"" + textfield_name.getText() + "\" \r\n" +
                "}";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("http://localhost:8080/elcam/add").post(body).build();

        System.out.println(json);

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }




    }



    public void Exit(ActionEvent e) throws IOException {
        MessageBox("Goodbye", "Exit");
        System.exit(0);
    }

    public static void MessageBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws IOException {
        //gittest
        //brand3
    }
}