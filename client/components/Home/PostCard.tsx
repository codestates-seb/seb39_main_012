import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import {GoLocation} from 'react-icons/go'
import {dummyPosts} from '@/utils/dummy/dummy'
import {colors} from '@/styles/colors'
import {toLocalScale} from '@/utils/util'
import {AiFillStar} from 'react-icons/ai'

type Post = typeof dummyPosts[0]

interface Props {
  post: Post
}

function PostCard({post}: Props) {
  const address = `${post.address[0].split(' ')[0]} ${post.address[0].split(' ')[1]}`
  return (
    <Container>
      <ImgBox>
        <CardImage mode="post" imgUrl={post.companyPostsImgList[0].imgUrl} />
      </ImgBox>
      <Content>
        <Location>
          <GoLocation />
          <span>{address}</span>
        </Location>
        <Title>{post.title}</Title>
        <Price>{toLocalScale(post.price)}원 / 박</Price>
        <Rating>
          <AiFillStar />
          <span>{post.rating}</span>
        </Rating>
      </Content>
    </Container>
  )
}

export default PostCard

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 300px;
  height: 400px;
  font-size: 20px;
  cursor: pointer;
  border-radius: 20px;

  @media (max-width: 768px) {
    width: 245px;
    height: 360px;
    font-size: 15px;
  }

  @media (max-width: 500px) {
    width: 170px;
    height: 280px;
    font-size: 10px;
  }
`

const Content = styled.div`
  padding-left: 5px;
`

const ImgBox = styled.div`
  height: 60%;
`

const Location = styled.div`
  display: flex;
  align-items: center;
  color: ${colors.grey2};
  font-size: 14px;
  margin-top: 15px;

  @media (max-width: 500px) {
    font-size: 8px;
    margin-top: 10px;
  }
`

const Title = styled.h1`
  margin-top: 10px;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 20px;
  line-height: 24px;

  @media (max-width: 500px) {
    font-size: 15px;
    margin-top: 8px;
  }
`

const Price = styled.div`
  margin-top: 10px;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  line-height: 24px;

  @media (max-width: 500px) {
    margin-top: 2px;
  }
`

const Rating = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  color: ${colors.mainColor};
  margin-top: 15px;

  @media (max-width: 500px) {
    margin-top: 2px;
  }

  span {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 800;
    font-size: 20px;
    line-height: 24px;
    color: black;

    @media (max-width: 500px) {
      font-size: 12px;
    }
  }
`
