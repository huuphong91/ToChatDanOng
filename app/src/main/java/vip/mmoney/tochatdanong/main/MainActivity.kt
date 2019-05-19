package vip.mmoney.tochatdanong.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import vip.mmoney.tochatdanong.ChapterContentActivity
import vip.mmoney.tochatdanong.R
import vip.mmoney.tochatdanong.base.BaseActivity
import vip.mmoney.tochatdanong.model.Chapter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    override fun showChapters(chapters: List<Chapter>) {
        val adapter = ArrayAdapter<Chapter>(this, android.R.layout.simple_list_item_1, chapters)
        lvMucLuc.adapter = adapter

        lvMucLuc.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@MainActivity, ChapterContentActivity::class.java)
            intent.putExtra("linkChuong", chapters[position].url)
            intent.putExtra("tenChuong", chapters[position].title)
            startActivity(intent) }
    }
}
