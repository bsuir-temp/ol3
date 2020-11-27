package com.bsuir.oitip.lab3.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bsuir.oitip.lab3.R;
import com.bsuir.oitip.lab3.model.Question;
import com.bsuir.oitip.lab3.model.Wrapper;
import com.bsuir.oitip.lab3.retrofit.RetroPoints;
import com.bsuir.oitip.lab3.retrofit.RetrofitCore;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFragment extends FragmentEvent {

    public static final String TAG = "QuestionFragment";
    private int session_index = 0;
    public List<Question> questions = new ArrayList();
    private View[] items = new View[9];
    private Random random = new Random();
    private int correctAnswer = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private boolean lock = false;

    private View.OnClickListener closeQuize = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new MainMenuFragment();
            getFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    };

    private View.OnClickListener submitAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(lock)return;
            items[2+correctAnswer].setBackgroundResource(R.drawable.button_background_success);
            switch (v.getId())
            {
                case R.id.answer_1:
                    if(0!=correctAnswer) {
                        v.setBackgroundResource(R.drawable.button_background_danger);
                        incorrectAnswers++;
                    }else correctAnswers++;


                    break;
                case R.id.answer_2:
                    if(1!=correctAnswer)
                    {
                        v.setBackgroundResource(R.drawable.button_background_danger);
                        incorrectAnswers++;
                    }else correctAnswers++;
                    break;
                case R.id.answer_3:
                    if(2!=correctAnswer)
                    {
                        v.setBackgroundResource(R.drawable.button_background_danger);
                        incorrectAnswers++;
                    }else correctAnswers++;
                    break;
                case R.id.answer_4:
                    if(3!=correctAnswer)
                    {
                        v.setBackgroundResource(R.drawable.button_background_danger);
                        incorrectAnswers++;
                    }else correctAnswers++;
                    break;
            }
            new Thread(() -> {
                lock = true;
                try { Thread.sleep(800); } catch (InterruptedException unimportant) { }
                getActivity().runOnUiThread(() -> {
                    onNext();
                    lock = false;
                });

            }).start();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetroPoints service = RetrofitCore.getRetrofitInstance().create(RetroPoints.class);
        Call<Wrapper> call = service.getQuestions();
        call.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                questions = response.body().results;
                items[6].setVisibility(View.GONE);
                items[7].setVisibility(View.VISIBLE);
                onNext();
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                DisconnectedFragment fragment = new DisconnectedFragment();
                getFragmentManager().beginTransaction().replace(R.id.content,fragment,TAG).commit();
                onError(t.getLocalizedMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, null);
        items[0] = view.findViewById(R.id.question);
        items[1] = view.findViewById(R.id.category);
        items[2] = view.findViewById(R.id.answer_1);
        items[3] = view.findViewById(R.id.answer_2);
        items[4] = view.findViewById(R.id.answer_3);
        items[5] = view.findViewById(R.id.answer_4);
        for(int i=2;i<6;i++)items[i].setOnClickListener(submitAnswer);
        items[6] = view.findViewById(R.id.loading_frame);
        items[7] = view.findViewById(R.id.question_frame);
        items[8] = view.findViewById(R.id.close_btn);
        items[8].setOnClickListener(closeQuize);
        return view;
    }

    public void onNext() {
        try {
            if(session_index==10)
            {
                Fragment fragment = new ResultsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("correct",correctAnswers);
                bundle.putInt("incorrect",incorrectAnswers);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
                return;
            }
            Question question = questions.get(session_index++);
            correctAnswer = random.nextInt(4);
            ((TextView)items[0]).setText(java.net.URLDecoder.decode(question.question, StandardCharsets.UTF_8.name()));
            ((TextView)items[1]).setText(java.net.URLDecoder.decode(question.category, StandardCharsets.UTF_8.name()));
            for(int i=2;i<6;i++)items[i].setBackgroundResource(R.drawable.button_background_default);
            int i = 2;
            for (String answer : question.incorrect_answers) {
                if (correctAnswer + 2 == i)
                    ((TextView)items[i++]).setText(java.net.URLDecoder.decode(question.correct_answer,StandardCharsets.UTF_8.name()));
                ((TextView)items[i++]).setText(java.net.URLDecoder.decode(answer, StandardCharsets.UTF_8.name()));
            }
            if (correctAnswer == 3)
                ((TextView)items[5]).setText(java.net.URLDecoder.decode(question.correct_answer,StandardCharsets.UTF_8.name()));
        }catch (Exception ex){ex.printStackTrace();}
    }

}