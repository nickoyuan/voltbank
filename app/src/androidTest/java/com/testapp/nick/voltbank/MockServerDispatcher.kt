package com.testapp.nick.voltbank

import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.RecordedRequest



class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path == "api/data") {
                return MockResponse().setResponseCode(200).setBody("{data:FakeData}")
            } else if (request.path == "api/codes") {
                return MockResponse().setResponseCode(200).setBody("{codes:FakeCode}")
            } else if (request.path == "api/number")
                return MockResponse().setResponseCode(200).setBody("number:FakeNumber")

            return MockResponse().setResponseCode(404)
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }
}