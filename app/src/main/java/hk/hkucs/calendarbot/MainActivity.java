package hk.hkucs.calendarbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.bson.Document;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    EditText txtbox1;
    TextView tv;
    MongoDBAdaptor myAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtbox1= (EditText) findViewById(R.id.txtbox1);
        button1 = (Button) findViewById(R.id.button1);
        tv = (TextView) findViewById(R.id.tv);
        button2 = (Button) findViewById(R.id.button2);
        myAdaptor = new MongoDBAdaptor("MyDB");

        button1.setOnClickListener(new clicker1());
        button2.setOnClickListener(new clicker2());
    }

    class clicker1 implements Button.OnClickListener{
        public void onClick(View v){
            ArrayList<Document> docs = myAdaptor.selectAll("task_table");
            String text = "";
            for(Document d : docs){
                String time = d.get("TIME",String.class);
                String location = d.get("LOCATION",String.class);
                String content = d.get("CONTENT",String.class);
                text += time+" "+location+" "+content+"\n";
            }

            tv.setText(text);
        }
    }

    class clicker2 implements Button.OnClickListener{
        public void onClick(View v){
            String a = txtbox1.getText().toString();
            String[] stringList = a.split(" ");
            Task t = new Task(stringList[0], stringList[1], stringList[2], stringList[3]);
            myAdaptor.insertOneDocument("task_table", t.objectToDoc());
            txtbox1.setText("");
        }
    }


}
