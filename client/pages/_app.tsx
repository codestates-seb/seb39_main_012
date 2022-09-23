import Layout from '@/components/Layout/Layout'
import type {AppProps} from 'next/app'
import Script from 'next/script'
import {RecoilRoot} from 'recoil'
import {GlobalStyle} from '../styles/GlobalStyle'
import 'react-toastify/dist/ReactToastify.css'

declare global {
  interface Window {
    Kakao: any
  }
}

function MyApp({Component, pageProps}: AppProps) {
  return (
    <RecoilRoot>
      <Script
        strategy="beforeInteractive"
        src={`//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.NEXT_PUBLIC_KAKAO_API_KEY}&autoload=false&libraries=services`}
      />
      <GlobalStyle />
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </RecoilRoot>
  )
}

export default MyApp
