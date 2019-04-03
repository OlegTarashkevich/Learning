//package com.prilaga.news.view.widget;
//
//import android.content.Context;
//import android.support.v7.widget.CardView;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.widget.ImageView;
//import android.widget.TextView;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import com.sonejka.news.R;
//import com.sonejka.news.mvp.model.Article;
//import com.sonejka.news.mvp.view.activity.BaseActivity;
//import com.sonejka.news.util.Logger;
//import com.sonejka.news.util.ShareUtil;
//import com.sonejka.news.util.TextUtil;
//import com.squareup.picasso.Picasso;
//
//import javax.inject.Inject;
//
///**
// * Created by Oleg Tarashkevich on 02.04.17.
// */
//
//public class ArticleCardView extends CardView {
//
//    @BindView(R.id.article_logo_view)
//    ImageView logoImageView;
//    @BindView(R.id.article_name_text_view)
//    TextView nameTextView;
//    @BindView(R.id.article_title_text_view)
//    TextView titleTextView;
//    @BindView(R.id.article_url_text_view)
//    TextView urlTextView;
//    @BindView(R.id.article_description_text_view)
//    TextView descriptionTextView;
//
//    @Inject Picasso picasso;
//    private Article.Entry mEntry;
//
//    public ArticleCardView(Context context) {
//        this(context, null);
//    }
//
//    public ArticleCardView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ArticleCardView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        LayoutInflater.from(getContext()).inflate(R.layout.cardview_article, this);
//        ButterKnife.bind(this);
//
//        if (isInEditMode()) return;
//        BaseActivity activity = (BaseActivity) getContext();
//        activity.getActivityComponent().inject(this);
//    }
//
//    public void setSource(Article.Entry entry) {
//        mEntry = entry;
//        if (mEntry == null) {
//            setVisibility(INVISIBLE);
//        } else {
//            setVisibility(VISIBLE);
//
//            nameTextView.setText(mEntry.getInfo());
//            titleTextView.setText(mEntry.getTitle());
//            urlTextView.setText(mEntry.getUrl());
//            descriptionTextView.setText(mEntry.getDescription());
//
//            try {
//                picasso.load(TextUtil.getEmptyUrl(mEntry.getUrlToImage()))
//                        .into(logoImageView);
//            } catch (Throwable e) {
//                Logger.e(e);
//            }
//        }
//    }
//
//    @OnClick(R.id.article_share_button)
//    public void onShareClick() {
//        if (mEntry != null) {
//            String message = TextUtil.string(R.string.app_name) + ":\n" +
//                    mEntry.getTitle() + "\n\n" +
//                    mEntry.getDescription() + "\n" +
//                    mEntry.getUrl();
//            ShareUtil.shareText(message, R.string.app_name);
//        }
//    }
//}
