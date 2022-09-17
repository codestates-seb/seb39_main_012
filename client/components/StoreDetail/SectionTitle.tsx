import React from 'react'
import styled from 'styled-components'
import Image from 'next/image'

interface SectionTitleProps {
  title: string
}

const SectionTitle = ({title}: SectionTitleProps) => {
  return (
    <CompanySectionTitle>
      <Image src="/images/dogpaw.png" alt="google-login" width={25} height={25} />
      <span>{title}</span>
    </CompanySectionTitle>
  )
}

export default SectionTitle

const CompanySectionTitle = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 2rem;

  span {
    font-size: 2rem;
    margin-left: 0.5rem;
    font-weight: 500;
  }
`
