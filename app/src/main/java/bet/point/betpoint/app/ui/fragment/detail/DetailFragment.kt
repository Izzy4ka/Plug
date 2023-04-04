package bet.point.betpoint.app.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import bet.point.betpoint.app.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import bet.point.betpoint.app.entity.news.entity.NewsEntity
import bet.point.betpoint.app.entity.news.service.NewsService

private const val ARG_PARAM_ID = "param_id"

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    private val service = NewsService

    private val newsEntity: NewsEntity by lazy {
        service.getNew(getIdNews())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBtn()
    }

    private fun initBtn() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .remove(this@DetailFragment).commit()
                }
            })
    }

    private fun initView() {
        with(requireBinding()) {
            txtTitle.text = newsEntity.title
            txtDescription.text = newsEntity.description
            Glide.with(imageNews).load(newsEntity.image)
                .into(imageNews)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun requireBinding(): FragmentDetailBinding = checkNotNull(binding)

    private fun getIdNews(): Int = requireArguments().getInt(ARG_PARAM_ID)
    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM_ID, id)
                }
            }
    }
}