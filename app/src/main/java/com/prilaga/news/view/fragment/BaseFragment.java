//package com.prilaga.news.view.fragment;
//
//
//import androidx.fragment.app.Fragment;
//import com.prilaga.news.view.activity.MainActivity;
//
///**
// * Created by Oleg Tarashkevich on 01.04.17.
// */
//
//public class BaseFragment extends Fragment {
//
//    private FragmentComponent fragmentComponent;
//
//    public FragmentComponent getFragmentComponent() {
//        if (fragmentComponent == null) {
//            BaseActivity activity = (BaseActivity) getActivity();
//            ActivityComponent activityComponent = activity.getActivityComponent();
//            fragmentComponent = activityComponent.plus(new FragmentModule(activity));
//        }
//        return fragmentComponent;
//    }
//
//    protected void showProgress(boolean show) {
//        activity().showProgress(show);
//    }
//
//    protected MainActivity activity() {
//        return (MainActivity) getActivity();
//    }
//}
