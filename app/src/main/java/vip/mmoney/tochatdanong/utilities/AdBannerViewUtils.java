package vip.mmoney.tochatdanong.utilities;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static vip.mmoney.tochatdanong.utilities.ConstantsKt.APP_ID;

public class AdBannerViewUtils {

    private static AdBannerViewUtils shareInstance;

    public static AdBannerViewUtils getShareInstance() {
        if (shareInstance == null) {
            shareInstance = new AdBannerViewUtils();
        }
        return shareInstance;
    }

    public void initAdView(Context context, AdView mAdView) {

        MobileAds.initialize(context, APP_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
