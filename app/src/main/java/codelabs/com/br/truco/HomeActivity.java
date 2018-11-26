package codelabs.com.br.truco;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends Activity {

    private FirebaseUser user;

    private TextView firstMark;
    private TextView secondMark;
    private EditText firstName;
    private EditText secondName;

    private Integer roundTeamOne;
    private Integer roundTeamTwo;

    private Integer firstCounter;
    private Integer secondCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firstMark = findViewById(R.id.firstMark);
        secondMark = findViewById(R.id.secondMark);

        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);

        firstCounter = 0;
        secondCounter = 0;

        firstMark.setText(String.valueOf(firstCounter));
        secondMark.setText(String.valueOf(secondCounter));

        firstName.setText(String.valueOf("Equipe 1"));
        secondName.setText(String.valueOf("Equipe 2"));

        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    public void sumFirstButton(View v) {
        if (firstCounter == 11) {
            Toast.makeText(HomeActivity.this, firstName.getText().toString() + " ganhou esta rodada!", Toast.LENGTH_SHORT).show();
            firstCounter = 0;
            firstMark.setText(String.valueOf(firstCounter));
        } else {
            firstCounter = firstCounter + 1;
            firstMark.setText(String.valueOf(firstCounter));
        }
    }

    public void subSecondButton(View v) {
        if (firstCounter != 0)
            firstCounter = firstCounter - 1;
        firstMark.setText(String.valueOf(firstCounter));
    }

    public void sumSecondButton(View v) {
        if (secondCounter == 11) {
            Toast.makeText(HomeActivity.this, secondName.getText().toString() + " ganhou esta rodada!", Toast.LENGTH_SHORT).show();
            secondCounter = 0;
            secondMark.setText(String.valueOf(secondCounter));
        } else {
            secondCounter = secondCounter + 1;
            secondMark.setText(String.valueOf(secondCounter));
        }
    }

    public void subFirstButton(View v) {
        if (secondCounter != 0)
            secondCounter = secondCounter - 1;
        secondMark.setText(String.valueOf(secondCounter));
    }

}
