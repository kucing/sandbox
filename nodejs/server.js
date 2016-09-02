// https://scotch.io/tutorials/build-a-restful-api-using-node-and-express-4
var express = require('express');
var fs = require('fs');
var bodyParser = require('body-parser');
var app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json({ type: 'application/*+json' }));
app.use(bodyParser.text({ type: 'text/html' }));

var jsonParser = bodyParser.json();

var port = process.env.PORT || 8090;
var router = express.Router();

router.get('/premie', function(req, res) {
    console.log("[GET] /premie");
    fs.readFile( __dirname + "/data/premie.json", 'utf8', function (err, data) {
        res.end( data );
    });
});

router.post('/premie', jsonParser, function(req, res) {
    console.log("[POST] body: " + JSON.stringify(req.body, null, 2));
    console.log("[POST] body.name: " + req.body.name)

    fs.readFile( __dirname + "/data/premie.json", 'utf8', function (err, data) {
        res.end( data );
    });
});

app.use('/', router);

app.listen( port, function() {
    console.log( 'Express server listening on port %d in %s mode', port, app.settings.env );
});