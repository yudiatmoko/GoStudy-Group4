package com.group4.gostudy.presentation.detail

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import android.view.OrientationEventListener
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.group4.gostudy.R
import com.group4.gostudy.databinding.ActivityDetailCourseBinding
import com.group4.gostudy.model.Course
import com.group4.gostudy.model.UserCourse
import com.group4.gostudy.presentation.detail.adapter.AdapterViewPager
import com.group4.gostudy.presentation.detail.material.dialog.DialogOrderFragment
import com.group4.gostudy.presentation.main.MainViewModel
import com.group4.gostudy.utils.formatDurationToMinutes
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailCourseActivity : AppCompatActivity() {
    private val binding: ActivityDetailCourseBinding by lazy {
        ActivityDetailCourseBinding.inflate(layoutInflater)
    }
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: AdapterViewPager

    private var youtubePlayer: YouTubePlayer? = null

    private var isFullScreen = false

    private val windowInsetsController: WindowInsetsControllerCompat by lazy {
        WindowCompat.getInsetsController(window, window.decorView)
    }

    private var previousOrientation: Int = -1

    private val viewModel: DetailViewModel by viewModel { parametersOf(intent?.extras) }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showData(viewModel.course, viewModel.userCourse)
        setCoursePremiumAndFree(viewModel.course, viewModel.userCourse)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        initYoutube()
        val orientationEventListener = object : OrientationEventListener(this) {
            override fun onOrientationChanged(orientation: Int) {
                val newOrientation = when (orientation) {
                    in 0..44 -> 0
                    in 45..134 -> 1
                    in 135..224 -> 2
                    in 225..314 -> 3
                    in 315..359 -> 0
                    else -> ORIENTATION_UNKNOWN
                }
                if (newOrientation != previousOrientation && !isFullScreen) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
                }
                previousOrientation = newOrientation
            }
        }
        val autoRotationOn = Settings.System.getInt(
            contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0
        ) == 1
        if (autoRotationOn) {
            orientationEventListener.enable()
        } else {
            orientationEventListener.disable()
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewpager2)

        adapter = AdapterViewPager(supportFragmentManager, lifecycle)

        tabLayout.addTab((tabLayout.newTab().setText("Tentang")))
        tabLayout.addTab((tabLayout.newTab().setText("Materi")))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    private fun setCoursePremiumAndFree(course: Course?, userCourse: UserCourse?) {
        val isPremiumCourse = course?.type
        val isAccessibleCourse: Boolean = userCourse?.isAccessible ?: false
        lifecycleScope.launch {
            val userToken = mainViewModel.getUserToken()
            if (isPremiumCourse == "Premium" && !isAccessibleCourse && userToken?.isNotEmpty() == true) {
                binding.btnBuy.setOnClickListener {
                    showDialogOrder()
                }
                binding.clSectionOrder.isVisible = true
                binding.tabLayout.isVisible = false
                binding.viewpager2.isUserInputEnabled = false
            } else if (isPremiumCourse == "Premium" && isAccessibleCourse && userToken?.isNotEmpty() == true) {
                binding.clSectionOrder.isVisible = false
                binding.tabLayout.isVisible = true
                binding.viewpager2.isUserInputEnabled = true
            } else if (isPremiumCourse == "Premium" && isAccessibleCourse && userToken?.isEmpty() == true) {
                binding.clSectionOrder.isVisible = false
            } else if (isPremiumCourse == "Premium" && !isAccessibleCourse && userToken?.isEmpty() == true) {
                binding.clSectionOrder.isVisible = false
            } else {
                binding.clSectionOrder.isVisible = false
                binding.tabLayout.isVisible = true
                binding.viewpager2.isUserInputEnabled = true
            }
        }
    }

    private fun showDialogOrder() {
        DialogOrderFragment().show(supportFragmentManager, "DialogOrderFragment")
    }

    private fun initYoutube() {
        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1) // enable full screen button
            .build()
        binding.youtubePlayerView.apply {
            enableAutomaticInitialization = false
            addFullscreenListener(object : FullscreenListener {
                override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                    enterFullScreen(fullscreenView)
                }

                override fun onExitFullscreen() {
                    exitFullscreen()
                }
            })
            initialize(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        this@DetailCourseActivity.youtubePlayer = youTubePlayer
                        observeDataVideo()
                    }
                },
                iFramePlayerOptions
            )
        }
        lifecycle.addObserver(binding.youtubePlayerView)
    }

    private fun observeDataVideo() {
        viewModel.videoId.observe(this) { result ->
            playVideo(result)
        }
    }

    private fun playVideo(videoUrl: String?) {
        if (!videoUrl.isNullOrBlank()) {
            youtubePlayer?.loadVideo(videoUrl, 0f)
        }
    }

    private fun exitFullscreen() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
        isFullScreen = false
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        binding.clCourse.isVisible = true
        binding.youtubePlayerView.isVisible = true
        binding.clSectionOrder.isVisible = false
        binding.flFullscreen.apply {
            isVisible = false
            removeAllViews()
        }
    }

    private fun enterFullScreen(view: View) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        isFullScreen = true
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        binding.clCourse.isVisible = false
        binding.youtubePlayerView.isVisible = false
        binding.clSectionOrder.isVisible = false
        binding.flFullscreen.apply {
            isVisible = true
            addView(view)
        }
    }

    fun loadVideoUrl(videoId: String) {
        youtubePlayer?.loadVideo(videoId, 0f)
    }

    private fun showData(course: Course?, userCourse: UserCourse?) {
        course?.let {
            binding.tvClassName.text = it.category?.name
            binding.tvLevel.text = it.level
            binding.tvDuration.text = it.totalDuration?.formatDurationToMinutes().plus(" Menit")
            binding.tvModule.text = String.format("%.0f Modul", it.totalModule?.toDouble())
            binding.tvClassTitle.text = it.name
            binding.tvRating.text = it.rating.toString()
            binding.txtMentorName.text = String.format("by %s", it.courseBy)
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        const val EXTRA_USER_COURSE = "EXTRA_USER_COURSE"
        fun startActivity(context: Context, course: Course?) {
            val intent = Intent(context, DetailCourseActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, course)
            context.startActivity(intent)
        }
        fun startActivityUserCourse(context: Context, userCourse: UserCourse?, course: Course?) {
            val intent = Intent(context, DetailCourseActivity::class.java)
            intent.putExtra(EXTRA_USER_COURSE, userCourse)
            intent.putExtra(EXTRA_PRODUCT, course)
            context.startActivity(intent)
        }
    }
}
