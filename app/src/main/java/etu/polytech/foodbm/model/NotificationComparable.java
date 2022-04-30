package etu.polytech.foodbm.model;

import android.app.Notification;

import java.util.Date;

public class NotificationComparable  implements Comparable, Cloneable{
    public Notification notification;
    public String title;
    public String description;
    public Date notifDate;
    //this.title = notification.extras.getCharSequence(Notification.EXTRA_TITLE).toString();
    // this.description = notification.extras.getCharSequence(Notification.EXTRA_TEXT).toString();

    public NotificationComparable(Notification notification, Date notifDate, String title, String description){
        this.notification = notification;
        this.notifDate = notifDate;
        this.title = title;
        this.description = description;
    }

    public Notification getNotification() {
        return notification;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getNotifDate() {
        return notifDate;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof NotificationComparable){
            NotificationComparable other = (NotificationComparable) o;
            if (this.notifDate.after(other.getNotifDate())) {
                return 1;
            } else if (this.notifDate.before(other.getNotifDate())) {
                return -1;
            } else{
                if (this.title.compareTo(other.getTitle()) > 0) {
                    return 1;
                } else if (this.title.compareTo(other.getTitle()) < 0) {
                    return -1;
                } else {
                    if (this.description.compareTo(other.getDescription()) > 0) {
                        return 1;
                    } else if (this.description.compareTo(other.getDescription()) < 0) {
                        return -1;
                    }
                }
            }
        }
        return 0;
    }
}
