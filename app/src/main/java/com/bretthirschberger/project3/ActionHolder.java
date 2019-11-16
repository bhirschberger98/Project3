package com.bretthirschberger.project3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActionHolder.ActionHolderListener} interface
 * to handle interaction events.
 * Use the {@link ActionHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionHolder extends Fragment implements View.OnDragListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String AMOUNT_OF_HOLDERS = "holders";
    private static final String ARG_PARAM2 = "param2";

    private ImageView mHolders[];
    private Direction[] mDirections;
    private LinearLayout mLayout;

    private int mAmountOfHolders;

    private ActionHolderListener mListener;

    public ActionHolder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ActionHolder.
     */
    public static ActionHolder newInstance(int param1) {
        ActionHolder fragment = new ActionHolder();
        Bundle args = new Bundle();
        args.putInt(AMOUNT_OF_HOLDERS, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAmountOfHolders = getArguments().getInt(AMOUNT_OF_HOLDERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDirections = new Direction[mAmountOfHolders];
        mHolders = new ImageView[mAmountOfHolders];
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_action_holder, container, false);
        for (int i = 0; i < mAmountOfHolders; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark_normal_background));
            mHolders[i] = imageView;
        }
        Log.i("Amount of holders", mAmountOfHolders + "");
        mLayout = v.findViewById(R.id.holders);
        for (int i = 0; i < mHolders.length; i++) {
            final int index = i;
            mHolders[i].setOnDragListener((dragged, event) -> {
                int dragEvent = event.getAction();
                ImageView target = (ImageView) dragged;
                final ImageView view = (ImageView) event.getLocalState();
                if (dragEvent == DragEvent.ACTION_DROP) {
                    target.setImageDrawable(view.getDrawable());
                    Log.i("ID", dragged.getId() + "");
                    Log.i("ID img", view.getId() + "");
                    Log.i("ID UP", R.id.up + "");
                    switch (view.getId()) {
                        case R.id.up:
                            mDirections[index] = Direction.UP;
                            break;
                        case R.id.down:
                            mDirections[index] = Direction.DOWN;
                            break;
                        case R.id.left:
                            mDirections[index] = Direction.LEFT;
                            break;
                        case R.id.right:
                            mDirections[index] = Direction.RIGHT;
                            break;
                        default:
                            mDirections[index] = Direction.NONE;
                            break;
                    }
                }
                return true;
            });
            mLayout.addView(mHolders[i]);
        }
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActionHolderListener) {
            mListener = (ActionHolderListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }


    public Direction[] getDirections() {
        return mDirections;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ActionHolderListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
