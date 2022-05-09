const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/api", {
      target: "http://localhost:8080", cookieDomainRewrite: "localhost", onProxyReq: relayRequestHeaders,
      onProxyRes: relayResponseHeaders
    })
  );
};

function relayRequestHeaders(proxyReq, req) {
  Object.keys(req.headers).forEach(function (key) {
    proxyReq.setHeader(key, req.headers[key]);
  });
}
function relayResponseHeaders(proxyRes, req, res) {
  Object.keys(proxyRes.headers).forEach(function (key) {
    res.append(key, proxyRes.headers[key]);
  });
}

