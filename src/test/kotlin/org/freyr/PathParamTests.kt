package org.freyr

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class PathParamTests {

    @Test
    fun testKotlinMainJavax() {
        given()
            .`when`().get("/kotlin-main-src-javax/check/main-javax")
            .then()
            .statusCode(200)
            .body(`is`("Check [kotlin-main-javax] main-javax"))
    }

    @Test
    fun testKotlinTestJavax() {
        given()
            .`when`().get("/kotlin-test-src-javax/check/test-javax")
            .then()
            .statusCode(200)
            .body(`is`("Check [kotlin-test-javax] test-javax"))
    }

    @Test
    fun testKotlinMainJboss() {
        given()
            .`when`().get("/kotlin-main-src-jboss/check/main-jboss")
            .then()
            .statusCode(200)
            .body(`is`("Check [kotlin-main-jboss] main-jboss"))
    }

    @Test
    fun testKotlinTestJboss() {
        given()
            .`when`().get("/kotlin-test-src-jboss/check/test-jboss")
            .then()
            .statusCode(200)
            .body(`is`("Check [kotlin-test-jboss] test-jboss"))
    }




    @Test
    fun testJavaMainJavax() {
        given()
            .`when`().get("/java-main-src-javax/check/main-javax")
            .then()
            .statusCode(200)
            .body(`is`("Check [java-main-javax] main-javax"))
    }

    @Test
    fun testJavaTestJavax() {
        given()
            .`when`().get("/java-test-src-javax/check/test-javax")
            .then()
            .statusCode(200)
            .body(`is`("Check [java-test-javax] test-javax"))
    }

    @Test
    fun testJavaMainJboss() {
        given()
            .`when`().get("/java-main-src-jboss/check/main-jboss")
            .then()
            .statusCode(200)
            .body(`is`("Check [java-main-jboss] main-jboss"))
    }

    @Test
    fun testJavaTestJboss() {
        given()
            .`when`().get("/java-test-src-jboss/check/test-jboss")
            .then()
            .statusCode(200)
            .body(`is`("Check [java-test-jboss] test-jboss"))
    }

}