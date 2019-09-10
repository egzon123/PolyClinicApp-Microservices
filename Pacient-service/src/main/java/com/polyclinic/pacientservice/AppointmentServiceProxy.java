package com.polyclinic.pacientservice;

import feign.Headers;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="appointments-service" ,url = "http://localhost:2000")
//@FeignClient("zuul-api-gateway")
//@RibbonClient("appointments-service")
public interface AppointmentServiceProxy {
//   @Headers("Authorization: Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNTYyNTAzMzY0LCJleHAiOjE1NjI1ODk3NjR9.HNrnMLZW6j8JbFqyJx2wGabl3AWcxzBOYg0RbIcsI3BCBv2hwC_7DsYBPQNx8jxqYVNX85LOAaJJGK7sHvvDeg")
    @GetMapping("/events")
    List<Object> showEvents();
}
