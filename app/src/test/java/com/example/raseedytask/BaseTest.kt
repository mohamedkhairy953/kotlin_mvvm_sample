package com.example.raseedytask


import com.example.raseedytask.BuildConfig.BASE_URL
import com.example.raseedytask.di.module.*
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.Before
import org.testng.annotations.AfterTest

abstract class BaseTest {

   lateinit var testAppComponent: TestAppComponent
    lateinit var mockServer: MockWebServer

    @Before
    open fun setUp(){
        this.configureMockServer()
        this.configureDi()
    }

    @AfterTest
    open fun tearDown(){
        this.stopMockServer()
    }

    // CONFIGURATION
    open fun configureDi(){
        this.testAppComponent = DaggerTestAppComponent.builder()
            .netModule(NetModule(if (isMockServerEnabled()) mockServer.url("/").toString() else BASE_URL))
            .repoModule(RepoModule())
            .apiModule(ApiModule())
            .daoModule(DaoModule())
            .build()
        this.testAppComponent.inject(this)
    }

    // MOCK SERVER
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    open fun configureMockServer(){
        if (isMockServerEnabled()){
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()){
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(responseCode: Int) = mockServer.enqueue(MockResponse()
        .setResponseCode(responseCode)
        .setBody(getJson()))

    private fun getJson() : String {
       return  "[\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.39.14_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"اطلب #010* من فودافون و اكسب اضعاف الشحنة\",\n" +
               "    \"url\": \"https://www.facebook.com/Vodafone.Egypt/videos/382370432387725/?__xts__[0]=68.ARDHTsCjdAlwO_FYRqbtiWx_Diky1VCjMhwWcFckSuJYu-zvB95gXlljeFHpZ149oxGlDLElMx6zvi7Ot6sJgSjwxdMHRSbPdmn_XLGt3bvb6KkthF7uVQup5_iB0lg5ZASN8Y1Zbtb5TcBHXXi8Qgc3Km3eLxEw2PkP8XPZv-IY5YHmgIMkg9npMQxW_VsUo9JZwWrob99AkqZDYxZMsm0HZbiSjxSEctbrvXYxWC5at5qLRFxRa01ItQDb0CTHhKKe4lT0K7VvgI7yWVJQHcqbD7FlMaJeH8jzcha7IH7O_ajFM1imCHR2mYYQWLsTzVXlem6G9HuZFP-czC00_Mw8sUsIePcbLN4gcoHdB1QKjvLpPKhoNk0PFsMbC8t0otA6P9iF8TGPv6onpwieEvjMJhrcCov6rDlWjA&__tn__=-R\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 1,\n" +
               "    \"page\": \"\"\n" +
               "  },\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.38.22_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"اطلب #012# من اورانج و اكسب 100 ضعف شحنتك\",\n" +
               "    \"url\": \"https://www.facebook.com/OrangeEgyptOfficial/videos/915159018826013/?__xts__[0]=68.ARBJlni3vWrrVu--WpmgDCsTAlqgsdtHFk3YrIAeVsR_0ROJRYZuEjSrJtdM_bwumFILeCAv9CgH987GRob5uQI1yZ6lC9LMQh5zsVtOyh07QAuHAtRmmrr2C-KM-iAIKaK-VQz565PCTsUpNQI9GkUSmh8sCwqF6MRWebUY8TUcQF7uxOZHHWMsfNREh1s3TKbXy5V59f3g90fvzuGNPpbovsRe81F8u1tw4uivo25n2aOV_hXy8ZjTdTLGJt9rlkk_2Y6V46fLRHGmxIs7qzhjqjw4nNVuhN1ecrtu8PrzjDbndnCHu0yY_eSaKnxXjI7G1h_kq_mr2nVCwaqWJXrHK1hPNag0E7dnyiTWgzvS7-6dUwd9R2zu80VJ1zyA6A9SdzKKcyMwUujnhlRf2juPxX3yncMv7Uvk74UimaECUl2_r3L7C3rcG1k6TY5eq_FWD0T_zZwqmygsqAyDkIv9nfm0BB8083OCSivmks4dlts7gBJ6hnh0Ek7oiwjtVs37iwNnZha30EYiJD-sq0xO6wdS_U5S-yNfxm88c0ZFXzCJxRq68siyDDnORG22vrxH5_rAEVWHf9zwEtBafLVxfIoQwhni-qR5Ng&__tn__=-R\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 2,\n" +
               "    \"page\": \"\"\n" +
               "  },\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.38.54_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"40 دقيقة سوشيال أو 60د فيديو كل يوم لمده أسبوع ب7ج #3*566*\",\n" +
               "    \"url\": \"https://www.facebook.com/etisalatmisr/videos/344721236428443/?__xts__[0]=68.ARDBHlwvqMRRYwcqR4XkRfmCt3JL5jA2QAr8q0cZ9SYFX6OEDJ3fq1Oyk-cPAVXpFdhspNrl3UYbqBwEPzgOQnWG1QkVBDfECnHX_PkIx2ZEfUpuvNvRf6oFDPL5VUde4GP7prbEY9NdF3BObLHTK57qPvJ8Y-tLPvK2VE5Q5jrxY3Gbz51gvvMPjH5NsQygkhAruhByhiLZfmcFhrDBqBInVVPJV_5cFOLy6en_tUa_7DMsVyF2YEmsxujZfvGGuBaZfZEoLGPjrDiKMbjGcQ1PcWqn2hFb2OVuPpZreG5CuOLNMlfPK4sYPxUrp2iBhui5ZAmIMPUgt4pfJpj3rN9IbaCqGA&__tn__=-R\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 3,\n" +
               "    \"page\": \"\"\n" +
               "  },\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.52.08_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"باقات كونكت X ب 5 جنيه بس في الشهر\",\n" +
               "    \"url\": \"https://www.facebook.com/etisalatmisr/photos/a.149674009666/10156931455129667/?type=3&theater\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 3,\n" +
               "    \"page\": \"\"\n" +
               "  },\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.55.11_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"احصل علي 2GB انترنت هدية من اورنچ صالحة لمدة اسبوع لما تغير شريحتك للـ 4G\",\n" +
               "    \"url\": \"https://www.facebook.com/OrangeEgyptOfficial/photos/a.190458140969490/2811006715581273/?type=3&theater\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 5,\n" +
               "    \"page\": \"\"\n" +
               "  },\n" +
               "  {\n" +
               "    \"picture\": \"https://gazef.s3.amazonaws.com/simswitch_app/media/uploads/Screen_Shot_2019-06-17_at_3.58.25_PM.png\",\n" +
               "    \"action\": \"url\",\n" +
               "    \"title\": \"(2000 ميجا نت + 400 دقيقة) بـ 25 جنيه اطلب #25*660*\",\n" +
               "    \"url\": \"https://www.facebook.com/TelecomEgypt/videos/1255247984630038/?__xts__[0]=68.ARDJ23ueJb1NsaTeikVsx6w4POHhCjt_NU6rJHeuXlZmGHvROJjWwf59mqkRrupZQCdEOGAyxneKdMWgb489Ms027kOoIG6pyfrRQPaLdettfEUkiraszG4IjjsDXZ3GgC3A1D7UHsiLZm2iT_ddHxs64K_7Qk9gNqBDnBXh7RX_wbBAGdroKo76YYKbeal_VJfBHejRpodZqotqFfHHhnx5bRFASTsnxj40KQGMXmjO4RpdAdaqtjyzXL_XmVOU-uJK228rr3P3zuo2yl7nCy_DWJOxOnzdarhCAXOo-gnsCB4pGQHMgdngnwUs5L_VCnoZC9Wd9k3XzGG_gO3j_o8zFPPqD6Fada0&__tn__=-R\",\n" +
               "    \"solo\": false,\n" +
               "    \"best_offer\": true,\n" +
               "    \"sucess\": true,\n" +
               "    \"order\": 4,\n" +
               "    \"page\": \"\"\n" +
               "  }\n" +
               "]"
    }
}