const BASE_URL = process.env.NODE_ENV === 'production'
  ? '/'
  : '/'

module.exports = {
  publicPath: BASE_URL,
  lintOnSave: true,
  // 这里写你调用接口的基础路径，来解决跨域，如果设置了代理，那你本地开发环境的axios的baseUrl要写为 '' ，即空字符串
  devServer: {
    port: 8080,
    proxy: {
        '/api': {
            target: 'http://localhost:4002',
            changeOrigin: true,
            pathRewrite: {
                '^/api': ''
            }
        }
    }
  }
}
