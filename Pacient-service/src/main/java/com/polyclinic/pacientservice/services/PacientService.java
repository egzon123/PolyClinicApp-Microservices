package com.polyclinic.pacientservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polyclinic.pacientservice.AppointmentServiceProxy;
import com.polyclinic.pacientservice.models.Pacient;
import com.polyclinic.pacientservice.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacientService {

        @Autowired
        private PacientRepository pacientRepository;
        @Autowired
        private AppointmentServiceProxy appointmentServiceProxy;

        public List<Pacient>  getPacients(){
            return (List<Pacient>) pacientRepository.findAll();
        }

        public Pacient getPacById(int id){
            return pacientRepository.findPacientById(id);
        }

        public int getSize(){
            return (int) pacientRepository.count();
        }

        public Pacient addNewPaceint(String formData) throws IOException {
            System.out.println("---------------------TO Add" +formData);
//            String [] array = formData.split(",");
//
//            List<String> arrayList = new ArrayList<>();
//            for(String str : array){
//                arrayList.add(str);
//            }
//
//            arrayList.remove(5);
//            System.out.println(arrayList);
//            String [] datal = array[5].split(":");
//            String s = datal[1].substring(1,10);
//            System.out.println(datal[1]+"---------------------------------------"+s);
//
//            StringBuilder sb = new StringBuilder();
//            for (String item : arrayList)
//            {
//
//                sb.append(item);
//                if(!item.contains("alergji")){
//                    sb.append(",");
//                }
//
//            }
//            System.out.println(sb.toString());
//            ObjectMapper mapper = new ObjectMapper();
//            Pacient pac = mapper.readValue(sb.toString(),Pacient.class);
//            Date datalindjes = null;
//            try {
//                datalindjes=new SimpleDateFormat("yyyy-MM-dd").parse(s);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            pac.setDataLindes(datalindjes);
            Pacient pac = convertFormData(formData);

            System.out.println(pac.getDataLindjes());
            System.out.println(pac.toString());
            pacientRepository.save(pac);
            return pac;
        }


        public Pacient deletePacient(int id){
            Optional<Pacient> pacient = pacientRepository.findById(id);
            Pacient pac = null;
            if(pacient.isPresent()){
                pac = pacient.get();
            }else{
                System.out.println("No pacient with id:"+id);
            }
            pacientRepository.delete(pac);
            System.out.println("DELETED");

            return pac;
        }

        public List<Pacient> getAll(){
            return (List<Pacient>) pacientRepository.findAll();
        }

        public int getPacientsSize(){
            List<Pacient>  pacients = (List<Pacient>) pacientRepository.findAll();

            return pacients.size();


        }

        public Pacient editPacient(String formData,int id) throws IOException {
            System.out.println("---------------------TO EDIT" +formData);
            Pacient pacient = convertFormData(formData);
            Optional<Pacient> pacientOld = pacientRepository.findById(id);

        pacient.setId(pacientOld.get().getId());


        System.out.println(pacient);

        pacientRepository.save(pacient);
        System.out.println("EDITED  ");
            return pacient;
        }

        public Pacient convertFormData(String formData) throws IOException {
            String [] array = formData.split(",");

            List<String> arrayList = new ArrayList<>();
            for(String str : array){
                arrayList.add(str);
            }

            arrayList.remove(5);
            System.out.println(arrayList);
            String [] datal = array[5].split(":");
            String s = datal[1].substring(1,10);
            System.out.println(datal[1]+"---------------------------------------"+s);

            StringBuilder sb = new StringBuilder();
            for (String item : arrayList)
            {

                sb.append(item);
                if(!item.contains("alergji")){
                    sb.append(",");
                }

            }
            System.out.println(sb.toString());
            ObjectMapper mapper = new ObjectMapper();
            Pacient pac = mapper.readValue(sb.toString(),Pacient.class);
            Date datalindjes = null;
            try {
                datalindjes=new SimpleDateFormat("yyyy-MM-dd").parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
          pac.setDataLindes(datalindjes);
            return pac;
        }


    public Object findById(int id) {
            return pacientRepository.findPacientById(id);
    }

    public List<Object> getAppointments(){
           return appointmentServiceProxy.showEvents();
    }
}
