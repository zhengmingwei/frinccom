var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack.config.dev');

var port = 9091;
var host = "127.0.0.1";

config.entry.index.unshift("webpack-dev-server/client?http://" + host + ":" + port, "webpack/hot/dev-server");


var compiler = webpack(config);

new WebpackDevServer(compiler, {
    publicPath: config.output.publicPath,
    hot: true,
    noInfo: false
}).listen(port, host, function (err, result) {
    if (err)
        console.log(err);
    else
        console.log('Listening at %s:%s', host, port);
});
