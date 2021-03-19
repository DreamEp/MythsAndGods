package fr.esilv.mythsandgods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import fr.esilv.mythsandgods.Category.CategoryActivity;

public class MainActivity extends AppCompatActivity {

    Button signout;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth auth;
    int RC_SIGN_IN =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
            }
        });
        signin = findViewById(R.id.google_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                signin.setVisibility(View.GONE);
                signout.setVisibility(View.GONE);
                signIn();
                /*
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                signin.setVisibility(View.VISIBLE);
                signout.setVisibility(View.VISIBLE);*/
            }
        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                if (account != null) {
                    firebaseAuthWithGoogle(account);
                } else{
                    Toast.makeText(MainActivity.this, "Sign-in failed, try again later.", Toast.LENGTH_LONG).show();
                }

                //handleSignInResult(task);
            } catch (ApiException e){

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String user_id = auth.getCurrentUser().getUid();
                            final DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("USERS").child(user_id);

                            current_user_db.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null) {
                                        //user exists, do something
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Connexion réussis", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //user does not exist, do something else
                                        String user_email = auth.getCurrentUser().getEmail();
                                        String user_phone = auth.getCurrentUser().getPhoneNumber();
                                        String user_photo = auth.getCurrentUser().getPhotoUrl().toString();
                                        String user_name = auth.getCurrentUser().getDisplayName();

                                        Map newPost = new HashMap();
                                        newPost.put("name", user_name);
                                        newPost.put("email", user_email);
                                        newPost.put("photo",user_photo);
                                        newPost.put("phone", user_phone);

                                        current_user_db.setValue(newPost);


                                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Création bien réussis", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });




                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Connection impossible", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    /*
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            updateUI(null);
        }
    }*/

}
