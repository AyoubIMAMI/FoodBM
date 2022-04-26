package etu.polytech.foodbm;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import etu.polytech.foodbm.model.NotificationModel;
import etu.polytech.foodbm.model.Plan;


public class ListAdaptaterNotification extends ArrayAdapter<Notification> {
    public ListAdaptaterNotification(Context context, ArrayList<Notification> planArrayList) {
        super(context, R.layout.conso_layout, planArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notification notification = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_layout, parent, false);
        }

        TextView notifName = convertView.findViewById(R.id.notificationNameTextView);
        TextView notifDate= convertView.findViewById(R.id.notificationDateTextView);

        CharSequence title = notification.extras.getCharSequence(Notification.EXTRA_TITLE);
        CharSequence text = notification.extras.getCharSequence(Notification.EXTRA_TEXT);

        notifName.setText("title");
        notifDate.setText("text");

        return convertView;
    }


}
