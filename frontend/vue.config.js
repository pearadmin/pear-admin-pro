const path = require("path");
const resolve = dir => {
  return path.join(__dirname, dir);
};
const isDev = process.env.NODE_ENV === "development";
const CompressionWebpackPlugin = require("compression-webpack-plugin");
const ProductionGzipExtensions = ["html", "js", "css", "svg"];
const WebpackBar = require("webpackbar");
const webpackBarName = "pear-admin";

module.exports = {
  publicPath: "",
  outputDir: "dist/admin",
  devServer: {
    open: true, 
    port: 8080 
  },
  parallel: require("os").cpus().length > 1,
  configureWebpack(config) {
    return {
      resolve: {
        alias: {
          "@": resolve("src")
        }
      },
      plugins: [
        new WebpackBar({
          name: webpackBarName
        })
      ]
    };
  },
  chainWebpack(config) {
    config.when(isDev, config => {
      config.devtool("source-map");
    });
    config.plugins.delete("prefetch");
    config.resolve.symlinks(true);

    config.when(!isDev, config => {
      config.devtool("none");
      config.optimization.splitChunks({
        chunks: "all",
        cacheGroups: {
          libs: {
            name: "Chunk-Libs",
            test: /[\\/]node_modules[\\/]/,
            priority: 10,
            chunks: "initial"
          },
          AntdUI: {
            name: "Chunk-Antd",
            priority: 20,
            test: /[\\/]node_modules[\\/]_?@ant-design(.*)/
          }
        }
      });
      config
        .plugin("compression")
        .use(CompressionWebpackPlugin, [
          {
            filename: "[path][base].gz[query]",
            algorithm: "gzip",
            test: new RegExp(
              "\\.(" + ProductionGzipExtensions.join("|") + ")$"
            ),
            threshold: 8192,
            minRatio: 0.8
          }
        ])
        .end();
    });
  },
  runtimeCompiler: true,
  productionSourceMap: false,
  css: {
    requireModuleExtension: true,
    sourceMap: true,
    loaderOptions: {
      less: {
        lessOptions: {
          javascriptEnabled: true
        }
      }
    }
  }
};
