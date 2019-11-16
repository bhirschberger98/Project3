package com.bretthirschberger.project3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FrameLayout mGameBoard;

    private OnFragmentInteractionListener mListener;

    private ImageView mPlayer;

    public GameBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBoardFragment newInstance(String param1, String param2) {
        GameBoardFragment fragment = new GameBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_board, container, false);
        mGameBoard = view.findViewById(R.id.game_frame);
        mPlayer = view.findViewById(R.id.player);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void setGameBoard(Drawable gameBoard) {
        mGameBoard.setBackground(gameBoard);
    }

    public void movePlayer(Direction[] userInput, Direction[] correctAnswer, int[] animationCoards) {
        int delay = 0;
        int up = 1;
        int down = 1;
        int left = 1;
        int right = 1;
        ObjectAnimator lastAnimation = ObjectAnimator.ofFloat(mPlayer, "translationY", 0);
        ObjectAnimator[] animation = new ObjectAnimator[userInput.length];
        for (int i = 0; i < userInput.length; i++) {
            if (userInput[i] == correctAnswer[i]) {
                switch (userInput[i]) {
                    case UP:
                        animation[i] = ObjectAnimator.ofFloat(mPlayer, "translationY", animationCoards[0] * up++);
                        break;
                    case DOWN:
                        animation[i] = ObjectAnimator.ofFloat(mPlayer, "translationY", animationCoards[1] * down++);
                        break;
                    case LEFT:
                        animation[i] = ObjectAnimator.ofFloat(mPlayer, "translationX", animationCoards[2] * left++);
                        break;
                    case RIGHT:
                        animation[i] = ObjectAnimator.ofFloat(mPlayer, "translationX", animationCoards[3] * right++);
                        break;
                    default:
                        animation[i] = null;
                        break;
                }
                if (animation[i] != null) {
                    animation[i].setDuration(2000);
                    animation[i].setStartDelay(2000 * delay);
                    animation[i].start();
                    delay++;
                }
            } else {
                mListener.increaseAttempts();
                Toast.makeText(getContext(),getResources().getText(R.string.try_again),Toast.LENGTH_SHORT).show();
                reset(delay);
                return;
            }
            Log.i("Direction", userInput[i].toString());
        }
        animation[animation.length - 1].addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mListener.goToNextLevel();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void reset(int delay) {
        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mPlayer, "translationY", 0);
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mPlayer, "translationX", 0);
        yAnimator.setStartDelay(2000 * delay);
        xAnimator.setStartDelay(2000 * delay);
        yAnimator.setDuration(0).start();
        xAnimator.setDuration(0).start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        void increaseAttempts();

        void goToNextLevel();
    }
}