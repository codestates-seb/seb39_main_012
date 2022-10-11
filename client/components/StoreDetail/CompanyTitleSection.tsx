import React, {useEffect, useState} from 'react'
import {colors} from '@/styles/colors'
import styled from 'styled-components'
import {AiFillStar} from 'react-icons/ai'
import {AiFillHeart} from 'react-icons/ai'
import {AiOutlineHeart} from 'react-icons/ai'
import {FiShare} from 'react-icons/fi'
import {PostById} from '@/types/post'
import {authPostService} from '@/apis/AuthPostAPI'
import LocalStorage from '@/utils/util/localStorage'
import {toast} from 'react-toastify'

interface Props {
  companyAddress: string
  companyName: string | undefined
  post: PostById | undefined
  postId: number
  // clickedLike: boolean
  // setClickedLike: React.Dispatch<React.SetStateAction<boolean>>
  addLikes: number
  setAddLikes: React.Dispatch<React.SetStateAction<number>>
}

const CompanyTitleSection = ({
  companyAddress,
  companyName,
  post,
  postId,
  // clickedLike,
  // setClickedLike,
  addLikes,
  setAddLikes,
}: Props) => {
  const [clickedLike, setClickedLike] = useState(false)

  useEffect(() => {
    if (window.Kakao) {
      const kakao = window.Kakao
      if (!kakao.isInitialized()) {
        window.Kakao.init(process.env.NEXT_PUBLIC_KAKAO_API_KEY)
      }
    }
  }, [])

  const shareKakao = () => {
    // window.Kakao.Link.sendCustom
    window.Kakao.Share.sendScrap({
      requestUrl: location.origin + location.pathname,
      // requestUrl: location.href,
      // templateId: 83072,
      // })
      templateId: 83074,
      templateArgs: {
        title: companyName,
        description: '호텔링 바로 예약하기',
        postId: String(postId),
        // image_url:''
        // pagePathname: location.pathname,
      },
      installTalk: true,
    })
  }

  const addLikesHandler = async () => {
    if (!LocalStorage.getItem('accessToken')) {
      toast.error('로그인이 필요한 서비스입니다.')
      return
    }

    setClickedLike(!clickedLike)
    if (clickedLike) {
      setAddLikes(addLikes - 1)
    } else {
      setAddLikes(addLikes + 1)
    }
  }

  return (
    <CompanyInfoTitleBox>
      <CompanyTitleText>
        <h1>{companyAddress + ' ' + companyName}</h1>
      </CompanyTitleText>
      <CompanyTitleSub>
        <CompanyTitleSubLeft>
          <CompanyTitleSubLeftReview>
            <span>
              <AiFillStar color={colors.mainColor} size="20" />
            </span>
            <span>{post?.avgScore}</span>
          </CompanyTitleSubLeftReview>
          <CompanyTitleSubLeftTag>
            {post?.hashTag?.map((hashtag, idx) => (
              <span key={idx}>#{hashtag.tag}</span>
            ))}
          </CompanyTitleSubLeftTag>
        </CompanyTitleSubLeft>
        <CompanyTitleSubRight>
          <CompanyPageShare onClick={shareKakao}>
            <span>
              <FiShare />
            </span>
            <span>공유하기</span>
          </CompanyPageShare>
          <CompanyPageLike onClick={addLikesHandler}>
            {clickedLike ? (
              <span className="likeClicked">
                <AiFillHeart size="15" />
                <span>찜하기 {addLikes}</span>
              </span>
            ) : (
              <span>
                <AiOutlineHeart size="15" />
                <span>찜하기 {addLikes}</span>
              </span>
            )}
          </CompanyPageLike>
        </CompanyTitleSubRight>
      </CompanyTitleSub>
    </CompanyInfoTitleBox>
  )
}

export default CompanyTitleSection

const CompanyInfoTitleBox = styled.div``

const CompanyTitleText = styled.div`
  h1 {
    font-size: 2.1rem;
    font-weight: 600;
  }
`

const CompanyTitleSub = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
`

const CompanyTitleSubLeft = styled.div`
  display: flex;
  font-size: 1.5rem;
`

const CompanyTitleSubLeftReview = styled.div`
  margin-right: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;

  span:first-child {
  }

  span:last-child {
    margin-left: 0.5rem;
  }
`
const CompanyTitleSubLeftTag = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    color: ${colors.grey4};
    margin-left: 0.8rem;
  }
`

const CompanyTitleSubRight = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.4rem;
  color: rgb(174, 205, 163);
`

const CompanyPageShare = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  span:last-child {
    margin-left: 0.5rem;
    text-decoration: underline;
    font-weight: 600;
    cursor: pointer;
  }

  :hover {
    color: ${colors.mainColor};
  }
`
const CompanyPageLike = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 2rem;

  span {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  span:last-child {
    margin-left: 0.2rem;
    text-decoration: underline;
    font-weight: 600;
    cursor: pointer;
  }

  :hover {
    color: ${colors.mainColor};
  }

  .likeClicked {
    color: ${colors.mainColor};
  }
`
