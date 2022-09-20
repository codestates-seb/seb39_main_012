import {createGlobalStyle} from 'styled-components'
import {reset} from './css'

export const GlobalStyle = createGlobalStyle`
  ${reset}
  html {
    font-size: 62.5%;
    box-sizing: border-box;
    font-family: 나눔스퀘어, 'NanumSquare', sans-serif;
    color: rgb(51, 51, 51);
  }
  
`
