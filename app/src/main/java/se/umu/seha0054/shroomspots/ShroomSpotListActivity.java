package se.umu.seha0054.shroomspots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ShroomSpotListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ShroomSpotListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if(itemID == R.id.action_add_shroomspot){
            //Handle the action
            Intent i = new Intent(this,  AddNewShroomSpot.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
