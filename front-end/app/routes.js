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
};