package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import CallBacks.Presenter;

import com.sayed.learnigretrofitlearning.R;

public class MainActivity extends AppCompatActivity {

    private Presenter presenter;
    private TextView salaryTV;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadContents();
        initListeners();
    }

    private void initListeners() {
       button.setOnClickListener(l ->{
           presenter.getSalary(editText.getText().toString() , value ->{
               salaryTV.setText(value);
           });
       });
    }

    private void loadContents() {
        presenter = new Presenter();
    }

    private void initViews() {
        this.salaryTV = findViewById(R.id.salaryTV);
        this.button = findViewById(R.id.button);
        this.editText = findViewById(R.id.editText);
    }
}