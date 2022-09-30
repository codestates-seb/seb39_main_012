import {createGlobalStyle} from 'styled-components'
import {reset} from './css'

export const GlobalStyle = createGlobalStyle`
  ${reset}
  * {
  font-family: 나눔스퀘어, 'NanumSquare', sans-serif;
  }

  html {
    font-size: 62.5%;
    box-sizing: border-box;
    color: rgb(51, 51, 51);
  }
  
  body {
    font-family: 나눔스퀘어, 'NanumSquare', sans-serif;
  }
`
