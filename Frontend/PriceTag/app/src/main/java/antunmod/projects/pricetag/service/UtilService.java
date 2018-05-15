package antunmod.projects.pricetag.service;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;

/**
   Util service for methods used by multiple Fragments and Activities.
 */
public class UtilService {


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
}
