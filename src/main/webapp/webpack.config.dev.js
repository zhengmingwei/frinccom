/****************************************************************
 *
 * 开发模式下的配置文件
 *
 ****************************************************************/
var path = require("path");
var webpack = require("webpack");
module.exports = {
    entry: {
        index: ["./source/entries/MainEntry.jsx"]
    },
    output: {
        path: path.resolve(__dirname, "assets"),
        publicPath: "/assets/",
        filename: "[name].bundle.js"
    },
    resolve: {
        root: [
            path.resolve('./components'),
            path.resolve('./source')
        ],
        extensions: ['', '.js', '.jsx']
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /(node_modules|bower_components)/,
                include: __dirname,
                loader: 'babel-loader',
                query: {
                    plugins: ['transform-runtime']
                }
            },
            {test: /\.css$/, loader: "style!css"},
        ]
    },
    plugins: [
    	new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': '"develop"'
            }
        })
    ]
};