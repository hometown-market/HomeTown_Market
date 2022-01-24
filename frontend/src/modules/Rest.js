import Axios from 'axios'

// const BASE_URL = 'http://15.165.216.62:8080/api'
// const VERSION = 'v1'

const RestUrl = {
  ProductList: '/api/product_list/',
  SearchProductList: '/api/search/products/',
  ProductDetail: '/product/',
  emailCheck: '/api/v1/emailCheck'
}

const getConfig = () => {
  const config = { headers: { 'Content-Type': 'application/json' }, withCredentials: true }
  // const config = { headers: { 'Content-Type': 'application/json' } }
  // const accessToken = Session.getAccessToken() // localStorage
  const accessToken = ''
  if (accessToken) {
    config.headers['Access-Token'] = accessToken
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
