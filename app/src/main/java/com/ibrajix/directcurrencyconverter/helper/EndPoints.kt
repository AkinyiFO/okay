package com.ibrajix.directcurrencyconverter.helper

import com.ibrajix.directcurrencyconverter.BuildConfig
import com.ibrajix.directcurrencyconverter.helper.EndPoints.Companion.API_KEY
import com.ibrajix.directcurrencyconverter.helper.EndPoints.Companion.API_TOKEN

/**
 * All URL listed in this class
 */

class EndPoints {

    companion object {

        //Base URL

        const val BASE_URL = "https://api.getgeoapi.com/api/v2/currency/"

        //API TOKEN
        const val API_TOKEN = "ca5ed3416099096d1229c09e6c3a7614eda7af9c"
        //API KEY
        const val API_KEY = API_TOKEN

        //COVERT URL
        const val  CONVERT_URL = "convert"

    }

}