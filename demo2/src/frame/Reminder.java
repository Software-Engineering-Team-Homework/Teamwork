package frame;
import reminders.*;

import java.util.Date;

public class Reminder {
    private int  reminder_id;
    private int user_id;
    private String title;
    private String deadline;
    private String detail;
    private int complete;
    private String createdTime;
    private String modifiedTime;

    public Reminder(int reminder_id,int user_id,String title, String deadline,String detail,String createdTime,String modifiedTime) {
        this.reminder_id = reminder_id;
        this.user_id = user_id;
        this.title = title;
        this.deadline = deadline;
        this.detail = detail;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.complete = 0;
    }

    public Reminder(int reminderId, int userId, String title, String deadline, String content) {
        this.reminder_id = reminderId;
        this.user_id = userId;
        this.title = title;
        this.deadline = deadline;
        this.detail = content;
        try {
            this.createdTime=get_created_at.getinf(this.user_id,this.reminder_id);
            this.modifiedTime=get_update_at.getinf(this.user_id,this.reminder_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.complete = 0;
    }


    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(int reminder_id) {
        this.reminder_id = reminder_id;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getreminder_id(){
        return reminder_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDetail() {
        return detail;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

}
