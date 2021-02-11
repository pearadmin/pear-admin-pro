
const useMock = false 

if (useMock) {
  const Mock = require('mockjs2')
  require('./services/user')

  Mock.setup({
    timeout: 1000
  })
}

