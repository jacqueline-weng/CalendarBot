package hk.hkucs.calendarbot;

import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    public Date DATE;
    public Date TIME;
    public String LOCATION;
    public String CONTENT;

    public Task(String date, String time ,String location, String content){

        this.LOCATION = location;
        this.CONTENT = content;

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("M.dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm a");
        try{
            this.DATE = dateFormat1.parse(date);
        }catch(ParseException e){

        }

        try{
            this.TIME = dateFormat2.parse(time);
        }catch(ParseException e){

        }



    }

    public Document objectToDoc(){
        Document doc = new Document();
        doc.put("DATE", this.DATE);
        doc.put("TIME", this.TIME);
        doc.put("LOCATION", this.LOCATION);
        doc.put("CONTENT", this.CONTENT);

        return doc;
    }
}
