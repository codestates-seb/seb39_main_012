import Document, {Html, Head, Main, NextScript} from 'next/document'

class MyDocument extends Document {
  render() {
    return (
      <Html>
        <Head>
          <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@2.0/nanumsquare.css"
          />
          {/* <script defer src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> */}
          <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    )
  }
}

export default MyDocument
