package vip.mmoney.tochatdanong.utilities;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static vip.mmoney.tochatdanong.utilities.ConstantsKt.APP_ID;
import static vip.mmoney.tochatdanong.utilities.ConstantsKt.INTERSTITIAL_AD_ID;

public class InterstitialUtils {

    private static InterstitialUtils shareInstance;
    private InterstitialAd mInterstitialAd;
    private AdCloseListener adCloseListener;
    private boolean isReloaded = false;

    public static InterstitialUtils getShareInstance() {
        if (shareInstance == null) {
            shareInstance = new InterstitialUtils();
        }
        return shareInstance;
    }

    public void initInterstitialAd(Context context) {

        MobileAds.initialize(context, APP_ID);

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(INTERSTITIAL_AD_ID);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                }
                loadInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (!isReloaded) {
                    isReloaded = true;
                    loadInterstitialAd();
                }
            }
        });

        loadInterstitialAd();
    }

    private void loadInterstitialAd() {
        if (mInterstitialAd != null && !mInterstitialAd.isLoaded() && !mInterstitialAd.isLoading()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public void showInterstitialAd(AdCloseListener adCloseListener) {
        if (canShowInterstitialAd()) {
            isReloaded = false;
            this.adCloseListener = adCloseListener;
            mInterstitialAd.show();

        } else {
            loadInterstitialAd();
            adCloseListener.onAdClosed();
        }
    }

    private boolean canShowInterstitialAd() {
        return mInterstitialAd != null && mInterstitialAd.isLoaded();
    }

    public interface AdCloseListener {
        void onAdClosed();
    }

}

