import Image from 'next/image'
import {ToastContainer} from 'react-toastify'
import styled from 'styled-components'
import mainBanner from '@/public/images/mainBanner.png'
import subBanner from '@/public/images/subBanner.jpg'
import CategoryTag from '@/components/Home/CategoryTag'
import {colors} from '@/styles/colors'
import {categoryTags} from '@/utils/options/options'
import PostCard from '@/components/Home/PostCard'
import {dummyPosts} from '@/utils/dummy/dummy'
import SearchBar from '@/components/Home/SearchBar'

function Home() {
  return (
    <Container>
      <MainBanner>
        <Image src={mainBanner} />
      </MainBanner>
      <MainSearchBar>
        <SearchBar />
      </MainSearchBar>
      <CategoryBox>
        {categoryTags.map((title, idx) => (
          <CategoryTag title={title} key={idx} />
        ))}
      </CategoryBox>
      <PostCardBox>
        {Array.from({length: 8}).map((_, idx) => (
          <PostCard key={idx} post={dummyPosts[0]}></PostCard>
        ))}
      </PostCardBox>
      <SubBanner>
        <Image src={subBanner} />
      </SubBanner>
      <PostCardBox>
        {Array.from({length: 8}).map((_, idx) => (
          <PostCard key={idx} post={dummyPosts[0]}></PostCard>
        ))}
      </PostCardBox>
      <ToastContainer />
    </Container>
  )
}

export default Home

const Container = styled.div`
  width: 100%;
`

const MainBanner = styled.div`
  margin-bottom: 40px;
`

const MainSearchBar = styled.div`
  width: 100%;
  height: 100px;
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: ${colors.grey1};
`

const CategoryBox = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 30px;
  width: 100%;
  margin: 40px 0;

  & > div:nth-child(odd) {
    background-color: ${colors.subColor1};
    color: ${colors.subFontColor1};
  }

  & > div:nth-child(even) {
    background-color: ${colors.subColor2};
    color: ${colors.subFontColor2};
  }

  @media (max-width: 768px) {
    gap: 20px;
  }

  @media (max-width: 390px) {
    gap: 15px;
  }
`

const PostCardBox = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 15px;
`

const SubBanner = styled.div`
  margin-bottom: 50px;
`
