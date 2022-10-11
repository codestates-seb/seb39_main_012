import {ceoService} from '@/apis/CeoPageAPI'
import {PostRecoil} from '@/types/ceopage'
import React from 'react'
import {toast} from 'react-toastify'
import styled from 'styled-components'
import CardImage from '../CardImage/CardImage'
import CompanyInfoTag from './CompanyInfoTag'

interface Props {
  post: PostRecoil
}

function CompanyInfoCard({post}: Props) {
  return (
    <Contaienr>
      <CardImage mode="post" imgUrl={post.thumbnail}></CardImage>
      <Title>{post.title}</Title>
      <Content>
        {/* <Price>30,000원 / 1박</Price> */}
        <CompanyInfoTag title={'수정'} onClick={() => console.log('수정')} />
        <CompanyInfoTag
          title={'삭제'}
          onClick={async () => {
            if (window.confirm('정말 삭제하시겠습니까?')) {
              const result = await ceoService.deleteMyCompany(post.id)
              if (result.status === 204) {
                toast.success('삭제되었습니다.')
                return
              }
              toast.error('삭제에 실패했습니다.')
            }
          }}
        />
      </Content>
    </Contaienr>
  )
}

export default CompanyInfoCard

const Contaienr = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
  width: 250px;

  @media (max-width: 768px) {
    width: 200px;
  }

  @media (max-width: 390px) {
    width: 170px;
  }
`

const Title = styled.h1`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 15px;
  line-height: 18px;
  display: flex;

  @media (max-width: 768px) {
    font-size: 12px;
  }
`
const Content = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
`

const Price = styled.p`
  font-family: 'Inter';
  font-style: normal;
  font-weight: 800;
  font-size: 15px;
  line-height: 18px;
`
