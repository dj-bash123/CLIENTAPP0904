package in.co.snapqa.clientapp0903.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import in.co.snapqa.clientapp0903.AcceptedDeadlineSessionFragment;
import in.co.snapqa.clientapp0903.AcceptedLiveSessionFragment;
import in.co.snapqa.clientapp0903.MainActivity;
import in.co.snapqa.clientapp0903.NewDealsFragment;
import in.co.snapqa.clientapp0903.R;
import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.AcceptDealRequest;
import in.co.snapqa.clientapp0903.models.AcceptedDealResponse;
import in.co.snapqa.clientapp0903.models.NewDealResponses;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OLA on 28/03/17.
 */

public class NewDealsRecyclerViewAdapter extends RecyclerView.Adapter<NewDealsRecyclerViewAdapter.MyViewHolder> {


    NewDealResponses newDealResponsess;
    String ftime;
    Date date;
    Calendar cal;
    AlertDialog.Builder alertDialog;
    String testDate;

    MainActivity mainActivity = new MainActivity();

    Context context;

    SharedPreferences sharedpreferences;



    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";

    public NewDealsRecyclerViewAdapter(NewDealResponses newDealResponses){
        newDealResponsess = newDealResponses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_deal_rv, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.subject.setText(newDealResponsess.getResponses().get(position).getSubjectName());

        final String ty = newDealResponsess.getResponses().get(position).getDealType();
        Log.d("type ki value", "  " + ty);

        Log.d("jkhdjksahda  ","" + newDealResponsess.getResponses().get(position).getTimeTo() );

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        try {
            date = formatter.parse(String.valueOf(newDealResponsess.getResponses().get(position).getTimeTo()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -5);
        cal.add(Calendar.MINUTE, -30);



        if(Calendar.HOUR<10){

            if(Calendar.MINUTE<10){
                ftime = "0" +cal.get(Calendar.HOUR) + ":" + "0"+ cal.get(Calendar.MINUTE);
            }else {
                ftime = "0" +cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);
            }
        }else {
            if(Calendar.MINUTE<10){
                ftime = cal.get(Calendar.HOUR) + ":" + "0"+ cal.get(Calendar.MINUTE);
            }else {
                ftime = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);
            }
        }

        if(Calendar.DATE<10){
            if(Calendar.MONTH < 10){
                testDate = "0" + cal.get(Calendar.DATE) + "/" + "0" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
            }else {
                 testDate = "0" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
            }
        }else {
            if(Calendar.MONTH < 10){
                 testDate = cal.get(Calendar.DATE) + "/" + "0" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
            }else {
                 testDate = cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
            }
        }

        String testDate = cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
        holder.date.setText(testDate);

        if(ty.equals("Home Work") || ty.equals("HomeWork")){
            holder.leftIV.setBackgroundResource(R.drawable.hourglass);
            holder.type.setText("Deadline Session");
            holder.leftTV.setText(testDate+ ", " + ftime);
            holder.rightIV.setBackgroundResource(R.drawable.rupee);
            holder.rightTV.setText(Integer.toString(newDealResponsess.getResponses().get(position).getPointsOffered()));
            holder.type.setBackgroundResource(R.color.colorTurquoise);
        }else{
            holder.leftIV.setBackgroundResource(R.drawable.hourglass);
            holder.type.setText("Live Session");
            holder.leftTV.setText(ftime);
            holder.rightIV.setBackgroundResource(R.drawable.hourglass);
            holder.rightTV.setText(Integer.toString(newDealResponsess.getResponses().get(position).getPointsOffered()));
        }

        holder.linearLayout.setVisibility(LinearLayout.GONE);

        final LinearLayout layout = holder.linearLayout;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layout.getVisibility() == LinearLayout.VISIBLE) {
                    layout.setVisibility(LinearLayout.GONE);
                }else {
                    layout.setVisibility(LinearLayout.VISIBLE);
                }

            }
        });

        sharedpreferences = holder.itemView.getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String token = sharedpreferences.getString(Key, "notPresent");

        final AcceptDealRequest acceptDealRequest = new AcceptDealRequest();
        acceptDealRequest.set_id(newDealResponsess.getResponses().get(position).get_id());
        acceptDealRequest.setToken(token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(holder.itemView.getContext().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API service = retrofit.create(API.class);

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Call<AcceptedDealResponse> call = service.acceptedDeal(acceptDealRequest);
                call.enqueue(new Callback<AcceptedDealResponse>() {
                    @Override
                    public void onResponse(Call<AcceptedDealResponse> call, Response<AcceptedDealResponse> response) {
                        AcceptedDealResponse response1 = response.body();
                        if(response.body().getMessage().equals("Successful !! Deal Booked !!")){
                            Log.d(",,", "" + " deal boooked");

                            AppCompatActivity activity = (AppCompatActivity) v.getContext();

                            if(ty.equals("HomeWork") || ty.equals("Home Work")) {
                                AcceptedDeadlineSessionFragment newDealFragment = new AcceptedDeadlineSessionFragment();
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).addToBackStack(null).commit();
                            }else {
                                AcceptedLiveSessionFragment newDealFragment = new AcceptedLiveSessionFragment();
                                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_main_activity, newDealFragment).addToBackStack(null).commit();
                            }
                        }else{
                            Toast.makeText(holder.itemView.getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                            Log.d("error in recycle", "  " );
                        }
                    }
                    @Override
                    public void onFailure(Call<AcceptedDealResponse> call, Throwable t) {
                        Log.d(" error recycer no res", "   " + t.getMessage());
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        if(newDealResponsess != null) {
            return newDealResponsess.getResponses().size();
        }else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView subject, type, date, leftTV, rightTV, bookName, material, comments;
        LinearLayout linearLayout;
        ImageView leftIV, rightIV;
        Button accept, reject;

        Typeface font1 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensanslight.ttf");
        Typeface font2 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensansregular.ttf");
        Typeface font3 = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/opensanssemibold.ttf");


        public MyViewHolder(final View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_subject);
            type = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_type);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.new_deal_fragment_rv_extended_layout);
            date = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_date);
            leftTV = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_left_tv);
            rightTV=(TextView) itemView.findViewById(R.id.new_deal_fragment_rv_right_tv);
            bookName = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_book_name);
            material = (TextView) itemView.findViewById(R.id.new_deal_fragment_rv_material);

            leftIV = (ImageView) itemView.findViewById(R.id.new_deal_fragment_rv_left_iv);
            rightIV = (ImageView) itemView.findViewById(R.id.new_deal_fragment_rv_right_iv);

            accept = (Button) itemView.findViewById(R.id.new_deal_fragment_rv_accept);
            reject = (Button) itemView.findViewById(R.id.new_deal_fragment_rv_reject);

            accept.setTypeface(font2);
            reject.setTypeface(font2, Typeface.BOLD);
            subject.setTypeface(font3);
            type.setTypeface(font1);
            date.setTypeface(font1);
            leftTV.setTypeface(font1, Typeface.BOLD);
            rightTV.setTypeface(font1, Typeface.BOLD);
            bookName.setTypeface(font1);
            material.setTypeface(font1);





        }
    }
}
