package com.apps.partizanin.androidappzno;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.partizanin.androidappzno.utils.ClientData;
import com.apps.partizanin.androidappzno.utils.ContentData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
    private FloatingActionButton testButton;
    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;
    private ImageView icon4;
    private Handler handler;

    final String LOG_TAG = "myLogs";

    final String FILENAME = "clientData.json";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

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

        viewInitialization();

        icon1.setVisibility(View.INVISIBLE);
        icon2.setVisibility(View.INVISIBLE);
        icon3.setVisibility(View.INVISIBLE);
        icon4.setVisibility(View.INVISIBLE);

        setClickListener();

        fillViewsValues();
    }


    private void setClickListener() {

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
                        testTask();
                        break;
                }
            }
        };

        rightButton.setOnClickListener(clickListener);
        leftButton.setOnClickListener(clickListener);
        testButton.setOnClickListener(clickListener);


    }

    private void cleanTestValues() {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);

        icon1.setVisibility(View.INVISIBLE);
        icon2.setVisibility(View.INVISIBLE);
        icon3.setVisibility(View.INVISIBLE);
        icon4.setVisibility(View.INVISIBLE);
    }

    private void testTask() {

        testingCurrentTask();
    }

    private void testingCurrentTask() {
        ClientData clientData = getClientData();
        List<String> trueAnswers = getTrueAnswers(clientData.getParagraph(), clientData.getTask());
        int trueIcon = R.drawable.ic_check_black_36dp;
        int falseIcon = R.drawable.ic_close_black_36dp;

        Resources res = getResources();

        if (trueAnswers.contains(checkBox1.getText().toString())) {
            icon1.setVisibility(View.VISIBLE);
            icon1.setImageDrawable(res.getDrawable(trueIcon));

        } else {
            if (checkBox1.isChecked()) {
                icon1.setVisibility(View.VISIBLE);
                icon1.setImageDrawable(res.getDrawable(falseIcon));
            }
        }

        if (trueAnswers.contains(checkBox2.getText().toString())) {
            icon2.setVisibility(View.VISIBLE);
            icon2.setImageDrawable(res.getDrawable(trueIcon));

        } else {

            if (checkBox2.isChecked()) {
                icon2.setVisibility(View.VISIBLE);
                icon2.setImageDrawable(res.getDrawable(falseIcon));
            }

        }

        if (trueAnswers.contains(checkBox3.getText().toString())) {
            icon3.setVisibility(View.VISIBLE);
            icon3.setImageDrawable(res.getDrawable(trueIcon));

        } else {

            if (checkBox3.isChecked()) {
                icon3.setVisibility(View.VISIBLE);
                icon3.setImageDrawable(res.getDrawable(falseIcon));
            }

        }

        if (trueAnswers.contains(checkBox4.getText().toString())) {
            icon4.setVisibility(View.VISIBLE);
            icon4.setImageDrawable(res.getDrawable(trueIcon));

        }else{
            if (checkBox4.isChecked()) {
                icon4.setVisibility(View.VISIBLE);
                icon4.setImageDrawable(res.getDrawable(falseIcon));
            }

        }
    }

    private void loadNextTask() {
        //todo implement method
        //todo make new method to read and write clientData
        setClientData(1, 2);
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
        testButton = (FloatingActionButton) findViewById(R.id.testButton);
        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
        icon4 = (ImageView) findViewById(R.id.icon4);
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
        /*todo fix this trouble "I/Choreographer: Skipped 86 frames!  The application may be doing too much work on its main thread."*/
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
        return readClientDataFile();
    }

    private String trueAnswersResource() {
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.answrs)) {
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
        ClientData clientData = getClientData();
        String clientParagraph = clientData.getParagraph();
        String clientTask = clientData.getTask();

        ContentData contentData = getContentData(clientParagraph, clientTask);
        textViewQuestion.setText(contentData.getQuestion());
        checkBox1.setText(contentData.getAnswers().get(0));
        checkBox2.setText(contentData.getAnswers().get(1));
        checkBox3.setText(contentData.getAnswers().get(2));
        checkBox4.setText(contentData.getAnswers().get(3));

        textViewTaskValue.setText(clientTask);
        textViewParagraphValue.setText(clientParagraph);

    }

    private ClientData getClientData() {
        ClientData result = new ClientData();
        String clientJsonString = clientResource();
        String clientParagraph = "";
        String clientTask = "";


        try {

            if (!clientJsonString.isEmpty()) {
                JSONObject clientData = new JSONObject(clientJsonString).getJSONObject("lastLocation");
                clientParagraph = clientData.getString("paragraph");
                clientTask = clientData.getString("task");
                result = new ClientData(clientParagraph, clientTask);
            }else {
                JSONObject clientData = new JSONObject();
                JSONObject lastLocationData = new JSONObject();
                lastLocationData.put("paragraph", 1);
                lastLocationData.put("task", 1);
                clientData.put("lastLocation", lastLocationData);
                writeClientDataFile(clientData.toString());
                result = new ClientData("1", "1");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private ContentData getContentData(String paragraph, String task) {
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

    private ArrayList<String> getTrueAnswers(String paragraph,String task) {

        ArrayList<String> result = new ArrayList<>();

        String answersResources = trueAnswersResource();

        try {
            JSONObject paragraphs = new JSONObject(answersResources).getJSONObject("paragraphs");
            JSONObject paragraph1 = paragraphs.getJSONObject(paragraph);
            JSONObject tasks = paragraph1.getJSONObject("tasks");
            JSONObject tasks1 = tasks.getJSONObject(task);
            JSONArray answers = tasks1.getJSONArray("Answers");

            for (int i = 0; i < answers.length(); i++) {
                result.add(answers.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return result;

    }

    private void setClientData(int paragraph, int task) {

        try {
            JSONObject fullObj = new JSONObject(readClientDataFile());
            JSONObject clientData = fullObj.getJSONObject("lastLocation");

            clientData.put("paragraph", paragraph);
            clientData.put("task", task);
            fullObj.put("lastLocation", clientData);

            writeClientDataFile(fullObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void writeClientDataFile(String writeText) {
        // TODO: 10.12.2015 make writable and readable json client data
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(writeText);
            // закрываем поток
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   private String readClientDataFile() {
       String result = "";
       boolean fileNotFound = false;
       try {
           // открываем поток для чтения
           BufferedReader br = new BufferedReader(new InputStreamReader(
                   openFileInput(FILENAME)));
           String line = "";
           // читаем содержимое
           while ((line = br.readLine()) != null) {
               result += line;
               Log.d(LOG_TAG, result);
           }
       } catch (FileNotFoundException ignored) {
       } catch (IOException e) {
           e.printStackTrace();
       }
       return result;
   }

}


