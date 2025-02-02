package com.example.imageinstagram;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imageinstagram.Adapter.EmojiAdapter;
import com.example.imageinstagram.Interface.EmojiFragmentListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ja.burhanrashid52.photoeditor.PhotoEditor;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmojiFragment extends BottomSheetDialogFragment implements EmojiAdapter.EmojiAdapterListener {

    RecyclerView recycler_emoji;
    static  EmojiFragment instance;

    EmojiFragmentListener listener;
    private String emoji;

    public void  setListener(EmojiFragmentListener listener){
        this.listener = listener;
    }


    public static EmojiFragment getInstance(){
        if(instance==null)
            instance = new EmojiFragment();
        return instance;
    }

    public EmojiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView =  inflater.inflate(R.layout.fragment_emoji, container, false);

        recycler_emoji = (RecyclerView)itemView.findViewById(R.id.recycler_emoji);
        recycler_emoji.setHasFixedSize(true);
        recycler_emoji.setLayoutManager(new GridLayoutManager(getActivity(),5));

        EmojiAdapter adapter = new EmojiAdapter(getContext(),PhotoEditor.getEmojis(itemView.getContext()),listener);

        recycler_emoji.setAdapter(adapter);
        return itemView;
    }

    @Override
    public void onEmojiItemSelected(String emoji) {

        listener.onEmojiSelected(emoji);
    }
}
