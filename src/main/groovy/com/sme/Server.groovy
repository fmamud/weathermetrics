package com.sme

import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET
import static spark.Spark.exception
import static spark.Spark.get

def http = new HTTPBuilder('http://api.openweathermap.org/data/2.5/')

get("/getData", { req, res ->
    res.type('application/json')

    def city = req.queryParams('city')
    def appid = req.queryParams('appid')

    if (!city || !appid) throw new IllegalArgumentException('city and appid parameters are required')

    http.request(GET, JSON) { httpRequest ->
        uri.path = 'forecast/daily'
        uri.query = [q: city, appid: appid]

        response.success = { resp, json ->
            def daily = json.list.temp.day
            def nightly = json.list.temp.night
            def pressure = json.list.pressure

            ['averages': [
                    'daily'   : avg(daily),
                    'nightly' : avg(nightly),
                    'pressure': avg(pressure)
            ]]
        }
        response.failure = { resp, json ->
            res.status(resp.status)
            json
        }
    }

}, new JsonTransformer())


exception(IllegalArgumentException) { e, req, res ->
    res.type('application/json')
    res.status(400)
    res.body("""{"error": "${e.message}"}""")
}

def avg(list) {
    list.sum() / list.size()
}