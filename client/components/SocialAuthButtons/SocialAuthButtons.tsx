import React from 'react'
import Image from 'next/image'
import styled from 'styled-components'

const SocialAuthButtons = () => {
  return (
    <Buttons>
      <Image src="/images/authGoogle.png" alt="google-login" width={53} height={53} />
      <Image src="/images/authFacebook.png" alt="facebook-login" width={50} height={50} />
      <Image src="/images/authNaver.png" alt="naver-login" width={50} height={50} />
      <Image src="/images/authKakao.png" alt="kakao-login" width={50} height={50} />
      <Image src="/images/authApple.png" alt="apple-login" width={45} height={45} />
    </Buttons>
  )
}

export default SocialAuthButtons

const Buttons = styled.div`
  margin-top: 5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1.5rem;
  cursor: pointer;
`
