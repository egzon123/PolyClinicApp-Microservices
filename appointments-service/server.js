const express = require("express"); 
// use body parse for parsing POST request
const bodyParser = require("body-parser"); 
const app = express();
const port = 2000; 

// we'll use mysql for db access and util to promisify queries
const util = require("util");
const mysql = require('mysql');

// use your own parameters for database
const mysqlConfig = {
	"connectionLimit": 10,
	"host": "localhost",
	"user": "root",
	"password": "xoni123",
	"database": "scheduler"
};


var cors = require('cors')

app.use(cors()) 
// const helmet = require("helmet");
// app.use(helmet());

// scheduler sends application/x-www-form-urlencoded requests,
app.use(bodyParser.urlencoded({ extended: true })); 

// you'll need these headers if your API is deployed on a different domain than a public page 
// in production system you could set Access-Control-Allow-Origin to your domains
// or drop this expression - by default CORS security is turned on in browsers
// app.use(function(req, res, next) {
// 	res.header("Access-Control-Allow-Origin", "*");
// 	res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
// 	res.header("Access-Control-Allow-Methods", "*");
// 	next();
// });

// return static pages from "./public" directory
app.use(express.static(__dirname + "/public"));

// --- Eureka client
const Eureka = require('eureka-js-client').Eureka;
// example configuration
const client = new Eureka({
    // application instance information
    instance: {
 
      app: 'appointments-service',
      hostName: 'localhost:2000',
      ipAddr: '127.0.0.1',
      statusPageUrl: 'http://localhost:2000',
      vipAddress: 'appointments-service',
      port: {
        $: 2000,
        '@enabled': 'true',
      },
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
      registerWithEureka: true,
      fetchRegistry: true,
    },
    eureka: {
      // eureka server host / port
      host: 'localhost',
      port: 8761,
      servicePath: '/eureka/apps/',
    },
  });

  client.logger.level('debug');
client.start(error => {
  console.log(error || 'Appointments service Eureka Started!')});

const router = require("./router");

// open connection to mysql
const connectionPool = mysql.createPool(mysqlConfig);
// console.log(connectionPool);
connectionPool.query = util.promisify(connectionPool.query);

// add listeners to basic CRUD requests
const Storage = require("./storage");
const eventsStorage = new Storage(connectionPool);
router.setRoutes(app, "/events", eventsStorage);



// start server
app.listen(port, () => {
	console.log("Server is running on port " + port + "...");
});