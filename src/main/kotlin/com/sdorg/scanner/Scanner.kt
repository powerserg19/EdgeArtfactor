package com.sdorg.scanner

import io.micronaut.http.HttpRequest.*
import io.micronaut.http.client.*
import io.micronaut.http.client.annotation.Client
import io.micronaut.rxjava3.http.client.Rx3HttpClient
import io.reactivex.rxjava3.*
import jakarta.inject.Inject
import picocli.CommandLine.*
import java.util.*



class Scanner:Runnable  {

    @field:Client("https://api.github.com")
    @Inject
    var client: Rx3HttpClient? = null

    @Option(names = ["-v", "--verbose"], description = ["Shows some project details"])
    var verbose = false

    @Parameters(description = ["One or more GitHub slugs (comma separated) to show stargazers for.", "  Default: \${DEFAULT-VALUE}"], split = ",", paramLabel = "<owner/repo>")
    var githubSlugs: List<String> = mutableListOf("micronaut-projects/micronaut-core", "remkop/picocli")
    override fun run() {
        System.out.printf("*************")
        for (slug in githubSlugs) {

            val m: Map<*, *> = client!!.retrieve(
                    GET<String>("/repos/$slug").header("User-Agent", "remkop-picocli"),
                    MutableMap::class.java).blockingFirst()
            System.out.printf("%s has %s stars%n", slug, m["watchers"])
            if (verbose) {
                println("3333")
                val msg = "Description: %s%nLicense: %s%nForks: %s%nOpen issues: %s%n%n"
                System.out.printf(msg, m["description"],
                        (m["license"] as Map<*, *>?)!!["name"],
                        m["forks"], m["open_issues"])
            }
        }
    }
}