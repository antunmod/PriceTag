package antunmod.projects.pricetag.service;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
   Util service for methods used by multiple Fragments and Activities.
 */
public class UtilService {

    private static final String SERVER_ERROR_STRING = "Poslužitelj je u stanju hibernacije, pričekajte malo pa pokušajte ponovo";
    protected static final String NON_EXISTING_ACCOUNT = "Nije pronađen račun za unesene informacije";
    public static final String LOGIN_ERROR = "Korisnik s unesenim korisničkim imenom i lozinkom ne postoji!";


    /**
      Shows the progress and hides the current view.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, final View currentView, final View loadingView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = currentView.getResources().getInteger(android.R.integer.config_shortAnimTime);


            currentView.setVisibility(show ? View.GONE : View.VISIBLE);
            currentView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    currentView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });


            loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
            loadingView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            currentView.setVisibility(show ? View.VISIBLE : View.GONE);
            loadingView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboardIn(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static boolean sqlInjectionTest(String string) {
        return string.contains(";") || string.contains("\"") || string.contains(")");
    }

    public static void toastServerError(Context context) {
        Toast.makeText(context, SERVER_ERROR_STRING, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
