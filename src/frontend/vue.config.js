module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '^/api': {
                target: 'http://localhost:9092',
                changeOrigin: true,
                logLevel: 'debug',
                pathRewrite: { '^/api': '/' },
            },
        },
    },
}
