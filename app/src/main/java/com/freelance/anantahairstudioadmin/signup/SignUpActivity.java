package com.freelance.anantahairstudioadmin.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudioadmin.R;
import com.freelance.anantahairstudioadmin.databinding.ActivitySignUpBinding;
import com.freelance.anantahairstudioadmin.home.HomeActivity;
import com.freelance.anantahairstudioadmin.utils.PrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 234;
    GoogleSignInClient googleSignInClient;
    AuthenticationLoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        loginViewModel = new ViewModelProvider(this).get(AuthenticationLoginViewModel.class);
        PrefManager.getInstance(this, true);

        initializationOfGoogleSigninOption();
        clickViews();
        observer();
    }

    private void observer() {
        loginViewModel.authenticationLiveData().observe(this, new Observer<Authentication>() {
            @Override
            public void onChanged(Authentication authentication) {
                if (authentication != null){
                    PrefManager.getInstance().putString(R.string.authToken,authentication.getData().getToken());

                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                    Log.i("authentication","token: "+authentication.getData().getToken());
                }
            }
        });

    }

    private void clickViews() {
        binding.googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void initializationOfGoogleSigninOption() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);

                firebaseAuthwithGoogle(googleSignInAccount);
            } catch (ApiException e) {
                e.printStackTrace();

//                Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthwithGoogle(GoogleSignInAccount accnt) {
//        Toast.makeText(this, "firebaseAuthwithGoogle" + accnt.getId(), Toast.LENGTH_SHORT).show();
        AuthCredential credential = GoogleAuthProvider.getCredential(accnt.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String name = mAuth.getCurrentUser().getDisplayName();
                            String email = mAuth.getCurrentUser().getEmail();
                            Uri uri = mAuth.getCurrentUser().getPhotoUrl();
                            String profileImg = uri.toString();

                            loginViewModel.authentication(email);



//                            Toast.makeText(LoginActivity.this, "Successfully logged", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
            finish();
            Log.i("authentication","token: "+PrefManager.getInstance().getString(R.string.authToken));

        }
    }
}
