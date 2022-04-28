package com.example.enterpriseExam.integrationtests

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class FullSystemTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DirtiesContext
    fun userShouldGetAllUsersIntegrationTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.get("/api/user"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
            .andExpect { jsonPath("$") { isArray() } }
    }

    @Test
    @DirtiesContext
    fun userShouldGetForbiddenWhenGettingAllAuthenticationTest() {
        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.get("/api/authentication/all"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isForbidden() } }
    }

    @Test
    @DirtiesContext
    fun userShouldGetForbiddenWhenAddingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.post("/api/shelter/edit/newanimal"){
            contentType = APPLICATION_JSON
            content = "{\"name\":\"hans\" , " +
                    "\"animalType\":\"dog\" ," +
                    "\"breed\":\"golden retriever\" ," +
                    "\"age\": 7 ," +
                    "\"health\":\"good boy\"}"
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isForbidden() } }
    }

    @Test
    @DirtiesContext
    fun userShouldGetForbiddenWhenDeletingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.delete("/api/shelter/edit/1"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isForbidden() } }

    }

    @Test
    @DirtiesContext
    fun userShouldGetForbiddenWhenUpdatingAuthorityTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.put("/api/user/edituser/1"){
            contentType = APPLICATION_JSON
            content = "{\"authorityName\":\"ADMIN\"}"
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isForbidden() } }
    }

    @Test
    @DirtiesContext
    fun userShouldGetForbiddenWhenUpdatingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.put("/api/shelter/edit/1"){
            contentType = APPLICATION_JSON
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isForbidden() } }
    }


    @Test
    @DirtiesContext
    fun userShouldGetOkWhenGetOneAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.get("/api/shelter/2"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
    }

    @Test
    @DirtiesContext
    fun userShouldGetOkWhenGetAllAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"user@user.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.get("/api/shelter"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
    }

    @Test
    @DirtiesContext
    fun adminShouldGetOkWhenGettingAllAuthenticationTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"admin@admin.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.get("/api/authentication/all"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
    }

    @Test
    @DirtiesContext
    fun adminShouldGetOkWhenAddingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"admin@admin.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.post("/api/shelter/edit/newanimal"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "\"name\":\"fanta\",\n" +
                    "\"animalType\":\"bunny\",\n" +
                    "\"breed\":\"white chocolate\",\n" +
                    "\"age\": 3,\n" +
                    "\"health\":\"ok\"\n" +
                    "}"
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    @DirtiesContext
    fun adminShouldGetOkWhenDeletingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"admin@admin.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.delete("/api/shelter/edit/1"){
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
    }

    @Test
    @DirtiesContext
    fun adminShouldGetOkWhenUpdatingAnimalTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"admin@admin.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.put("/api/shelter/edit/2"){
            contentType = APPLICATION_JSON
            content = "{\"name\":\"smiley\", " +
                    "\"animalType\":\"donkey\"," +
                    "\"breed\":\"japanese muhmuh\"," +
                    "\"age\": 1 ," +
                    "\"health\":\"great\"}"
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }

    @Test
    @DirtiesContext
    fun adminShouldGetOkWhenUpdatingAuthorityTest() {

        val loggedInUser = mockMvc.post("/api/login"){
            contentType = APPLICATION_JSON
            content = "{\n" +
                    "    \"email\": \"admin@admin.com\",\n" +
                    "    \"password\": \"pirate\"\n" +
                    "}"
        }
            .andExpect { status { isOk() } }
            .andReturn()

        val theCookie = loggedInUser.response.getCookie("access_token")

        mockMvc.put("/api/user/edituser/2"){
            contentType = APPLICATION_JSON
            content = "{\"authorityName\":\"ADMIN\"}"
            theCookie?.let { cookie(it) }
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(APPLICATION_JSON) } }
    }
}