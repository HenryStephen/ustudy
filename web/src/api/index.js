import axios from 'axios'
import config from '@/config'

const base =
  process.env.NODE_ENV === 'development'
    ? config.baseUrl.dev
    : config.baseUrl.pro


axios.interceptors.request.use(
  config => {
    let token  = localStorage.getItem('token')
    let url = config.url
    if (!url.startsWith('/api/token')){
      if (token) {
        token = 'Bearer' + ' ' + token.replace(/'|"/g, '')
        config.headers['Authorization'] = token
      }
    }
      
    return config
  },
  err => {
    Message.error('请求超时!')
    return Promise.resolve(err);
  }
)

export const postRequest = (url, data) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: data,
    transformRequest: [
      function (data) {
        let ret = ''
        for (const it in data) {
          ret +=
            encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
        }
        return ret
      }
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [
      function (data) {
        let ret = ''
        for (const it in data) {
          ret +=
            encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
        }
        return ret
      }
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export const deleteRequest = (url, data = {}) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    data: data,
    transformRequest: [
      function (data) {
        let ret = ''
        for (const it in data) {
          ret +=
            encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
        }
        return ret
      }
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export const getRequest = (url, params = {}) => {
  return axios({
    method: 'get',
    // url: '/api' + url,
    url: `${base}${url}`,
    params: params,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export const register = (telephone, password, captcha) => {
  return axios({
    method: 'post',
    url: '/api/user',
    params: {
      username: telephone,
      telephone:  telephone,
      password: password
    },
    headers: {
      captcha: captcha,
      deviceId: telephone
    }
  })
}

export const getSmsCode = (deviceId, captcha) => {
  return axios({
    method: 'get',
    url: '/api/code/sms',
    params: {
      deviceId:  deviceId
    },
    headers: {
      captcha: captcha,
      deviceId: deviceId
    }
  })
}

export const login = (phone, password) => {
  return axios({
    method: 'post',
    url: '/api/token',
    data: {
      phone: phone,
      password: password
      // captcha: verifyCode
    },
    headers: {},
    transformRequest: [
      function (data) {
        let ret = ''
        for (const it in data) {
          ret +=
            encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
        }
        return ret
      }
    ]
  })
}
