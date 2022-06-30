package com.lfcoding.rotaryclock

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.doOnLayout
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.lfcoding.rotaryclock.adapters.YearAdapter
import com.lfcoding.rotaryclock.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val TAG = "Slava Ukraini!"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mVelocityTracker: VelocityTracker? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //In order to get child views it needs to be a binding, not the view?
        val minutesDisc = binding.clockInclude.minutesInclude.root
        val hoursDisc = binding.clockInclude.hoursInclude.root
        val daysDisc = binding.calendarInclude.daysInclude.root
        val monthsDisc = binding.calendarInclude.monthsInclude.root

        setUpRecyclers()

        staticRotation()

        //TODO leftoff here
        val gestureDetector = GestureDetectorCompat(this, gestureListener)
        minutesDisc.doOnLayout{ setupDynamicAnimations() }

        binding.clockInclude.minutesInclude.root.setOnTouchListener(
            View.OnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        mVelocityTracker?.clear()
                        mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                        mVelocityTracker?.addMovement(event)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        Log.d("TAG", "TOUCH EVENT!")
                        mVelocityTracker?.apply {
                            val pointerId: Int = event.getPointerId(event.actionIndex)
                            addMovement(event)
                            computeCurrentVelocity(1000)
                            Log.d("", "X velocity: ${getXVelocity(pointerId)}")
                            Log.d("", "Y velocity: ${getYVelocity(pointerId)}")
                            //TODO get a vector, start position, relation to center and possible rotation

                            val fling = FlingAnimation(view, DynamicAnimation.ROTATION)

                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        mVelocityTracker?.recycle()
                        mVelocityTracker = null
                    }
                }

                return@OnTouchListener true
            }
        )

    }

    private fun setupDynamicAnimations(){
        //TODO
    }

    private fun setUpRecyclers() {

        binding.calendarInclude.yearsInclude.recyclerView1.apply {
            adapter = YearAdapter()
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            scrollToPosition((Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % 10 + 1)
            smoothScrollBy(0, 1)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(manager)
                        val pos: Int = manager.getPosition(centerView!!) % 10
                        Log.d("TAG", "Snapped Item Position: $pos")
                    }
                }
            })

        }

        binding.calendarInclude.yearsInclude.recyclerView2.apply {
            adapter = YearAdapter()
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            scrollToPosition((Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % 10 - 1)
            smoothScrollBy(0, 1)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(manager)
                        val pos: Int = manager.getPosition(centerView!!) % 10
                        Log.d("TAG", "Snapped Item Position: $pos")
                    }
                }
            })
        }

        binding.calendarInclude.yearsInclude.recyclerView3.apply {
            adapter = YearAdapter()
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            scrollToPosition((Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % 10 + 1)
            smoothScrollBy(0, 1)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(manager)
                        val pos: Int = manager.getPosition(centerView!!) % 10
                        Log.d("TAG", "Snapped Item Position: $pos")
                    }
                }
            })
        }

        binding.calendarInclude.yearsInclude.recyclerView4.apply {
            adapter = YearAdapter()
            val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
            scrollToPosition((Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % 10 + 1)
            smoothScrollBy(0, 1)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val centerView = snapHelper.findSnapView(manager)
                        val pos: Int = manager.getPosition(centerView!!) % 10
                        Log.d("TAG", "Snapped Item Position: $pos")
                    }
                }
            })
        }

    }

    private fun staticRotation(){

        lifecycleScope.launch{
            for(x in 0..1000 step 6){
                binding.clockInclude.hoursInclude.root.rotation += 6
                //Rotation of tv12 not changing. Behaves differently when parent rotates than when layer rotates?
                binding.clockInclude.hoursInclude.tv12.rotation = 0f
                delay(100)
            }
            for(x in 360 downTo 0 step 6){
                binding.calendarInclude.monthsInclude.root.rotation = x.toFloat()
                delay(100)
            }
        }
    }
}