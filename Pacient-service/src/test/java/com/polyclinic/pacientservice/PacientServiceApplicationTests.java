package com.polyclinic.pacientservice;

import com.polyclinic.pacientservice.controllers.PacientController;
import com.polyclinic.pacientservice.models.Pacient;
import com.polyclinic.pacientservice.repositories.PacientRepository;
import com.polyclinic.pacientservice.services.PacientService;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@WebMvcTest(value = PacientController.class, secure = false)
public class PacientServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacientService pacientService;
    @MockBean
    private PacientRepository pacientRepository;



    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    String dateInString1 = "21/07/1963";
    String dateInString2 = "31/08/1982";
    Date datalindjes1,datalindjes2;

    {
        try {
            datalindjes1 = sdf.parse(dateInString1);
            datalindjes1 = sdf.parse(dateInString2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    List<Pacient > pacientList = new ArrayList<>();
    Pacient pac1 = new Pacient("12j3o1j","Egzon","Berisha","Ali",'M',datalindjes1,"Drenas","Drenas","04646664","JO","JO");
    Pacient pac2 = new Pacient("zasjdd23o1j","Florian","Hoti","akwd",'M',datalindjes2,"Drenas","Drenas","04646664","JO","JO");


    @Test public void getPacientsTest() throws Exception {
        pacientList.add(pac1);
        pacientList.add(pac2);

        when(pacientService.getPacients())
                .thenReturn(pacientList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/pacients").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());

    String expected = "[{\"id\":0,\"nrPersonal\":\"12j3o1j\",\"emri\":\"Egzon\",\"mbiemri\":\"Berisha\",\"emriPrindit\":\"Ali\",\"gjinia\":\"M\",\"adresa\":\"Drenas\",\"vendlindja\":\"Drenas\",\"tel\":\"04646664\",\"semundjeKronike\":\"JO\",\"alergji\":\"JO\",\"dataLindjes\":\"1982-08-31T00:00:00.000+0000\"},{\"id\":0,\"nrPersonal\":\"zasjdd23o1j\",\"emri\":\"Florian\",\"mbiemri\":\"Hoti\",\"emriPrindit\":\"akwd\",\"gjinia\":\"M\",\"adresa\":\"Drenas\",\"vendlindja\":\"Drenas\",\"tel\":\"04646664\",\"semundjeKronike\":\"JO\",\"alergji\":\"JO\",\"dataLindjes\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse()
               .getContentAsString(), false);


    }

    @Test public void addNewPacientTest() throws Exception {
        Pacient mockPacient = new Pacient("12j3o1j","Egzon","Berisha","Ali",'M',datalindjes1,"Drenas","Drenas","04646664","JO","JO");

      String examplePacientJson = "{\"id\":0,\"nrPersonal\":\"12j3o1j\",\"emri\":\"Egzon\",\"mbiemri\":\"Berisha\",\"emriPrindit\":\"Ali\",\"gjinia\":\"M\",\"adresa\":\"Drenas\",\"vendlindja\":\"Drenas\",\"tel\":\"04646664\",\"semundjeKronike\":\"JO\",\"alergji\":\"JO\"";

        when(pacientService.addNewPaceint(anyString())).thenReturn(mockPacient);

        // Send pacient as body to /addPacient
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/addPacient")
                .accept(MediaType.APPLICATION_JSON).content(examplePacientJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());



        assertEquals("http://localhost/addPacient/0",
                response.getHeader(HttpHeaders.LOCATION));

    }


    @Test
    public void deletePacByIdTest() throws Exception {
        String uri = "/delete/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
// assertEquals(content, "Pacient is deleted successsfully");
    }



    //Flori testing

//    @Test
//    public void getPacientsTest() throws Exception {
//        Mockito.when(pacientController.getPacients()).thenReturn(
//                Collections.emptyList());
//
//        MvcResult result = mockMvc.perform(
//                MockMvcRequestBuilders.get("/getPacients").accept(MediaType.APPLICATION_JSON)
//        ).andReturn();
//
//        System.out.println(result.getResponse());
//        Mockito.verify(pacientController).getPacients();
//    }
//
//    @Test
//    public void addPacientTest() throws Exception{
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addPacient").accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse());
//        String expected = "{id:1, nrPersonal:161737832, emri:Florian, mbiemri:Hoti, emriPrindit:Sheqe, gjinia:M," +
//                "datalindjes:1997-15-11, adresa:Drenas, vendlindja:Germany, tel:045567319, semundjeKronike: , alergji: }";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//    }


    }


