package vip.mmoney.tochatdanong


import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_chapter_content.*
import vip.mmoney.tochatdanong.base.BaseActivity
import vip.mmoney.tochatdanong.utilities.AdBannerViewUtils

class ChapterContentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_content)

        AdBannerViewUtils.getShareInstance().initAdView(applicationContext, adViewContent)

        wvChapterContent.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val extrasData = intent.extras

        val sNoiDungChuong = extrasData?.getString("linkChuong", "")

        val sTieuDe = extrasData?.getString("tenChuong", "")

        toolBarNoiDung.title = sTieuDe
        setSupportActionBar(toolBarNoiDung)

        wvChapterContent.loadUrl("file:///android_asset/$sNoiDungChuong")

        wvChapterContent.settings.builtInZoomControls = true
    }
}
