package com.guitarlearn.guitarlearn.mvvm.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guitarlearn.guitarlearn.R;
import com.guitarlearn.guitarlearn.common.RecyclerViewEvents;
import com.guitarlearn.guitarlearn.databinding.FragmentMenuBinding;
import com.guitarlearn.guitarlearn.mvvm.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MenuFragment extends Fragment implements RecyclerViewEvents.Listener<String> {

    private List<String> list = new ArrayList<>();
    private FragmentMenuBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = Arrays.asList(getResources().getStringArray(R.array.menu_items));
        MenuAdapter adapter = new MenuAdapter(getContext(), list, this);
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(String item, View v, int position) {

    }
}
