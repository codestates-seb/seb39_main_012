/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    domains: ['moongtel-imgs.s3.ap-northeast-2.amazonaws.com'],
  },
  reactStrictMode: true,
  swcMinify: true,
  compiler: {
    styledComponents: true, // styled-components classname Erorr
  },
  webpack(config) {
    config.module.rules.push({
      test: /\.svg$/i,
      issuer: /\.[jt]sx?$/,
      use: ['@svgr/webpack'],
    })
    return config
  },
}

module.exports = nextConfig
