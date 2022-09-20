import Layout from '@/components/Layout/Layout'
import type {AppProps} from 'next/app'
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
      <GlobalStyle />
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </RecoilRoot>
  )
}

export default MyApp
