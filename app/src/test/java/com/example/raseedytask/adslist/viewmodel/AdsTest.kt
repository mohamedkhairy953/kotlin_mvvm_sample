package com.example.raseedytask.adslist.viewmodel

import android.app.Activity
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.raseedytask.BaseTest
import com.example.raseedytask.adslist.model.AdsModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY
import java.net.HttpURLConnection.HTTP_OK


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UserUnitTest : BaseTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    // FOR DATA
    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: AdsViewModel
    private val EXPECTED_First = AdsModel(
        "https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.39.14_PM.png",
        "اطلب #010* من فودافون و اكسب اضعاف الشحنة",
        "https://www.facebook.com/Vodafone.Egypt/videos/382370432387725/?__xts__[0]=68.ARDHTsCjdAlwO_FYRqbtiWx_Diky1VCjMhwWcFckSuJYu-zvB95gXlljeFHpZ149oxGlDLElMx6zvi7Ot6sJgSjwxdMHRSbPdmn_XLGt3bvb6KkthF7uVQup5_iB0lg5ZASN8Y1Zbtb5TcBHXXi8Qgc3Km3eLxEw2PkP8XPZv-IY5YHmgIMkg9npMQxW_VsUo9JZwWrob99AkqZDYxZMsm0HZbiSjxSEctbrvXYxWC5at5qLRFxRa01ItQDb0CTHhKKe4lT0K7VvgI7yWVJQHcqbD7FlMaJeH8jzcha7IH7O_ajFM1imCHR2mYYQWLsTzVXlem6G9HuZFP-czC00_Mw8sUsIePcbLN4gcoHdB1QKjvLpPKhoNk0PFsMbC8t0otA6P9iF8TGPv6onpwieEvjMJhrcCov6rDlWjA&__tn__=-R",
        1
        )


    // OVERRIDING
    override fun isMockServerEnabled(): Boolean = true

    @Before
    override fun setUp() {
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel =ViewModelProviders.of(this.activity).get(AdsViewModel::class.java)
    }

    // TESTS
    @Test
    fun getUser_whenSuccess() {
        // Prepare data
        this.mockHttpResponse(HTTP_OK)
        // Pre-test
        assertEquals(null, this.viewModel.adsResponse.value, null)
        // Execute View Model
        this.viewModel.getAds()
        // Checks
        assertEquals(this.viewModel.adsResponse.value?.get(0), EXPECTED_First)
        assertEquals(this.viewModel.dataLoading.value, false)
        assertEquals(this.viewModel.showNoNetworkScreenEvent.value, false)
    }

    @Test
    fun getUser_whenError() {
        // Prepare data
        this.mockHttpResponse(HTTP_BAD_GATEWAY)
        // Pre-test
        assertEquals(null, this.viewModel.adsResponse.value, null)
        // Execute View Model
        this.viewModel.getAds()
        // Checks
        assertEquals(this.viewModel.adsResponse.value, null)
        assertEquals(this.viewModel.dataLoading.value, false)
        assertEquals(this.viewModel.errorMessageEvent.value?.isNotEmpty(), true)
    }
}