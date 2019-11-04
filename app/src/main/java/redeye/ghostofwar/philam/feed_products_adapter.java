package redeye.ghostofwar.philam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class feed_products_adapter extends RecyclerView.Adapter<feed_products_adapter.AllcurrentHolder>{

    private Context context;
    private List<feed_notification_required_settergetter> feed_notification_required_settergetter;
    public Integer count = 0;
    public  static String ipaddress;

    public feed_products_adapter(Context context, List<feed_notification_required_settergetter> feed_notification_required_settergetter)
    {
        this.context = context;
        this.feed_notification_required_settergetter = feed_notification_required_settergetter;
        ipaddress = EndPoints.BASE_URL;

    }

    @Override
    public AllcurrentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_feed_notification_template, null);
        AllcurrentHolder holder = new AllcurrentHolder(view);
        int width = parent.getMeasuredWidth();
        view.setMinimumWidth(width);
        return holder;
    }
    @Override
    public void onBindViewHolder(final AllcurrentHolder holder, int position) {

        final feed_notification_required_settergetter feed_required_settergetter = feed_notification_required_settergetter.get(position);
        holder.notifiername.setText(feed_required_settergetter.pso_service_name());
        holder.activitycontent.setText(feed_required_settergetter.pso_service_desc());
        holder.notifiername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, market_product_fulldetails.class);
                intent.putExtra("ProductName",holder.notifiername.getText());
                context.startActivity(intent);
            }
        });

    }
    Integer rowindex = 0;
    @Override
    public int getItemCount() {
        return feed_notification_required_settergetter.size();
    }
    public class AllcurrentHolder extends RecyclerView.ViewHolder{

        TextView activitycontent, activitydate;
        ImageView notifierimage,imotion;
        TextView notifiername;
        LinearLayout notiflayout;
        ImageView activityicon;
        RelativeLayout activityiconlayout;
        Button followback;
        public AllcurrentHolder(final View itemView) {
            super(itemView);
            notifiername= itemView.findViewById(R.id.notifiername);
            activitycontent = itemView.findViewById(R.id.activitycontent);

        }
    }

}
