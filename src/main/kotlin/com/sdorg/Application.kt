package com.sdorg

import com.sdorg.scanner.Scanner
import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.runtime.Micronaut.run
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(name = "git-star", header = [
    "@|green       _ _      _             |@",
    "@|green  __ _(_) |_ __| |_ __ _ _ _  |@",
    "@|green / _` | |  _(_-<  _/ _` | '_| |@",
    "@|green \\__, |_|\\__/__/\\__\\__,_|_|   |@",
    "@|green |___/                        |@"],
        description = ["Shows GitHub stars for a project"],
        mixinStandardHelpOptions = true, version = ["git-star 0.1"])

fun main(args: Array<String>) {
    PicocliRunner.execute(Scanner::class.java, *args);
}

