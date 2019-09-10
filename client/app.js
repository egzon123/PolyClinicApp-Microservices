const path = require('path');

// const expressThymeleaf = require("express-thymeleaf")
// const TemplateEngine = require("thymeleaf");
const express = require('express');
const bodyParser = require('body-parser');

var cors = require('cors');
const request = require('request');
var opn = require('opn');
const app = express();
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cors());
app.use(express.static(path.join(__dirname, 'public')));

// app.engine('html', expressThymeleaf(TemplateEngine));
// app.set('view engine', 'html');
app.set('view engine', 'ejs');
app.set('views', 'views');
const axios = require("axios");

var token = 'Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNTY3MjY3ODcwLCJleHAiOjE1NjczNTQyNzB9.U4OcC1OKvnKfT8dvo5cgHm8WCP1uBAFkG33etRx0pUroJHAOtMG42LloGv3DHJ7Lx_lXZQKgr1V4NbvrHhqwqw';

// const Eureka = require('eureka-js-client').Eureka;
// // example configuration
// const client = new Eureka({
//     // application instance information
//     instance: {
//       app: 'client-app-node',
//       hostName: 'localhost:3000',
//       ipAddr: '127.0.0.1',
//       statusPageUrl: 'http://localhost:3000',
//       vipAddress: 'client-app-node',
//       port: {
//         $: 3000,
//         '@enabled': 'true',
//       },
//       dataCenterInfo: {
//         '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
//         name: 'MyOwn',
//       },
//       registerWithEureka: true,
//       fetchRegistry: true,
//     },
//     eureka: {
//       // eureka server host / port
//       host: 'localhost',
//       port: 8761,
//       servicePath: '/eureka/apps/',
//     },
//   });

//   client.logger.level('debug');
// client.start(error => {
//   console.log(error || 'NodeJS Eureka Started!')});
// axios.get("http://localhost:8765/currency-conversion-service/currency-exchage-feign/getAll").then(response=>{
      
    
// app.get('/', function(req, res){ 
//      res.render("index",{money:response.data});
//   }); 

// });
app.get("/index",function(req,res){
    res.render("index");
})

app.get("/calendar",function(req,res){
     res.render("calendarView");
})

app.get('/login',function(req,res){
    res.render("login");
})
app.post("/login",function(req,res){
    var username = req.body.username;
    var password = req.body.password;
    request({
        url: "http://localhost:8080/auth",
        json: true,
        multipart: {
            chunked: false,
            data: [
                {
                    'content-type': 'application/json',
                    body: {
                        "username":"admin",
                        "password":"12345"
                    }
                }
            ]
        }
    }, function (error, response, body) {
        if (!error && response.statusCode === 200) {
            console.log(body)
            console.log("HEADER:" + response.headers['content-encoding']);
        }
        else {

            console.log("error: " + error)
            console.log("response.statusCode: " + response.statusCode)
            console.log("response.statusText: " + response.statusText)
        }
    })
});

app.get("/pacientet" ,function(req,response){

    request({
        url: 'http://localhost:8080/pacient-service/api/pacients',
        headers: {
           'Authorization': token
        },
        rejectUnauthorized: false
      }, function(err, res) {
            if(err) {
              console.error(err);
            } else {
            
              response.render("pacientet",{

                              pacients :JSON.parse(res.body)
                          })
            }
      
      });
})

app.get("/google",function(req,res){
    
    opn('http://google.com');
})

// app.get('/pacientet',function(req,res){
//     axios.get("http://localhost:8080/pacient-service/pacients").then(result =>{


//         res.render("pacientet",{
//             pacients :result.data
//         })
// //         templateEngine.processFile('template.html', { greeting: 'Hello!' })
// //   .then(result => {
// //     // Do something with the result...
// //   });
//     })
// });

app.get('/regjistro-pacientin',function(req,res){
    res.render("regjistro-pacientin")
});

app.get("/somedata",function(req,res){
    res.render("/pacientet")
})

app.get("/getToken",function(req,res){
    var token = req.headers['x-access-token'];
    if (!token) return res.status(401).send({ auth: false, message: 'No token provided.' });
    
    jwt.verify(token, config.secret, function(err, decoded) {
      if (err) return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });
      
      res.status(200).send(decoded);
    });

})
app.post('/shto-pac',function(req,response){
    // axios.get("http://localhost:8080/pacient-service/addPacient").then(result =>{


    //     res.render("pacientet",{
    //         pacients :result.data
    //     })
    // })
   
    console.log()
    request({
        method : 'POST',
        url: 'http://localhost:8080/pacient-service/api/addPacient',
        headers: {
           'Authorization': token
        },
         body: JSON.stringify({
             nrPersonal :req.body.nrPersonal,
            emri: req.body.emri,
            mbiemri: req.body.mbiemri,
            emriPrindit: req.body.emriPrindit,
            gjinia: req.body.gjinia,
            datalindjes:req.body.datalindjes,
            adresa : req.body.adresa,
            vendlindja: req.body.vendlindja,
            tel: req.body.tel,
            semundjeKronike: req.body.semundjeKronike,
            alergji: req.body.alergji
        
        }),
        params:req.body.datalindjes,
        rejectUnauthorized: false
      }, function(err, res) {
            if(err) {
              console.error(err);
            } else {
                console.log(req.body.datalindjes);
              
                console.log("added");
                response.redirect("http://localhost:3000/pacientet");
            }
      
      });
});


app.post('/deletePac/:id',function(req,response,next){
    console.log(req.params.id);
    request({   
        method:'DELETE',
        url: 'http://localhost:8080/pacient-service/api/delete/'+req.params.id,
        headers: {
           'Authorization': token
        },
        rejectUnauthorized: false
      }, function(err, res) {
            if(err) {
              console.error(err);
            } else {
                
                response.redirect("http://localhost:3000/pacientet");
            
            }
      
      });
})


app.post('/editpac/:id',function(req,response){

        console.log("Edit pac with id: "+req.params.id);
  

        request({
            method : 'POST',
            url: 'http://localhost:8080/pacient-service/api/edit/'+req.params.id,
            headers: {
               'Authorization': token
            },
             body: JSON.stringify({
                 nrPersonal :req.body.nrPersonal,
                emri: req.body.emri,
                mbiemri: req.body.mbiemri,
                emriPrindit: req.body.emriPrindit,
                gjinia: req.body.gjinia,
                datalindjes:req.body.datalindjes,
                adresa : req.body.adresa,
                vendlindja: req.body.vendlindja,
                tel: req.body.tel,
                semundjeKronike: req.body.semundjeKronike,
                alergji: req.body.alergji
            
            }),
            params:req.body.datalindjes,
            rejectUnauthorized: false
          }, function(err, res) {
                if(err) {
                  console.error(err);
                } else {
                    console.log(req.body.datalindjes);
                  
                    console.log("edited");
                    response.redirect("http://localhost:3000/pacientet");
                }
          
          });
})

app.listen(3000,function(){
    console.log('Client app listening on port 3000');
})
