import React from 'react'
import {colors} from '@/styles/colors'
import Image from 'next/image'
import styled from 'styled-components'

interface AvailableServiceCardProps {
  serviceTitle: string
  serviceContent: string
  iconImage: string
  alt: string
}

const AvailableServiceCard = ({
  serviceTitle,
  serviceContent,
  iconImage,
  alt,
}: AvailableServiceCardProps) => {
  return (
    <AvailableServiceBg>
      <AvailableServiceIcon>
        <AvailableServiceIconWrapper>
          <Image src={iconImage} alt={alt} width={60} height={60} />
        </AvailableServiceIconWrapper>
      </AvailableServiceIcon>
      <AvailableServiceContent>
        <h3>{serviceTitle}</h3>
        <h3>{serviceContent}</h3>
      </AvailableServiceContent>
    </AvailableServiceBg>
  )
}

export default AvailableServiceCard

const AvailableServiceBg = styled.div`
  display: flex;
  width: 30%;
  border-radius: 1.5rem;
  padding: 0.8rem;
`

const AvailableServiceIconWrapper = styled.div`
  margin: 1rem;
`

const AvailableServiceIcon = styled.div`
  flex: 1;
`

const AvailableServiceContent = styled.div`
  flex: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  line-height: 2rem;
  color: rgb(51, 51, 51);

  h3 {
    font-size: 1.6rem;
  }

  h3:first-child {
    font-weight: 700;
  }
`
