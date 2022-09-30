import React from 'react'
import styled from 'styled-components'
import Image from 'next/image'
import {colors} from '@/styles/colors'

interface SectionTitleProps {
  title: string
  sub1?: string
  sub2?: string
}

const SectionTitle = ({title, sub1, sub2}: SectionTitleProps) => {
  return (
    <CompanySectionTitle>
      <Image src="/images/dogpaw.png" alt="section-title" width={25} height={25} />
      <span className="text title">{title}</span>
      <span className="text sub1">{sub1}</span>
      <span className="text">{sub2}</span>
    </CompanySectionTitle>
  )
}

export default SectionTitle

const CompanySectionTitle = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 2rem;

  .text {
    font-size: 2rem;
    font-weight: 700;
  }

  .title {
    margin-left: 0.5rem;
  }

  .sub1 {
    color: ${colors.mainColor};
    margin-left: 0.5rem;
  }
`
