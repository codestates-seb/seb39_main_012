import React from 'react'
import Image from 'next/image'
import {colors} from '@/styles/colors'
import styled from 'styled-components'
import ReviewStarGenerator from './ReviewStarGenerator'

interface Props {
  reviewStarNum: number
  reviewImageSrc1?: string
  reviewImageSrc2?: string
  reviewImageSrc3?: string
  reviewTitle: string
  reviewContent: string
  reviewDate: string
  reviewerName: string
}

const CompanyReviewListCard = ({
  reviewStarNum,
  reviewerName,
  reviewDate,
  reviewTitle,
  reviewContent,
  reviewImageSrc1,
  reviewImageSrc2,
  reviewImageSrc3,
}: Props) => {
  return (
    <CompanyReviewListCardContainer>
      <CompanyReviewsListInfo>
        <span>{ReviewStarGenerator(reviewStarNum)}</span>
        <span>{reviewerName}</span>
        <span>{reviewDate}</span>
      </CompanyReviewsListInfo>
      <CompanyReviewsListTitle>{reviewTitle}</CompanyReviewsListTitle>
      <CompanyReviewsListContent>{reviewContent}</CompanyReviewsListContent>
      <CompanyReviewsListImages>
        <CompanyReviewListImagesBox>
          {reviewImageSrc1 !== undefined ? (
            <CompanyReviewsListImage>
              <Image
                src={reviewImageSrc1}
                alt="review-image"
                width={180}
                height={180}
                objectFit="fill"
                unoptimized={true}
              />
            </CompanyReviewsListImage>
          ) : null}

          {reviewImageSrc2 !== undefined ? (
            <CompanyReviewsListImage>
              <Image
                src={reviewImageSrc2}
                alt="review-image"
                width={180}
                height={180}
                objectFit="fill"
                unoptimized={true}
              />
            </CompanyReviewsListImage>
          ) : null}
          {reviewImageSrc3 !== undefined ? (
            <CompanyReviewsListImage>
              <Image
                src={reviewImageSrc3}
                alt="review-image"
                width={180}
                height={180}
                objectFit="fill"
                unoptimized={true}
              />
            </CompanyReviewsListImage>
          ) : null}
        </CompanyReviewListImagesBox>
      </CompanyReviewsListImages>
    </CompanyReviewListCardContainer>
  )
}

export default CompanyReviewListCard

const CompanyReviewListCardContainer = styled.div`
  border-top: 1px solid rgb(238, 237, 237);
  padding: 3rem 0;

  span {
    font-size: 1.3rem;
    color: ${colors.grey2};
    margin-right: 3rem;
  }
`

const CompanyReviewsListTitle = styled.div`
  font-size: 1.5rem;
  font-weight: 600;
  margin: 1rem 0 0.6rem 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`
const CompanyReviewsListContent = styled.div`
  margin: 0.6rem 0 2rem 0;
  font-size: 1.3rem;
  color: rgb(102, 102, 102);
  line-height: 2.2rem;
  overflow: hidden;
  white-space: normal;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
  word-break: keep-all;
`

const CompanyReviewsListInfo = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`

const CompanyReviewsListImages = styled.div``

const CompanyReviewListImagesBox = styled.div`
  display: flex;
  gap: 10px;
`

const CompanyReviewsListImage = styled.div`
  border-radius: 0.7rem;
  overflow: hidden;
  cursor: pointer;
`
