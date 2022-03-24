package com.wikilift.uf2soundplayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import com.wikilift.uf2soundplayer.data.bbdd.DatabaseApp;
import com.wikilift.uf2soundplayer.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

public class MainActivity extends AppCompatActivity/* implements
        NavigationView.OnNavigationItemSelectedListener */{

    private ActivityMainBinding binding;
    public static DatabaseApp masterDB;
    public static boolean init=true;
    private NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupNavigation();
        changeColorStatusBar();
        setListener();
        masterDB=new DatabaseApp(this);


    }

    private void setListener() {
        binding.navigationView.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,MainActivity.this.getResources().getString(R.string.thanks),Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                       MainActivity.this.finish();
                    }
                }, 1000);

            }
        });
    }

    private void changeColorStatusBar(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Crimson));
    }
    // Setting Up One Time Navigation
    private void setupNavigation() {


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();


        //navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);

        NavigationUI.setupWithNavController(binding.navigationView, navController);

        //binding.navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), binding.drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        binding.drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (menuItem.getItemId()) {

            case R.id.addFragment:
                Toast.makeText(MainActivity.this,"pene",Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.addFragment);
                break;

            case R.id.landingFragment2:

                navController.navigate(R.id.landingFragment2);
                break;




        }
        return true;

    }*/
}