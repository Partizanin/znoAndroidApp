package com.apps.partizanin.androidappzno;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apps.partizanin.androidappzno.utils.ContentData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewQuestion;
    private TextView textViewParagraphValue;
    private TextView textViewTaskValue;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private FloatingActionButton rightButton;
    private FloatingActionButton leftButton;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setClickListener();

        viewInitialization();

        fillViewsValues();
    }


    private void setClickListener(){

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.leftButton:
                        loadPreviousTask();
                        break;
                    case R.id.rightButton:
                        loadNextTask();
                        break;
                    case R.id.testButton:
                        break;
                }
            }
        };

        rightButton.setOnClickListener(clickListener);
        leftButton.setOnClickListener(clickListener);
        testButton.setOnClickListener(clickListener);


    }

    private void loadNextTask() {
        //todo implement method
        //todo make new method to read and write clientData
    }

    private void loadPreviousTask() {
        //todo implement method
    }

    private void viewInitialization() {
        textViewQuestion = (TextView) findViewById(R.id.questionText);
        textViewParagraphValue = (TextView) findViewById(R.id.paragraphValue);
        textViewTaskValue = (TextView) findViewById(R.id.taskValue);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        rightButton = (FloatingActionButton) findViewById(R.id.rightButton);
        leftButton = (FloatingActionButton) findViewById(R.id.leftButton);
        testButton = (Button) findViewById(R.id.testButton);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String contentResource() {
        Log.d("MyFilter", "contentResource method");
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.data)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private String clientResource() {
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.clientdata)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private void fillViewsValues() {

        String clientJsonString = clientResource();
        String clientParagraph = "";
        String clientTask= "";

        try {
            JSONObject clientData = new JSONObject(clientJsonString).getJSONObject("lastLocation");
            clientParagraph = clientData.getString("paragraph");
            clientTask = clientData.getString("task");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ContentData contentData = getContentData(clientParagraph, clientTask);
            textViewQuestion.setText(contentData.getQuestion());
            checkBox1.setText(contentData.getAnswers().get(0));
            checkBox2.setText(contentData.getAnswers().get(1));
            checkBox3.setText(contentData.getAnswers().get(2));
            checkBox4.setText(contentData.getAnswers().get(3));

            textViewTaskValue.setText(clientTask);
            textViewParagraphValue.setText(clientParagraph);

    }

    private ContentData getContentData(String paragraph,String task) {
        ContentData result = new ContentData();

        String jsonString = contentResource();
        try {
            JSONObject paragraphs = new JSONObject(jsonString).getJSONObject("paragraphs");
            JSONObject paragraph1 = paragraphs.getJSONObject(paragraph);
            JSONObject tasks = paragraph1.getJSONObject("tasks");
            JSONObject tasks1 = tasks.getJSONObject(task);
            String question = String.valueOf(tasks1.get("Question"));
            JSONArray answers = tasks1.getJSONArray("Answers");

            result.setQuestion(question);
            for (int i = 0; i < answers.length(); i++) {
                result.getAnswers().add(answers.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return result;
    }

}


