var request = require('request');
module.exports = function(app) {
    app.post('/images', function(req, res){
        console.log(req.body);
        var path = req.body.image.path;
        var imageName = req.body.image.name;
        var publicId = imageName.split('.')[0];
        /// If there's an error
        if(!imageName){
            console.log("There was an error: " + imageName)
            res.redirect("/");
            res.end();
        } else {
            console.log(path);
            var images = [];
            var image = {};
            image.path = path
            images.push(image)
            res.json({'images':images});
        }
    });

    app.get('/test', function(req, res){
        request.get('http://127.0.0.1:8080/createindex')
        .on('response', function(response) {
            console.log(response.statusCode) // 200
            console.log(response.headers['content-type']) // 'image/png'

        })
    });
};