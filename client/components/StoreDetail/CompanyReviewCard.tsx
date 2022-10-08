import React from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import Image from 'next/image'
import ReviewStarGenerator from './ReviewStarGenerator'

interface Props {
  reviewImageSrc: string
  alt: string
  reviewTitle: string
  reviewStarNum: number
  reviewContent: string
  reviewDate: string
  reviewerName: string
  reviewClicked: boolean
  setReviewClicked: React.Dispatch<React.SetStateAction<boolean>>
}

const CompanyReviewCard = ({
  reviewImageSrc,
  alt,
  reviewTitle,
  reviewStarNum,
  reviewContent,
  reviewDate,
  reviewerName,
  reviewClicked,
  setReviewClicked,
}: Props) => {
  return (
    <CompanyReviewsCard onClick={() => setReviewClicked(!reviewClicked)}>
      <CompanyReviewsCardTop>
        <Image
          src={reviewImageSrc}
          alt={alt}
          width={300}
          height={250}
          objectFit="cover"
          // unoptimized={true}
        />
      </CompanyReviewsCardTop>
      <CompanyReviewsCardBottom>
        <CompanyReviewsCardTitle>{reviewTitle}</CompanyReviewsCardTitle>
        <CompanyReviewsCardStars>{ReviewStarGenerator(reviewStarNum)}</CompanyReviewsCardStars>
        <CompanyReviewsCardContent>{reviewContent}</CompanyReviewsCardContent>
        <CompanyReviewsInfo>
          <span>{reviewDate}</span>
          <span>{reviewerName}</span>
        </CompanyReviewsInfo>
      </CompanyReviewsCardBottom>
    </CompanyReviewsCard>
  )
}

export default CompanyReviewCard

const CompanyReviewsCard = styled.div`
  /* flex: 1 1 30%; */
  width: 30%;
  border-radius: 3rem;
  overflow: hidden;
  box-shadow: 1px 1px 8px 2px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 4px 4px 8px 0 rgba(0, 0, 0, 0.2);

  :hover {
    transition: 0.3s;
    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.2), -3px -3px 3px -3px rgba(0, 0, 0, 0.2);
  }
`

const CompanyReviewsCardTop = styled.div``

const CompanyReviewsCardBottom = styled.div`
  padding: 1rem 1rem 1rem 1.5rem;
`

const CompanyReviewsCardTitle = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  margin: 1rem 0 0.6rem 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`

const CompanyReviewsCardContent = styled.p`
  height: 8rem;
  margin: 0.6rem 0 2rem 0;
  font-size: 1.3rem;
  color: rgb(102, 102, 102);
  line-height: 2rem;
  overflow: hidden;
  white-space: normal;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
  word-break: keep-all;
`
const CompanyReviewsCardStars = styled.div``

const CompanyReviewsInfo = styled.div`
  color: ${colors.grey2};
  font-size: 1.3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1.2rem 0;

  span:last-child {
    margin-right: 2rem;
  }
`
