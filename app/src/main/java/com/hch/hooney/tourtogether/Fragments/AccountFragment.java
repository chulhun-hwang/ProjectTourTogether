package com.hch.hooney.tourtogether.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hch.hooney.tourtogether.DAO.DAO;
import com.hch.hooney.tourtogether.LoginActivity;
import com.hch.hooney.tourtogether.R;
import com.hch.hooney.tourtogether.SettingActivity;
import com.hch.hooney.tourtogether.SettingForEvent.AccountTabAdapter;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private final String TAG = "AccountFragment";
    private final int SIGNAL_SETTING = 7005;

    //Layout Resource
    private ViewPager viewPager;
    private AccountTabAdapter accountTabAdapter;
    private TabLayout tabLayout;

    private ImageView Setting;
    private TextView UserName;
    private ImageView UserProfile;

    //variable
    private View view;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        init();
        event();

        return view;
    }

    private void init(){
        Setting = (ImageView) view.findViewById(R.id.account_settingBTN);

        //유저명
        UserName = (TextView) view.findViewById(R.id.account_username);
        UserName.setText(DAO.user.getUNAME());

        //유저 프로필이미지
        UserProfile = (ImageView) view.findViewById(R.id.account_userImage);
        Picasso.with(getContext()).load(DAO.user.getUPROFILEIMAGE()).into(UserProfile);
        UserProfile.setBackground(new ShapeDrawable(new OvalShape()));
        UserProfile.setClipToOutline(true);

        viewPager = (ViewPager) view.findViewById(R.id.account_viewpager);

        tabLayout = (TabLayout) view.findViewById(R.id.account_tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.account_tab0));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.account_tab1)));
//        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.account_tab2)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.account_tab3)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        accountTabAdapter = new AccountTabAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(accountTabAdapter);
    }

    private void event(){
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivityForResult(intent, SIGNAL_SETTING);
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGNAL_SETTING){
            switch (resultCode){
                case 9008:
                    //logout
                    saveAutoLoginPreferences(0);
                    FirebaseAuth.getInstance().getCurrentUser().delete();
                    FirebaseAuth.getInstance().signOut();

                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                    break;
                default:
                    break;
            }
        }
    }
    private void saveAutoLoginPreferences(int num){
        //구글 1 페이스북 2 나머지 0
        SharedPreferences pref = getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("autologin", num);
        editor.commit();
    }
}
