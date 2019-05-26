package vip.mmoney.tochatdanong

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import vip.mmoney.tochatdanong.base.BaseActivity
import vip.mmoney.tochatdanong.main.MainActivity
import vip.mmoney.tochatdanong.utilities.InterstitialUtils

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_splash_screen)

        InterstitialUtils
            .getShareInstance() //Khởi tạo InterstitialUtils
            .initInterstitialAd(applicationContext) //Khởi tạo InterstitialAd bên trong InterstitialUtils và chỉ tạo 1 lần để sử dụng trong suốt vòng đời của app

        changeActivity()
    }

    private fun changeActivity() {
        Thread {
            try {
                Thread.sleep(1500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            runOnUiThread { onResourcesLoaded() }

        }.start()
    }

    private fun onResourcesLoaded() {
        //Gọi hàm showInterstitialAd, nếu quảng cáo Full Screen đã được tải thì sẽ hiển thị cho user,
        //Nếu quảng cáo FUll Screen chưa tải xong thì gọi hàm onAdClosed để chuyển màn hình ngay, không để user chờ load QC
        InterstitialUtils.getShareInstance().showInterstitialAd {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
