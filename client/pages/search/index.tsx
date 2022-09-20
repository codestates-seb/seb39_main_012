import {GetServerSideProps} from 'next'
import PostCard from '@/components/Home/PostCard'
import SearchBar from '@/components/Home/SearchBar'
import {colors} from '@/styles/colors'
import {dummyPosts} from '@/utils/dummy/dummy'
import React from 'react'
import styled from 'styled-components'

// export const getServerSideProps: GetServerSideProps = async (context) => {
//   console.log(context.query)
//   // data 없을 땐 리턴값을 달리함

//   return {
//     redirect: {
//       destination: '/',
//       permanent: false,
//     },
//   }
// }

function Search() {
  return (
    <Container>
      <MainSearchBar>
        <SearchBar />
      </MainSearchBar>
      <PostCardBox>
        {Array.from({length: 8}).map((_, idx) => (
          <PostCard key={idx} post={dummyPosts[0]}></PostCard>
        ))}
      </PostCardBox>
    </Container>
  )
}

export default Search

const Container = styled.div`
  width: 100%;
`

const MainSearchBar = styled.div`
  width: 100%;
  height: 100px;
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: ${colors.grey1};
  margin-bottom: 20px;
`
const PostCardBox = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 15px;
`
