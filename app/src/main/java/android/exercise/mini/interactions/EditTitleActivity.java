package android.exercise.mini.interactions;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

    private static final long MOVE = 200L;
    private boolean isEditing = false;
    private FloatingActionButton fabStartEdit;
    private FloatingActionButton fabEditDone;
    private TextView textViewTitle;
    private EditText editTextTitle;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_title);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // find all views
        fabStartEdit = findViewById(R.id.fab_start_edit);
        fabEditDone = findViewById(R.id.fab_edit_done);
        textViewTitle = findViewById(R.id.textViewPageTitle);
        editTextTitle = findViewById(R.id.editTextPageTitle);

        // setup - start from static title with "edit" button
        fabStartEdit.setVisibility(View.VISIBLE);
        fabEditDone.setVisibility(View.GONE);
        textViewTitle.setText("Page title here");
        textViewTitle.setVisibility(View.VISIBLE);
        editTextTitle.setText("Page title here");
        editTextTitle.setVisibility(View.GONE);

        // handle clicks on "start edit"
        fabStartEdit.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "start edit" FAB
      2. animate in the "done edit" FAB
      3. hide the static title (text-view)
      4. show the editable title (edit-text)
      5. make sure the editable title's text is the same as the static one
      6. optional (HARD!) make the keyboard to open with the edit-text focused,
          so the user can start typing without the need another click on the edit-text

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
            // animate out the "start edit" FAB
            fabStartEdit.animate().alpha(0f).translationX(MOVE).translationY(MOVE).setDuration(200L)
                    .withEndAction(() -> fabStartEdit.setVisibility(View.GONE)).start();

            // animate in the "done edit" FAB
            fabEditDone.setVisibility(View.VISIBLE);
            fabEditDone.animate().alpha(1f).translationX(0).translationY(0).setDuration(200L).start();

            // hide the static title (text-view)
            textViewTitle.setVisibility(View.GONE);

            // show the editable title (edit-text)
            editTextTitle.setVisibility(View.VISIBLE);

            // make sure the editable title's text is the same as the static one
            editTextTitle.setText(textViewTitle.getText());

            // make the keyboard to open with the edit-text focused
            editTextTitle.requestFocus();
            imm.showSoftInput(editTextTitle, InputMethodManager.SHOW_IMPLICIT);

            isEditing = true;
        });

        // handle clicks on "done edit"
        fabEditDone.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "done edit" FAB
      2. animate in the "start edit" FAB
      3. take the text from the user's input in the edit-text and put it inside the static text-view
      4. show the static title (text-view)
      5. hide the editable title (edit-text)
      6. make sure that the keyboard is closed

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
            // animate out the "done edit" FAB
            fabEditDone.animate().alpha(0f).translationX(MOVE).translationY(MOVE).setDuration(200L)
                    .withEndAction(() -> fabEditDone.setVisibility(View.GONE)).start();

            // animate in the "start edit" FAB
            fabStartEdit.setVisibility(View.VISIBLE);
            fabStartEdit.animate().alpha(1f).translationX(0).translationY(0).setDuration(200L).start();

            // take the text from the user's input in the edit-text and put it inside the static text-view
            textViewTitle.setText(editTextTitle.getText());

            // show the static title (text-view)
            textViewTitle.setVisibility(View.VISIBLE);

            // hide the editable title (edit-text)
            editTextTitle.setVisibility(View.GONE);

            // make sure that the keyboard is closed
            imm.hideSoftInputFromWindow(textViewTitle.getWindowToken(), 0);

            isEditing = false;
        });
    }

    @Override
    public void onBackPressed() {
        // BACK button was clicked
        if (isEditing) {
            // hide the edit-text
            editTextTitle.setVisibility(View.GONE);

            // show the static text-view with previous text (discard user's input)
            textViewTitle.setVisibility(View.VISIBLE);

            // animate out the "done-edit" FAB
            fabEditDone.animate().alpha(0f).translationX(MOVE).translationY(MOVE).setDuration(200L)
                    .withEndAction(() -> fabEditDone.setVisibility(View.GONE)).start();

            // animate in the "start edit" FAB
            fabStartEdit.setVisibility(View.VISIBLE);
            fabStartEdit.animate().alpha(1f).translationX(0).translationY(0).setDuration(200L).start();

            isEditing = false;
        } else {
            super.onBackPressed();
        }
    }
}