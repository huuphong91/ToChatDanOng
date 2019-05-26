package vip.mmoney.tochatdanong.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import vip.mmoney.tochatdanong.AboutActivity
import vip.mmoney.tochatdanong.ChapterContentActivity
import vip.mmoney.tochatdanong.R
import vip.mmoney.tochatdanong.base.BaseActivity
import vip.mmoney.tochatdanong.model.Chapter
import vip.mmoney.tochatdanong.utilities.AdBannerViewUtils
import vip.mmoney.tochatdanong.utilities.InterstitialUtils
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AdBannerViewUtils.getShareInstance().initAdView(applicationContext, adViewMain)

        toolBar.title = resources.getString(R.string.main_title_bar)
        setSupportActionBar(toolBar)

        mPresenter.takeView(this)
        mPresenter.loadChapters()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mn_share_item -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.noi_dung_chia_se))
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.chia_se)))
            }
            R.id.mn_info -> {
                val intentInfo = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentInfo)
            }
            R.id.mn_other_app_item -> {
                val uriOtherApp = Uri.parse("market://search?q=pub:MMoney.VIP")
                val intentOtherApp = Intent(Intent.ACTION_VIEW, uriOtherApp)
                try {
                    startActivity(intentOtherApp)

                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, resources.getString(R.string.loadmarket), Toast.LENGTH_LONG).show()
                }

            }
            R.id.mn_rating_item -> try {
                val uriRate = Uri.parse("market://details?id=$packageName")
                val intentRate = Intent(Intent.ACTION_VIEW, uriRate)
                startActivity(intentRate)
            } catch (ex: ActivityNotFoundException) {
                val uriRate = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                val intentRate = Intent(Intent.ACTION_VIEW, uriRate)
                startActivity(intentRate)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.to_chat_dan_ong)
            builder.setMessage(R.string.on_back_button_message)
            builder.setPositiveButton(R.string.yes) { _, _ -> moveTaskToBack(true) }
            builder.setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }
            builder.show()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun showChapters(chapters: List<Chapter>) {
        val adapter = ArrayAdapter<Chapter>(this, android.R.layout.simple_list_item_1, chapters)
        lvMucLuc.adapter = adapter

        lvMucLuc.setOnItemClickListener { _, _, position, _ ->

            InterstitialUtils.getShareInstance().showInterstitialAd {
                val intent = Intent(this@MainActivity, ChapterContentActivity::class.java)
                intent.putExtra("linkChuong", chapters[position].url)
                intent.putExtra("tenChuong", chapters[position].title)
                startActivity(intent) }
        }

    }
}
