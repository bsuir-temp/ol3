package com.bsuir.oitip.lab3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bsuir.oitip.lab3.R;
import com.bsuir.oitip.lab3.firebase.FirebaseAuthRepo;
import com.bsuir.oitip.lab3.model.User;
import com.bsuir.oitip.lab3.model.Wrapper;
import com.bsuir.oitip.lab3.retrofit.RetroPoints;
import com.bsuir.oitip.lab3.retrofit.RetrofitCore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends FragmentEvent {

    public static final String TAG = "LoginFragment";
    private FirebaseAuthRepo auth;
    private View items[] = new View[6];
    private View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User user = new User();
            user.email = ((EditText)items[0]).getText().toString();
            user.password = ((EditText)items[1]).getText().toString();
            items[4].setVisibility(View.GONE);
            items[5].setVisibility(View.VISIBLE);
            try {
                auth.signIn(getActivity(), user, task -> {
                    if (task.isSuccessful()) {
                        Fragment fragment = new MainMenuFragment();
                        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                    } else
                    {
                        items[5].setVisibility(View.GONE);
                        items[4].setVisibility(View.VISIBLE);
                        onError("Failed login");
                    }
                });
            }catch (Throwable error){
                items[5].setVisibility(View.GONE);
                items[4].setVisibility(View.VISIBLE);
                onError(error.getLocalizedMessage());
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = new FirebaseAuthRepo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, null);
        items[0] = root.findViewById(R.id.field_email);
        items[1] = root.findViewById(R.id.field_password);
        items[2] = root.findViewById(R.id.login_button);
        items[2].setOnClickListener(login);
        items[4] = root.findViewById(R.id.login_frame);
        items[5] = root.findViewById(R.id.loading_frame);
        return root;
    }

}
