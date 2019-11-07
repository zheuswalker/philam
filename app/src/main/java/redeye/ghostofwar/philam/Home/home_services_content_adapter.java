package redeye.ghostofwar.philam.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import redeye.ghostofwar.philam.Configs.EndPoints;
import redeye.ghostofwar.philam.ServicesOffered.ProductsOffered;
import redeye.ghostofwar.philam.R;

public class home_services_content_adapter extends RecyclerView.Adapter<home_services_content_adapter.AllcurrentHolder>{

    private Context context;
    private List<home_services_content_constructors> home_services_content_constructors;
    public Integer count = 0;
    public  static String ipaddress;

    public home_services_content_adapter(Context context, List<home_services_content_constructors> home_services_content_constructors)
    {
        this.context = context;
        this.home_services_content_constructors = home_services_content_constructors;


    }

    @Override
    public AllcurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_services_offered_template, null);
        AllcurrentHolder holder = new AllcurrentHolder(view);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        return holder;
    }
    @Override
    public void onBindViewHolder(final AllcurrentHolder holder, int position) {

        final home_services_content_constructors feed_required_settergetter = home_services_content_constructors.get(position);
        holder.notifiername.setText(feed_required_settergetter.pso_service_name());
        holder.activitycontent.setText(feed_required_settergetter.pso_service_desc());
        holder.serviceview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsOffered.class);
                intent.putExtra("ProductName",holder.notifiername.getText());
                context.startActivity(intent);
            }
        });

        int selectedColor = Color.parseColor("#b8123e");
        String td = feed_required_settergetter.pso_service_name().trim();
        if(td.indexOf(" ")>0) {
            td = td.substring(0, 1) + td.substring(td.indexOf(" "), td.indexOf(" ") + 2);
            td=td.replace(" ","");
        }
        else
            td = td.substring(0,1);
        TextDrawable drawable1 = TextDrawable.builder()
                .buildRound(td.toUpperCase(), selectedColor);
        holder.serviceicon.setImageDrawable(drawable1);


    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return home_services_content_constructors.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView activitycontent;
        ImageView serviceicon;
        TextView notifiername;
        CardView serviceview;
        public AllcurrentHolder(final View itemView) {
            super(itemView);
            notifiername= itemView.findViewById(R.id.notifiername);
            activitycontent = itemView.findViewById(R.id.activitycontent);
            serviceicon = itemView.findViewById(R.id.serviceicon);
            serviceview = itemView.findViewById(R.id.serviceview);

        }
    }

}
