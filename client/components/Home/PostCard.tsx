import React from 'react'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import {GoLocation} from 'react-icons/go'
import {colors} from '@/styles/colors'
import {toLocalScale} from '@/utils/util'
import {AiFillStar} from 'react-icons/ai'
import {Post} from '@/types/post'
import {useRouter} from 'next/router'

interface Props {
  post: Post
}

function PostCard({post}: Props) {
  const router = useRouter()
  // const address = `${post.address[0].split(' ')[0]} ${post.address[0].split(' ')[1]}`
  return (
    <Container onClick={() => router.push(`/detail/${post.id}`)}>
      <ImgBox>
        <CardImage mode="post" imgUrl={post.img.url} />
      </ImgBox>
      <Content>
        <Location>
          <GoLocation />
          <span>{post.address}</span>
        </Location>
        <Title>{post.title.length < 37 ? post.title : post.title.slice(0, 38) + '...'}</Title>
        <Price>{toLocalScale(post.minPrice)}원 / 박</Price>
        <Rating>
          <AiFillStar />
          <span>{post.avgScore}</span>
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
  font-size: 13px;
  margin-top: 6px;

  @media (max-width: 500px) {
    font-size: 8px;
    margin-top: 10px;
  }
`

const Title = styled.h1`
  margin-top: 8px;
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
  margin-top: 6px;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  line-height: 24px;
  font-size: 16px;
  color: rgb(69, 69, 69);

  @media (max-width: 500px) {
    margin-top: 2px;
  }
`

const Rating = styled.div`
  display: flex;
  align-items: center;
  gap: 3px;
  color: ${colors.mainColor};
  font-size: 16px;

  @media (max-width: 500px) {
    margin-top: 1px;
  }

  span {
    font-family: 'Inter';
    font-weight: 450;
    font-size: 20px;
    line-height: 24px;
    color: rgb(85, 85, 85);
    font-size: 13px;

    @media (max-width: 500px) {
      font-size: 12px;
    }
  }
`
