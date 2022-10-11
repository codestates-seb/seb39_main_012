import React from 'react'
import Image from 'next/image'
import styled from 'styled-components'

interface AvailableServiceCardProps {
  serviceTitle: string
  serviceContent: string
  iconImageSrc: string
  alt: string
  width: number
  height: number
}

const AvailableServiceCard = ({
  serviceTitle,
  serviceContent,
  iconImageSrc,
  alt,
  width,
  height,
}: AvailableServiceCardProps) => {
  return (
    <AvailableServiceBg>
      <AvailableServiceIcon>
        <AvailableServiceIconBg>
          <AvailableServiceIconWrapper>
            <Image src={iconImageSrc} alt={alt} width={width} height={height} />
          </AvailableServiceIconWrapper>
        </AvailableServiceIconBg>
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
  flex: 0 0 30%;
  border-radius: 1.5rem;
  padding: 0.8rem;
`

const AvailableServiceIconBg = styled.div`
  margin: 1rem;
  width: 6rem;
  height: 6rem;
  background-color: rgb(255, 255, 255);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
`

const AvailableServiceIconWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
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
    font-size: 1.5rem;
  }

  h3:first-child {
    font-weight: 700;
  }
`
