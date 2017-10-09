package bd.edu.iub.secs.ccse.bloodmanager.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bd.edu.iub.secs.ccse.bloodmanager.R;
import business.DonorTO;

/**
 * Created by User on 9/30/2015.
 */
public class DonorListAdapter extends ArrayAdapter<DonorTO>{

    private Context context;
    private ArrayList<DonorTO> list;
    public DonorListAdapter(Context context, ArrayList<DonorTO> list) {
        super(context, R.layout.item_layout);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getPosition(DonorTO item) {
        return super.getPosition(item);
    }

    @Override
    public DonorTO getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_layout, parent, false);
        }
        TextView email = (TextView) v.findViewById(R.id.tvEmail);
        TextView phone = (TextView) v.findViewById(R.id.tvPhone);

        DonorTO donorTO = list.get(position);
        email.setText(donorTO.getEmail());
        phone.setText(donorTO.getPhone());
        return v;
    }
}
