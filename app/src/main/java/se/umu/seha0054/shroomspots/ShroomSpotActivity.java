package se.umu.seha0054.shroomspots;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ShroomSpotActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        UUID ssID = (UUID)getIntent().getSerializableExtra(ShroomSpotFragment.TEST);
        return ShroomSpotFragment.newInstance(ssID);
    }
}
