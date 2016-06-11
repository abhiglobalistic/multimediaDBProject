var express = require('express');
var bodyParser = require('body-parser');
var formidable = require('express-formidable');
var app = express();

app.use(express.static(__dirname + '/public'));
app.use(bodyParser.urlencoded({'extended':'true'}));
app.use(bodyParser.json());
app.use(bodyParser.json({type: 'application/vnd.api+json'}));
//app.use(methodOverride());
app.use(formidable.parse());
app.use(function(req, res, next) { 
	res.header('Access-Control-Allow-Origin', "*"); 
	res.header('Access-Control-Allow-Methods','GET,PUT,POST,DELETE'); 
	res.header('Access-Control-Allow-Headers', 'Content-Type'); 
	next();
});


//var database = require('./config/database');
//mongoose.connect(database.url)
var port = process.env.NODE_PORT || 3000
var server = app.listen(port);
var routes = require('./app/routes')(app);	
console.log("App listening on port " + port);