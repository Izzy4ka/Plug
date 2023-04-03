package bet.point.betpoint.app.ui.activity.plug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bet.point.betpoint.app.R
import bet.point.betpoint.app.databinding.ActivityPlugBinding
import bet.point.betpoint.app.entity.news.service.NewsService
import bet.point.betpoint.app.ui.activity.plug.adapter.NewsAdapter
import bet.point.betpoint.app.ui.fragment.detail.DetailFragment

class PlugActivity : AppCompatActivity(), NewsAdapter.DetailInfo {
    private val newsService = NewsService

    private var binding: ActivityPlugBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlugBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initAdapter() {
        val adapter = NewsAdapter(newsService.getNews(), this)
        binding?.rvNews?.adapter = adapter
    }

    override fun openDetail(id: Int) {
        val fragment = DetailFragment.newInstance(id)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
            .commit()
    }
}