import styled from 'styled-components'
import CategoryTag from '@/components/Home/CategoryTag'
import {colors} from '@/styles/colors'
import {categoryTags} from '@/utils/options/options'
import PostCard from '@/components/Home/PostCard'
import SearchBar from '@/components/Home/SearchBar'
import {postService} from '@/apis/PostAndSearchAPI'
import {useEffect, useState} from 'react'
import useIntersect from '@/hooks/useIntersect'
import {Post} from '@/types/post'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import {flexCenter} from '@/styles/css'
import {GetStaticProps, InferGetStaticPropsType} from 'next'
import BannerSwiper from '@/components/Home/BannerSwiper'
import {bannerImages} from '@/public/images'
import Image from 'next/image'

export const getStaticProps: GetStaticProps = async () => {
  const res1 = await postService.getPosts(1)
  const res2 = await postService.getPosts(2)
  return {
    props: {
      posts1: res1.data,
      posts2: res2.data,
      pageInfo: res2.pageInfo,
    },
    revalidate: 14400, // 4시간
  }
}

function Home({posts1, posts2, pageInfo}: InferGetStaticPropsType<typeof getStaticProps>) {
  const [page, setPage] = useState(3)
  const [posts, setPosts] = useState<Post[]>([])
  const [totalPage, setTotalPage] = useState(pageInfo.totalPages)
  const [isLoading, setIsLoading] = useState(false)

  const fetchMore = async () => {
    setIsLoading(true)
    const res = await postService.getPosts(page)
    setPosts([...posts, ...res.data])
    setPage(page + 1)
    setTotalPage(res.pageInfo.totalPages)
    setIsLoading(false)
  }

  const callback = async () => {
    if (page > totalPage) return
    fetchMore()
  }

  // useEffect(() => {
  //   postService.getPosts(page).then((res) => {
  //     setPosts(res.data)
  //     setPage(page + 1)
  //     setTotalPage(res.pageInfo.totalPages)
  //   })
  // }, [])

  const ref = useIntersect(callback)

  if (!posts) return <LoadingSpinner></LoadingSpinner>

  return (
    <Container>
      <MainBanner>
        <BannerSwiper
          banners={[bannerImages.mainBanner, bannerImages.mainBanner2, bannerImages.mainBanner3]}
        />
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
        {posts1.map((post: Post) => (
          <PostCard post={post} key={post.id} />
        ))}
      </PostCardBox>
      <SubBanner>
        <Image src={bannerImages.subBanner} sizes={'100%'} alt={'BannerImg'} />
      </SubBanner>
      <PostCardBox>
        {posts2.map((post: Post, idx: number) => (
          <PostCard post={post} key={idx} />
        ))}
      </PostCardBox>
      <SubBanner>
        <Image src={bannerImages.subBanner2} sizes={'100%'} alt={'BannerImg'} />
      </SubBanner>
      <PostCardBox>
        {posts.map((post: Post, idx: number) => (
          <PostCard post={post} key={idx} />
        ))}
      </PostCardBox>
      {page > totalPage ? (
        <div></div>
      ) : isLoading ? (
        <SpinnerBox>
          <LoadingSpinner />
        </SpinnerBox>
      ) : (
        <Intersection ref={ref}></Intersection>
      )}
    </Container>
  )
}

export default Home

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`

const MainBanner = styled.div`
  margin-bottom: 40px;
  width: 100%;

  @media (min-width: 1920px) {
    width: 150%;
  }

  @media (min-width: 1540px) {
    width: 150%;
  }

  @media (max-width: 1280px) {
    width: 100%;
  }
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
  flex-wrap: wrap;
  gap: 15px;
`

const SubBanner = styled.div`
  margin-bottom: 40px;
  width: 100%;

  /* @media (min-width: 1920px) {
    width: 150%;
  }

  @media (max-width: 1280px) {
    width: 100%;
  } */
`

const SpinnerBox = styled.div`
  ${flexCenter}
  width: 100%;
`

const Intersection = styled.div`
  height: 5px;
  width: 100%;
`
