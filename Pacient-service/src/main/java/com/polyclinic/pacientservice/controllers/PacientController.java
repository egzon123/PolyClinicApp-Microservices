package com.polyclinic.pacientservice.controllers;

import com.polyclinic.pacientservice.models.Pacient;
import com.polyclinic.pacientservice.repositories.PacientRepository;
import com.polyclinic.pacientservice.services.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PacientController {

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private PacientService pacientService;

    @GetMapping("/pacient")
    @ResponseBody
    public String getTest(){
        return "hello from pacient" ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/pacients")
    public List<Pacient> getPacients(){

        return (List<Pacient>) pacientService.getPacients();
    }
    @PostMapping("/addPacient")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Pacient> addPacient(@RequestBody String formData)  {
        System.out.println("---------------------ADDED" +formData);

        Pacient pacient = null;
        try {
            pacient = pacientService.addNewPaceint(formData);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(pacient.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Pacient>(new HttpHeaders(),HttpStatus.FORBIDDEN);
        }



    }

    @RequestMapping(path="delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePacient(@PathVariable("id") int id){

         pacientService.deletePacient(id);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/edit/{id}")
    public RedirectView editPacient(@RequestBody String formData,@PathVariable("id") int id){
       System.out.println(formData);

        try {
            pacientService.editPacient( formData, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Date datalindjes = null;
//        try {
//            datalindjes=new SimpleDateFormat("yyyy-MM-dd").parse(s);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Optional<Pacient> pacientOld = pacientRepository.findById(id);
//
//        pacient.setId(pacientOld.get().getId());
//
//        pacient.setDataLindes(datalindjes);
//        System.out.println(pacient);
//
//        pacientRepository.save(pacient);
//        System.out.println("EDITED  ");
        return  localRedirectPacientet();
    }




    @GetMapping("/index")
    public RedirectView getIndexPage(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/index");
        return redirectView;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    public RedirectView localRedirectPacientet() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/pacinet-serivce/pacients");
        return redirectView;
    }

    public void sendRiderect() throws IOException {
        String url = "http://localhost:3000/pacientet";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNTYxNzMwOTE1LCJleHAiOjE1NjE4MTczMTV9.B19X5F2hmTGO6Ktfh5a_FQ-LZbvMjKqW01kJp5cU9EBxefy4hyXBNSFGRxrzLTMMNCmOrZQIGCb_gfCoH4kDHw";
        ((HttpURLConnection) con).setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "text/plain");
        con.setRequestProperty("charset", "UTF-8");
        con.setRequestProperty("Authorization", "Bearer " + token);
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

    }


    @GetMapping("appointments-from-pacient-service")
    public List<Object> getAppoinntmentsFromPacient(){
        System.out.println(pacientService.getAppointments());
        return pacientService.getAppointments();
    }



//@PostMapping("/addPacient")
//    public void addPacient(@ModelAttribute(value ="user") User user,  @RequestParam("user") String s){
//    System.out.println("---------------------"+user.getId());
//    System.out.println("---------------------"+s);
//    System.out.println("---------------------");
//
//}
}
