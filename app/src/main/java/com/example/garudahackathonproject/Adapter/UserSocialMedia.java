package com.example.garudahackathonproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.garudahackathonproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserSocialMedia extends RecyclerView.Adapter<UserSocialMedia.ViewHolder> {

    private Context mContext;
    private List<String> mUsers;

    private FirebaseUser firebaseUser;

    String url;

    public UserSocialMedia(Context mContext, List<String> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_social, viewGroup, false);
        return new UserSocialMedia.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        url = mUsers.get(i);
        int beginning, end;
        String elements = "", tempElements = "";

        if(url.indexOf("instagram")!=-1) {
            viewHolder.socialMediaName.setText("Instagram");

            Downloader downloader = new Downloader();
            downloader.execute(url);
            try {
                elements = downloader.get();
            } catch (Exception e) {
                Log.e("ERROR IN THREAD",e.toString());
            }

            beginning = elements.indexOf("(@")+2;
            end = elements.indexOf(") • ");
            String usernameText = elements.substring(beginning,end);
            viewHolder.username.setText(usernameText);

            beginning = elements.indexOf("<title>")+7;
            end = elements.indexOf(" (@");
            String fullnameText = elements.substring(beginning,end);
            viewHolder.fullname.setText(fullnameText);

            beginning = elements.indexOf("<meta content")+15;
            end = elements.indexOf(" Followers");
            String followersText = elements.substring(beginning,end);
            viewHolder.followers.setText(followersText);

            beginning = elements.indexOf("og:image")+19;
            tempElements = elements.substring(beginning);
            end = tempElements.indexOf("\" />");
            String imageUrl = tempElements.substring(0,end);

            viewHolder.webView.loadData(getPicture(viewHolder,imageUrl) , "text/html", null);

            goToSocial(viewHolder, url);

        } else if (url.indexOf("tiktok")!=-1) {
            viewHolder.socialMediaName.setText("TikTok");

            Downloader downloader = new Downloader();
            downloader.execute(url);
            try {
                elements = downloader.get();
            } catch (Exception e) {
                Log.e("ERROR IN THREAD",e.toString());
            }

            beginning = elements.indexOf("(@")+2;
            end = elements.indexOf(") Official TikTok");
            String usernameText = elements.substring(beginning,end);
            viewHolder.username.setText(usernameText);

            beginning = elements.indexOf("<title>")+7;
            end = elements.indexOf(" (@");
            String fullnameText = elements.substring(beginning,end);
            viewHolder.fullname.setText(fullnameText);

            tempElements = elements.substring(elements.indexOf(") on TikTok |"));
            beginning = tempElements.indexOf("Likes.")+7;
            end = tempElements.indexOf(" Fans.");
            String followersText = tempElements.substring(beginning,end);
            viewHolder.followers.setText(followersText);

            beginning = elements.indexOf("og:image")+19;
            tempElements = elements.substring(beginning);
            end = tempElements.indexOf("\"/>");
            String imageUrl = tempElements.substring(0,end);

            viewHolder.webView.loadData(getPicture(viewHolder,imageUrl) , "text/html", null);

            goToSocial(viewHolder, url);

        } else if (url.indexOf("youtube")!=-1) {
            viewHolder.socialMediaName.setText("Youtube");

            Downloader downloader = new Downloader();
            downloader.execute(url);
            try {
                elements = downloader.get();
            } catch (Exception e) {
                Log.e("ERROR IN THREAD",e.toString());
            }

            beginning = elements.indexOf("/user/");
            tempElements = elements.substring(beginning+6);
            end = tempElements.indexOf("/");
            String usernameText = tempElements.substring(0,end);
            viewHolder.username.setText(usernameText);

            beginning = elements.indexOf("subscriberCountText")+39;
            tempElements = elements.substring(beginning);
            end = tempElements.indexOf(" subscriber")+11;
            String fullnameText = tempElements.substring(0,end);
            viewHolder.fullname.setText(fullnameText);

            viewHolder.followers.setText("");
            viewHolder.followersTag.setText("");

            beginning = elements.indexOf("\"avatar\"")+32;
            tempElements = elements.substring(beginning);
            end = tempElements.indexOf("\",\"");
            String imageUrl = tempElements.substring(0,end);

            viewHolder.webView.loadData(getPicture(viewHolder,imageUrl) , "text/html", null);

            goToSocial(viewHolder, url);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username, fullname, followers, socialMediaName, followersTag;
        public CircleImageView image_profile;
        public WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            fullname = itemView.findViewById(R.id.fullname);
            followers = itemView.findViewById(R.id.followers);
            socialMediaName = itemView.findViewById(R.id.socialMediaName);
            followersTag = itemView.findViewById(R.id.followersTag);
            image_profile = itemView.findViewById(R.id.image_profile);
            webView = itemView.findViewById(R.id.html_text);
        }
    }

    class Downloader extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = null;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = reader.readLine())!=null) {
                    result+=line;
                }
                connection.disconnect();
            } catch (Exception e) {
                Log.e("ERROR FETCHING", e.toString());
            }
            return result;
        }
    }

    public String getPicture (ViewHolder viewHolder, String imageUrl) {
        return  "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "img{max-width: 100%; width:auto; height: auto;}" +
                "img {border-radius: 50%}\n" +
                "</style>" +
                "</head>" +
                "<body style=\"background-color:black\">\n" +
                "<center>\n" +
                "<img width=\"100%\" height=\"100%\" src=\""+imageUrl+"\">\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";
    }

    public void goToSocial(ViewHolder viewHolder, final String url) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(intent);
            }
        });
    }
}
