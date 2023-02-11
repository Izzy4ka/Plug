package com.example.plug.ui.activity.plug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plug.databinding.ActivityPlugBinding
import com.example.plug.news.service.NewsService
import com.example.plug.ui.activity.plug.adapter.NewsAdapter

class PlugActivity : AppCompatActivity() {

    private val newsService = NewsService.getInstance()

    private val binding: ActivityPlugBinding by lazy {
        ActivityPlugBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = NewsAdapter(newsService.getNews())
        binding.rvNews.adapter = adapter
    }
}