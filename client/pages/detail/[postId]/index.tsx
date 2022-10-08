import React, {useState} from 'react'
import styled from 'styled-components'
import StoreDetail from '@/components/StoreDetail/StoreDetail'
import Footer from '@/components/Layout/Footer/Footer'
import {useRouter} from 'next/router'

// export const getServerSideProps = async (context: {query: {id: any}}) => {
//   const {id} = context.query
//   const res = await postService.getPostById(Number(id))
//   // const res = await fetch(`https://moontel-api.shop:8080/v1/posts/${id}?page=1&size=5`)
//   // const postData = await res.json()
//   return {
//     props: {
//       postData: res.data,
//     },
//     revalidate: 43200,
//   }
// }

// const Detail = () => {

// const Detail = ({postData}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
// const [tempPost, setTempPost] = useState<PostById>(postData)

const Detail = () => {
  const router = useRouter()
  const postId = router.query.postId

  return (
    <Container>
      {/* <StoreDetail post={tempPost} /> */}
      <StoreDetail postId={Number(postId)} />
      <Footer />
    </Container>
  )
}

export default Detail

const Container = styled.div`
  width: 100%;
`
