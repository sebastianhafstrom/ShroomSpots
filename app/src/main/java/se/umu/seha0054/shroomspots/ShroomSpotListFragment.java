package se.umu.seha0054.shroomspots;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ShroomSpotListFragment extends Fragment {
    private RecyclerView shroomSpotRecyclerView;
    private ShroomSpotAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shroomspot_list, container, false);

        shroomSpotRecyclerView = v.findViewById(R.id.shroomspot_recycler_view);
        shroomSpotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        ShroomSpotList list = ShroomSpotList.get(getActivity());
        List<ShroomSpot> shroomSpots = list.getShroomSpots();

        adapter = new ShroomSpotAdapter(shroomSpots);
        shroomSpotRecyclerView.setAdapter(adapter);
    }

    private class ShroomSpotAdapter extends RecyclerView.Adapter<ShroomSpotHolder>{
        private List<ShroomSpot> shroomSpots;

        public ShroomSpotAdapter(List<ShroomSpot> list){
            shroomSpots = list;
        }

        @Override
        public ShroomSpotHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ShroomSpotHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ShroomSpotHolder holder, int position){
            ShroomSpot shroomSpot = shroomSpots.get(position);
            holder.bind(shroomSpot);
        }

        @Override
        public int getItemCount(){
            return shroomSpots.size();
        }
    }

    private class ShroomSpotHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ShroomSpot shroomSpot;
        private TextView title;
        private TextView date;
        private TextView empty;

        public ShroomSpotHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_shroomspot, parent, false));
            title = itemView.findViewById(R.id.shroomspot_title_list);
            date = itemView.findViewById(R.id.shroomspot_date_list);
            empty = itemView.findViewById(R.id.shroomspot_emptied_list);
            itemView.setOnClickListener(this);
        }
        public void bind(ShroomSpot s) {
            shroomSpot = s;
            title.setText(shroomSpot.getTitle());
            date.setText(shroomSpot.getLastVisited().toString());
            if(shroomSpot.isEmptied())
                empty.setText("Tömd");
            else if (!shroomSpot.isEmptied())
                empty.setText("Fylld");
        }
        @Override
        public void onClick(View v){
            Intent i = new Intent(getActivity(), ShroomSpotActivity.class);
            i.putExtra(ShroomSpotFragment.TEST, shroomSpot.getSSID());
            startActivity(i);
        }
    }
}
