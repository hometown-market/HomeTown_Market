import Axios from 'axios'

// const BASE_URL = 'http://15.165.216.62:8080/api'
// const VERSION = 'v1'

const RestUrl = {
  ProductList: '/api/product_list/',
  CategoryProductList: '/api/product_list/:categoryId',
  SearchProductList: '/api/search/products/',
  WishProductList: '/shop/wish',
  Wish: '/api/products/wish/:productId',
  ProductDetail: '/product/:productId',
  emailCheck: '/api/login/email-check'
}

const getConfig = () => {
  const config = { headers: { 'Content-Type': 'application/json;charset=utf-8' }, withCredentials: true }
  // const config = { headers: { 'Content-Type': 'application/json' } }
  const accessToken = localStorage.getItem('access_token') // localStorage
  if (accessToken) {
    config.headers.Authorization = accessToken
  }
  config.headers['Access-Control-Allow-Origin'] = '*'
  config.headers['Access-Control-Allow-Headers'] = '*'
  return config
}

const Rest = {
  get (url) {
    const config = getConfig()
    return Axios.get(url, config)
  },

  post (url, body) {
    const config = getConfig()
    return Axios.post(url, body, config)
  },

  delete (url) {
    const config = getConfig()
    return Axios.delete(url, config)
  }
}

export { RestUrl, Rest, Axios }
