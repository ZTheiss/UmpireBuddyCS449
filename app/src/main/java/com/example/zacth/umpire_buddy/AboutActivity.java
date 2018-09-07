package com.example.zacth.umpire_buddy;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends UmpireBuddy{


    public class ViewHolder extends AboutActivity.ViewHolder{
        TextView aboutInfo;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            aboutInfo = itemView
        }
    }
}
