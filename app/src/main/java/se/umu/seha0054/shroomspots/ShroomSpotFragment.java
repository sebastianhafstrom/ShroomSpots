package se.umu.seha0054.shroomspots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

public class ShroomSpotFragment extends Fragment {
    private static final int REQUEST_DATE = 0;
    public static final String TEST = "ShroomSpot.ID";
    public static final String DIALOG_DATE = "DialogDate";

    private ShroomSpot shroomSpot;
    private Button dateButton;

    public static ShroomSpotFragment newInstance(UUID ssID){
        Bundle args = new Bundle();
        args.putSerializable(TEST, ssID);

        ShroomSpotFragment fragment = new ShroomSpotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID ssID = (UUID)getArguments().getSerializable(TEST);
        shroomSpot = ShroomSpotList.get(getActivity()).getShroomSpot(ssID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_shroomspot, parent, false);
        TextView title = v.findViewById(R.id.shroomspot_title);
        title.setText(shroomSpot.getTitle());
        dateButton = v.findViewById(R.id.shroomspot_date);
        dateButton.setText(shroomSpot.getLastVisited().toString());
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(shroomSpot.getLastVisited());
                dialog.setTargetFragment(ShroomSpotFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        CheckBox c = v.findViewById(R.id.shroomspot_emptied);
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shroomSpot.setEmptied(b);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            shroomSpot.setLastVisited(date);
            updateDate();
        }
    }

    private void updateDate() {
        dateButton.setText(shroomSpot.getLastVisited().toString());
    }

}
